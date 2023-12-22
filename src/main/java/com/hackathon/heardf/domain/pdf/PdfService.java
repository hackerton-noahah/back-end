package com.hackathon.heardf.domain.pdf;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.aspose.words.cloud.*;
import com.aspose.words.cloud.api.WordsApi;
import com.aspose.words.cloud.model.requests.ConvertDocumentRequest;
import com.hackathon.heardf.domain.pdf.dto.PdfRequestDto.PdfToText;
import com.hackathon.heardf.domain.pdf.dto.PdfRequestDto.RegisterPdf;
import com.hackathon.heardf.domain.pdf.dto.PdfResponseDto.TextResult;
import com.hackathon.heardf.global.s3.S3Service;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import javax.mail.MessagingException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;

@Service
@Log4j2
@RequiredArgsConstructor
public class PdfService {

    private final PdfRepository pdfRepository;

    private final S3Service s3Service;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    @Value("${aspose.client-id}")
    private String clientId;
    @Value("${aspose.client-secret}")
    private String clientSecret;

    private final AmazonS3Client amazonS3Client;

    public void register(RegisterPdf registerPdf) {
        Pdf pdf = new Pdf(registerPdf.getName(), registerPdf.getUrl());
        pdfRepository.save(pdf);
    }

    public void makePdfToText(PdfToText pdfToText) throws IOException, ApiException, MessagingException {
        Pdf pdf = pdfRepository.findById(pdfToText.getId()).orElse(null);
        if(pdf == null) throw new RuntimeException("ID에 해당하는 PDF를 찾을 수 없음");

        String pdfS3Name = s3Service.parseFileName(pdf.getUrl());

        useApi(pdfS3Name);
    }

    public TextResult getPdfToText(Long id) {
        //TODO: pdf id에 해당하는 text 파일을 읽어와서 String을 임시 내용 대신 넣어주면 됨
        log.info("pdf id: {}", id);
        return new TextResult("임시 내용");
    }

    public void useApi(String pdfName) throws IOException, MessagingException, ApiException {
        ApiClient apiClient = new ApiClient(clientId, clientSecret, null);
        WordsApi wordsApi = new WordsApi(apiClient);
        log.info("1. client 생성 성공");

        // pdf 이름으로 S3에서 Object 가져오기
        S3Object s3Object = amazonS3Client.getObject(bucket, pdfName);

        log.info("2. s3에서 pdf 로드 성공");

        InputStream inputStream = s3Object.getObjectContent();
        byte[] pdfBytes = IOUtils.toByteArray(inputStream);
        inputStream.close();

        log.info("3. input stream close");

        File pdfFile = new File("path/to/save/test.pdf");
        FileUtils.writeByteArrayToFile(pdfFile, pdfBytes);
        Resource resource = new FileSystemResource(pdfFile.getAbsolutePath());

        log.info("4. pdf 저장! 경로 {}", resource);

        byte[] doc = Files.readAllBytes(Paths.get("test.pdf").toAbsolutePath());
        ConvertDocumentRequest request = new ConvertDocumentRequest(
                doc, "txt", null, null, null, null, null, null, null);
        byte[] convert = wordsApi.convertDocument(request);

        log.info("5. API 요청 성공");

        // text 파일 생성
        File file = new File("/files/txt/result.txt");
        if (!file.exists()) {   // 파일이 없다면 새로 생성
            file.createNewFile();
        }

        log.info("6. txt 파일 생성 성공");

        // FileWriter 객체 생성
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        // result.txt에 내용 작성
        bw.write(Arrays.toString(convert));
        log.info("7. txt 파일에 작성 완료");

        // 자원 해제
        bw.close();
    }
}

package com.hackathon.heardf.domain.pdf;

import com.aspose.words.cloud.ApiException;
import com.hackathon.heardf.domain.pdf.dto.PdfRequestDto;
import com.hackathon.heardf.domain.pdf.dto.PdfRequestDto.PdfToText;
import com.hackathon.heardf.domain.pdf.dto.PdfRequestDto.RegisterPdf;
import com.hackathon.heardf.domain.pdf.dto.PdfResponseDto.TextResult;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import jakarta.validation.Valid;
import java.io.IOException;
import javax.mail.MessagingException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PdfController {

    private final PdfService pdfService;

    /**
     * [GET] test API
     */
    @Operation(summary = "test API")
    @GetMapping("/test")
    public ResponseEntity<Integer> test() {
        return ResponseEntity.ok(200);
    }

    /**
     * [POST] PDF 등록
     */
    @Operation(summary = "PDF 등록")
    @PostMapping("/pdfs")
    public ResponseEntity<Long> registerPdf(@RequestBody RegisterPdf registerPdf) {
        return ResponseEntity.ok(pdfService.register(registerPdf));
    }

    /**
     * [POST] PDF -> TEXT 변환하기 (ASPOSE 이용)
     */
    @Operation(summary = "PDF -> TEXT 변환하기")
    @PostMapping("/pdfs/text")
    public ResponseEntity<Integer> makePdfToText(@RequestBody PdfToText pdfToText)
            throws IOException, ApiException, MessagingException {
        pdfService.makePdfToText(pdfToText);
        return ResponseEntity.ok(200);
    }

    /**
     * [GET] PDF- > TEXT 결과 조회하기
     */
    @Operation(summary = "PDF- > TEXT 결과 조회하기")
    @GetMapping("/pdfs/{id}")
    public ResponseEntity<TextResult> getText(@PathVariable("id") Long id) {
        return ResponseEntity.ok(pdfService.getPdfToText(id));
    }
}

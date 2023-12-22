package com.hackathon.heardf.domain.pdf;

import com.aspose.words.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PdfService {

    private final PdfRepository pdfRepository;

    @Value("${aspose.client-id}")
    private String clientId;
    @Value("${aspose.client-secret}")
    private String clientSecret;


    /**
    ApiClient apiClient = new ApiClient("clientId, clientSecret, null);
    WordsApi wordsApi = new WordsApi(apiClient);

    byte[] doc = Files.readAllBytes(Paths.get("test.pdf").toAbsolutePath());
    ConvertDocumentRequest request = new ConvertDocumentRequest(
            doc, "txt", null, null, null, null);
    ConvertDocument convert = wordsApi.convertDocument(request);
     */
}

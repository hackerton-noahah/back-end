package com.hackathon.heardf.domain.pdf;

import com.hackathon.heardf.domain.pdf.dto.PdfRequestDto;
import com.hackathon.heardf.domain.pdf.dto.PdfRequestDto.RegisterPdf;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PdfController {

    private final PdfService pdfService;

    /**
     * [POST] PDF 등록
     */
    @Operation(summary = "PDF 등록")
    @PostMapping("/pdfs")
    public ResponseEntity<Integer> registerChallenge(@RequestBody RegisterPdf registerPdf) {
        pdfService.register(registerPdf);
        return ResponseEntity.ok(200);
    }

    /**
     * TODO: [POST] PDF -> TEXT 변환하기 (ASPOSE 이용)
     */

    /**
     * TODO: [GET] TEXT 조회하기
     */
}

package com.hackathon.heardf.domain.pdf;

import com.aspose.words.cloud.ApiException;
import com.hackathon.heardf.domain.pdf.dto.PdfRequestDto;
import com.hackathon.heardf.domain.pdf.dto.PdfRequestDto.PdfToText;
import com.hackathon.heardf.domain.pdf.dto.PdfRequestDto.RegisterPdf;
import com.hackathon.heardf.domain.pdf.dto.PdfResponseDto.TextResult;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.io.IOException;
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
     * [POST] PDF 등록
     */
    @Operation(summary = "PDF 등록")
    @PostMapping("/pdfs")
    public ResponseEntity<Integer> registerPdf(@RequestBody RegisterPdf registerPdf) {
        pdfService.register(registerPdf);
        return ResponseEntity.ok(200);
    }

    /**
     * [POST] PDF -> TEXT 변환하기 (ASPOSE 이용)
     */
    @Operation(summary = "PDF -> TEXT 변환하기")
    @PostMapping("/pdfs/text")
    public ResponseEntity<Integer> makePdfToText(@RequestBody PdfToText pdfToText) throws IOException, ApiException {
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

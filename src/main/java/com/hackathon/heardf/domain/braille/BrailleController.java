package com.hackathon.heardf.domain.braille;

import com.hackathon.heardf.domain.braille.dto.BrailleRequestDto.TextToBraille;
import com.hackathon.heardf.domain.braille.dto.BrailleResponseDto.BrailleResult;
import com.hackathon.heardf.domain.pdf.dto.PdfRequestDto.RegisterPdf;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BrailleController {

    private final BrailleService brailleService;

    /**
     * TODO: [POST] TEXT -> 점자 변환하기 (GPT 이용)
     */
    @Operation(summary = "TEXT -> 점자 변환하기")
    @PostMapping("/brailles")
    public ResponseEntity<BrailleResult> registerBraille(@RequestBody TextToBraille textToBraille) {
        return ResponseEntity.ok(brailleService.registerBraille(textToBraille));
    }

    /**
     * TODO: [GET] 점자 조회하기
     */
}

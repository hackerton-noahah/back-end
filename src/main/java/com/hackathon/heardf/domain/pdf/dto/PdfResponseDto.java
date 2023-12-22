package com.hackathon.heardf.domain.pdf.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PdfResponseDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TextResult {
        private String text;
    }
}

package com.hackathon.heardf.domain.pdf.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PdfRequestDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RegisterPdf {
        private String name;
        private String url;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PdfToText {
        private Long id;
    }
}

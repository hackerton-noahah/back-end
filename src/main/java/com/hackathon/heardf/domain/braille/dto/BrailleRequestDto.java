package com.hackathon.heardf.domain.braille.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BrailleRequestDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TextToBraille {
        private String text;
    }
}

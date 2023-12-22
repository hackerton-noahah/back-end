package com.hackathon.heardf.domain.braille.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BrailleResponseDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BrailleResult {
        private String braille;
    }
}

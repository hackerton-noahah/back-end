package com.hackathon.heardf.domain.braille;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BrailleController {

    private final BrailleService brailleService;
}

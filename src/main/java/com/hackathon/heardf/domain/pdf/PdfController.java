package com.hackathon.heardf.domain.pdf;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PdfController {

    private final PdfService pdfService;
}

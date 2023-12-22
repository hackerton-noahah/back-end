package com.hackathon.heardf.domain.braille;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrailleService {

    private final BrailleRepository brailleRepository;
}

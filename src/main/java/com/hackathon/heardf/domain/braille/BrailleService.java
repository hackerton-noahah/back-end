package com.hackathon.heardf.domain.braille;

import com.hackathon.heardf.domain.braille.dto.BrailleRequestDto.TextToBraille;
import com.hackathon.heardf.domain.braille.dto.BrailleResponseDto.BrailleResult;
import com.hackathon.heardf.global.gpt.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrailleService {

    private final BrailleRepository brailleRepository;
    private final ChatService chatService;

    public BrailleResult registerBraille(TextToBraille textToBraille) {
        BrailleResult brailleResult = chatService.getChatResponse(textToBraille.getText());
        brailleRepository.save(new Braille(textToBraille.getText(), brailleResult.getBraille()));
        return brailleResult;
    }
}

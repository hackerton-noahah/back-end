package com.hackathon.heardf.global.gpt;

import com.hackathon.heardf.domain.braille.dto.BrailleResponseDto.BrailleResult;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final String prePrompt = "\n이 내용을 점자로 바꿔줘.";

    private final ChatgptService chatgptService;

    public BrailleResult getChatResponse(String text) {
        // ChatGPT 에게 질문을 던집니다.
        String question = "\"" + text + "\"" + prePrompt;
        String responseMessage = chatgptService.sendMessage(question);
        return new BrailleResult(responseMessage);
    }
}

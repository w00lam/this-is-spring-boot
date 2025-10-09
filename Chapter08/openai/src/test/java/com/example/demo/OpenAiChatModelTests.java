package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OpenAiChatModelTests {
    @Autowired
    private OpenAiChatModel chatModel;

    @Test
    public void testChatModelSimple() {
        String result = chatModel.call("서울 올림픽은 몇 회 올림픽이야?");
        System.out.println(result);
    }

    @Test
    public void testChatModelMessage() {
        UserMessage userMessage = new UserMessage("서울 올림픽에 대해 알려 주세요");
        SystemMessage systemMessage = new SystemMessage("답변은 최대한 간결하게 하고 관련된 내용은 뉴스를 참고하고 해 줘");
        String result = chatModel.call(userMessage, systemMessage);
        System.out.println(result);
    }
}

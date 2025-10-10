package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.metadata.RateLimit;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.ai.openai.audio.speech.SpeechResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootTest
public class OpenAiAudioSpeechModelTests {
    @Autowired
    private OpenAiAudioSpeechModel speechModel;

    @Test
    public void testSpeechModelSimple() throws IOException {
        byte[] bin = speechModel.call("""
                안녕하세요 반갑습니다.
                스프링 부트는 자바 프레임워크 중에 가장 인기가 많은 프레임워크입니다.
                """);
        Files.write(Paths.get("D:\\archive\\audio\\ai_tts_simple.mp3"), bin);
    }

    @Test
    public void testSpeechModelOptions() throws IOException {
        OpenAiAudioSpeechOptions options = OpenAiAudioSpeechOptions.builder()
                .model("tts-1-hd")
                .voice(OpenAiAudioApi.SpeechRequest.Voice.NOVA)
                .responseFormat(OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3)
                .speed(1.0f)
                .build();

        String textToSpeech = """
                안녕하세요 반갑습니다.
                스프링부트는 자바 프레임워크 중에 가장 인기가 많은 프레임워크입니다.
                """;

        SpeechPrompt prompt = new SpeechPrompt(textToSpeech, options);
        SpeechResponse response = speechModel.call(prompt);
        System.out.println("meta = " + response.getMetadata());

        RateLimit limit = response.getMetadata().getRateLimit();
        if (limit != null) {
            System.out.println("requestLimit = " + limit.getRequestsLimit() +
                    ", requestRemaining = " + limit.getRequestsRemaining() +
                    ", requestReset = " + limit.getRequestsReset());
            System.out.println("tokensLimit = " + limit.getTokensLimit() +
                    ", tokensRemaining = " + limit.getTokensRemaining() +
                    ", tokensReset = " + limit.getTokensReset());
        }

        Files.write(Paths.get("D;\\archive\\audio\\ai_tts_options.mp3"), response.getResult().getOutput());
    }
}

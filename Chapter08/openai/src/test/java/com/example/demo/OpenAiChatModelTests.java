package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.metadata.RateLimit;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

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

    @Test
    public void testChatModeMessageContext() {
        var system = new SystemMessage("간략하게 답변해 주세요");
        var message1 = new UserMessage("서울 올림픽에 대해 알려 주세요");
        var assistant1 = new AssistantMessage("서울 올림픽은 1988년 9월 17일부터 10월 2일까지 대한민국 서울에서 개최된 제24회 하계 올림픽입니다. 이 대회는 한국이 처음으로 주최한 올림픽으로, 아시아에서 열린 두 번째 하계 올림픽이기도 합니다. " +
                "총 159개 국가에서 약 8,391명의 선수가 참가하였으며, 23종목에서 237개의 메달이 경쟁되었습니다. 서울 올림픽은 정치적 긴장 속에서도 성공적으로 개최되어, 한국의 국제적 위상을 높이는 계기가 되었습니다. " +
                "특히, 이 대회에서는 올림픽 역사상 처음으로 여자 유도, 태권도, 그리고 배드민턴이 정식 종목으로 포함되었습니다. 서울 올림픽은 또한 개막식과 폐막식의 화려함, 그리고 대중의 열띤 참여로 기억되고 있습니다. " +
                "이후, 서울 올림픽은 대한민국의 경제 성장과 문화 발전에도 큰 영향을 미쳤습니다.");
        var message2 = new UserMessage("그럼 바로 그 이전 올림픽은 어디야?");
        var assistant2 = new AssistantMessage("바로 이전 올림픽은 1984년 하계 올림픽으로, 미국 로스앤잴레스에서 개최되었습니다. 이 대회는 7월 28일부터 8월 12일까지 진행되었고, 많은 국가가 참여한 강행된 올림픽 중 하나였습니다.");
        var message3 = new UserMessage("그럼 그 2개의 올림픽 중 참여 국가는 어디가 많아?");
        String result = chatModel.call(system, message1, assistant1, message2, assistant2, message3);
        System.out.println(result);
    }

    @Test
    public void testChatGptPrompt() {
        List<Message> messages = List.of(
                new SystemMessage("간략하게 답변해 주세요."),
                new UserMessage("서울 올림픽에 대해 알려주세요"),
                new AssistantMessage("서울 올림픽은 1988년 9월 17일부터 10월 2일까지 대한민국 서울에서 개최된 제24회 하계 올림픽입니다. 이 대회는 한국이 처음으로 주최한 올림픽으로, 아시아에서 열린 두 번째 하계 올림픽이기도 합니다. " +
                        "총 159개 국가에서 약 8,391명의 선수가 참가하였으며, 23종목에서 237개의 메달이 경쟁되었습니다. 서울 올림픽은 정치적 긴장 속에서도 성공적으로 개최되어, 한국의 국제적 위상을 높이는 계기가 되었습니다. " +
                        "특히, 이 대회에서는 올림픽 역사상 처음으로 여자 유도, 태권도, 그리고 배드민턴이 정식 종목으로 포함되었습니다. 서울 올림픽은 또한 개막식과 폐막식의 화려함, 그리고 대중의 열띤 참여로 기억되고 있습니다. " +
                        "이후, 서울 올림픽은 대한민국의 경제 성장과 문화 발전에도 큰 영향을 미쳤습니다."),
                new UserMessage("그럼 바로 그 이전 올림픽은 어디야?"),
                new AssistantMessage("바로 이전 올림픽은 1984년 하계 올림픽으로, 미국 로스앤잴레스에서 개최되었습니다. 이 대회는 7월 28일부터 8월 12일까지 진행되었고, 많은 국가가 참여한 강행된 올림픽 중 하나였습니다."),
                new UserMessage("그럼 그 2개의 올림픽 중 참여 국가는 어디가 많아?")
        );

        ChatOptions chatOptions = OpenAiChatOptions.builder()
                .model(OpenAiApi.ChatModel.GPT_4_O)
                .N(2)
                .temperature(1.0).build();

        Prompt prompt = new Prompt(messages, chatOptions);

        ChatResponse chatResponse = chatModel.call(prompt);

        // 토큰 사용량
        Usage usage = chatResponse.getMetadata().getUsage();
        System.out.println("promptsTokens = " + usage.getPromptTokens());
        System.out.println("completionTokens = " + usage.getCompletionTokens());
        System.out.println("totalTokens = " + usage.getTotalTokens());

        // 요청 제한 상세
        RateLimit rateLimit = chatResponse.getMetadata().getRateLimit();
        System.out.println("requestLimit = " + rateLimit.getRequestsLimit());
        System.out.println("requestRemaining = " + rateLimit.getRequestsRemaining());
        System.out.println("requestReset = " + rateLimit.getRequestsReset());

        // 토큰 사용 제한 상세
        System.out.println("tokensLimit = " + rateLimit.getTokensLimit());
        System.out.println("tokensRemaining = " + rateLimit.getTokensRemaining());
        System.out.println("tokensReset = " + rateLimit.getTokensReset());

        // 답변 출력
        for (Generation generation : chatResponse.getResults()) {
            System.out.println(generation.getOutput().getText());
        }
    }

    @Test
    public void testChatModelImage() throws MalformedURLException {
        Resource resource = new ClassPathResource("image/Disney_World_2.jpg");

        Message message = new UserMessage("사진 속의 풍경을 멋진 시로 써 주세요.");
        String result = chatModel.call(message);
        System.out.println(result);
    }

    @Test
    public void testChatModelAudioInput() {
        var resource = new ClassPathResource("audio/sample_audio.mp3");

        var message = new UserMessage("이 오디오 파일의 내용에 대해 요약해 주세요");

        ChatResponse response = chatModel.call(
                new Prompt(List.of(message),
                        OpenAiChatOptions.builder()
                                .model(OpenAiApi.ChatModel.GPT_4_O_AUDIO_PREVIEW)
                                .build())
        );

        System.out.println(response.getResult().getOutput().getText());
    }

    @Test
    public void testChatModelAudioOutput() throws IOException {
        String message = "스프링 부트에 대해 간단하게 설명해 주세요";
        ChatResponse response = chatModel.call(new Prompt(message, OpenAiChatOptions.builder()
                .temperature(0.5)
                .model(OpenAiApi.ChatModel.GPT_4_O_AUDIO_PREVIEW)
                .outputModalities(List.of("text", "audio"))
                .outputAudio(new OpenAiApi.ChatCompletionRequest.AudioParameters(
                        OpenAiApi.ChatCompletionRequest.AudioParameters.Voice.NOVA,
                        OpenAiApi.ChatCompletionRequest.AudioParameters.AudioResponseFormat.MP3))
                .build()));

        String text = response.getResult().getOutput().getText();
        System.out.println("result = " + text);

        byte[] audio = response.getResult().getOutput().getMedia().getFirst().getDataAsByteArray();
        Files.write(Paths.get("D:\\archive\\audio\\ai_chat_audio.mp3"), audio);
    }

    @Test
    public void testChatModelAudioInputOutput() throws IOException {
        var resource = new ClassPathResource("audio/sample_audio_ask.mp3");
        var message = new UserMessage("질문에 친절하고 간략하게 답변해 주세요");

        ChatResponse response = chatModel.call(new Prompt(message,
                OpenAiChatOptions.builder()
                        .temperature(0.5)
                        .model(OpenAiApi.ChatModel.GPT_4_O_AUDIO_PREVIEW)
                        .outputModalities(List.of("text", "audio"))
                        .outputAudio(new OpenAiApi.ChatCompletionRequest.AudioParameters(
                                OpenAiApi.ChatCompletionRequest.AudioParameters.Voice.NOVA,
                                OpenAiApi.ChatCompletionRequest.AudioParameters.AudioResponseFormat.MP3))
                        .build()));

        String text = response.getResult().getOutput().getText(); // audio transcript
        System.out.println("result = " + text);

        byte[] audio = response.getResult().getOutput().getMedia().getFirst().getDataAsByteArray(); // audio data
        Files.write(Paths.get("D:\\archive\\audio\\ai_chat_audio_answer.mp3"), audio);
    }
}

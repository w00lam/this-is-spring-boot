package com.example.demo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.Embedding;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OpenAiEmbeddingModelTests {
    private static final Logger log = LoggerFactory.getLogger(OpenAiEmbeddingModelTests.class);

    @Autowired
    private OpenAiEmbeddingModel embeddingModel;
    @Autowired
    private PgVectorStore vectorStore;
    @Autowired
    private OpenAiChatModel chatModel;

    @Test
    public void testEmbeddingModelSimple() {
        float[] vector = embeddingModel.embed("첫 번째 텍스트 문서입니다.");
        log.info("vector = {}", vector);
    }

    @Test
    public void testEmbeddingModelResponse() {
        String text1 = "첫 번째 텍스트 문서입니다.";
        String text2 = "두 번째 텍스트 문서입니다.";
        EmbeddingResponse response = embeddingModel.embedForResponse(List.of(text1, text2));

        log.info("metadata.model = {}", response.getMetadata());
        log.info("metadata.usage.promptTokens = {}, generationTokens = {}, totalTokens = {}",
                response.getMetadata().getUsage().getPromptTokens(),
                response.getMetadata().getUsage().getCompletionTokens(),
                response.getMetadata().getUsage().getTotalTokens());

        for (Embedding embedding : response.getResults()) {
            float[] vector = embedding.getOutput();
            log.info("vector = {}", vector);
        }
    }

    @Test
    public void testVectorStore() {
        String text1 = "첫 번째 텍스트 문서입니다.";
        String text2 = "두 번째 텍스트 문서입니다.";
        List<Document> documents = List.of(new Document(text1), new Document(text2));
        vectorStore.write(documents);
    }
}

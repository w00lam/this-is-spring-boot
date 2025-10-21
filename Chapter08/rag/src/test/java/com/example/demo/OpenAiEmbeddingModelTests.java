package com.example.demo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.document.Document;
import org.springframework.ai.document.DocumentReader;
import org.springframework.ai.embedding.Embedding;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.text.MessageFormat;
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

    @Test
    public void textReader() {
        DocumentReader reader = new TextReader("classpath:/운수좋은날.txt");
        List<Document> documents = reader.read();

        TokenTextSplitter splitter = new TokenTextSplitter();
        List<Document> chunks = splitter.split(documents);

        vectorStore.write(splitter.split(chunks));
    }

    @Test
    public void pdfReader() throws IOException {
        var reader = new PagePdfDocumentReader("classpath:/인공지능_시대의_예술.pdf");
        List<Document> documents = reader.read();

        TokenTextSplitter splitter = new TokenTextSplitter();
        List<Document> chunks = splitter.split(documents);

        vectorStore.write(splitter.split(chunks));
    }

    @Test
    public void textReaderSimilaritySearch() {
        String question = "김첨지 아내는 무슨 병에 걸렸나요?";
        var documents = vectorStore.similaritySearch(question);

        assert documents != null;
        var information = String.join("\n", documents.stream().map(Document::getText).toList());

        String prompt = MessageFormat.format("""
                다음의 정보를 기반으로 하여 답을 하고,
                정보가 없는 경우에는 모른다고 답변하세요.
                [정보]
                {0}
                [질문]
                {1}
                """, information, question);

        Message message = new UserMessage(prompt);
        String result = chatModel.call(message);
        log.info("result = {}", result);
    }
}

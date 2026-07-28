package rag.mcp.mcp_server.tools;



import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class RagTool {

    private static final Logger log = LoggerFactory.getLogger(RagTool.class);

    private final VectorStore vectorStore;

    public RagTool(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public record RagRequest(
            String query
    ){}

    public record RagResponse(
            String documents
    ){}

    @Tool(
            name = "getDocuments",
            description = """
                    Searches the organization's document knowledge base
                    for information relevant to the user's question.
                    Use this tool when the user asks about information
                    that may be contained in the organization's documents.
                    """
    )
    public RagResponse getDocuments(@NonNull RagRequest ragRequest){
        SearchRequest request = SearchRequest.builder()
                .query(ragRequest.query())
                .topK(2)
                .similarityThreshold(0.3)
                .build();
        List<Document> documents = vectorStore.similaritySearch(request);

        if (documents.isEmpty()) {
            return new RagResponse(
                    "No relevant documents were found."
            );
        }

        String docs = documents.stream()
                .map(doc -> """
                    Source: %s

                    %s
                    """.formatted(
                        doc.getMetadata().getOrDefault("file_name", "Unknown"),
                        doc.getText()))
                .collect(Collectors.joining("\n\n----------------\n\n"));


        return new RagResponse(docs);
    }
}






package rag.mcp.mcp_server.configs;


import io.modelcontextprotocol.server.McpServerFeatures;
import io.modelcontextprotocol.spec.McpSchema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PromptConfig {


    @Bean
    public List<McpServerFeatures.SyncPromptSpecification> greetingPrompt(){

        var prompt = McpSchema.Prompt.builder("greeting")
                .description("A friendly greeting prompt")
                .arguments(List.of(
                        McpSchema.PromptArgument.builder("name")
                                .title("name")
                                .required(true)
                                .build()
                ))
                .build();

        var promptSpecification = new McpServerFeatures.SyncPromptSpecification(prompt, (exchange, getPromptRequest) -> {
            String nameArgument = (String) getPromptRequest.arguments().get("name");
            if (nameArgument == null) { nameArgument = "friend"; }
            var userMessage = new McpSchema.PromptMessage(McpSchema.Role.USER, McpSchema.TextContent.builder("Hello " + nameArgument + "! How can I assist you today?").build());
            return McpSchema.GetPromptResult.builder(List.of(userMessage)).description("A personalized greeting message").build();
        });

        return List.of(promptSpecification);
    }
}

package rag.mcp.mcp_server.configs;


import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rag.mcp.mcp_server.tools.RagTool;

@Configuration
public class RagConfig {

    @Bean
    public ToolCallbackProvider ragTools(RagTool ragTool){
        return MethodToolCallbackProvider.builder().toolObjects(ragTool).build();
    }



}

package rag.mcp.mcp_server.configs;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rag.mcp.mcp_server.tools.DateTimeTool;
import rag.mcp.mcp_server.tools.ProductTool;
import rag.mcp.mcp_server.tools.WeatherTool;

@Configuration
public class ToolsConfig {

    @Bean
    public ToolCallbackProvider productTools(ProductTool productTool){
        return MethodToolCallbackProvider.builder().toolObjects(productTool).build();
    }

    @Bean
    public ToolCallbackProvider dateTimeTools(DateTimeTool dateTimeTool){
        return MethodToolCallbackProvider.builder().toolObjects(dateTimeTool).build();

    }

    @Bean
    public ToolCallbackProvider weatherTools(WeatherTool weatherTool){
        return MethodToolCallbackProvider.builder().toolObjects(weatherTool).build();

    }

}

package rag.mcp.mcp_server.configs;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rag.mcp.mcp_server.tools.*;

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


    @Bean
    public ToolCallbackProvider customerTools(CustomerTool customerTool){
        return MethodToolCallbackProvider.builder().toolObjects(customerTool).build();

    }

    @Bean
    public ToolCallbackProvider orderTools(OrderTool orderTool){
        return MethodToolCallbackProvider.builder().toolObjects(orderTool).build();

    }

    @Bean
    public ToolCallbackProvider cartTools(ShoppingCartTool cartTool){
        return MethodToolCallbackProvider.builder().toolObjects(cartTool).build();

    }

}

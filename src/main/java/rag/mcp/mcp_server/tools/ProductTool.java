package rag.mcp.mcp_server.tools;


import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;
import rag.mcp.mcp_server.clients.ProductApiClient;
import rag.mcp.mcp_server.records.ProductResponse;

@Component
public class ProductTool {


    private final ProductApiClient productApiClient;

    public ProductTool(ProductApiClient productApiClient) {
        this.productApiClient = productApiClient;
    }


    @Tool(
            name = "getProductById",
            description = """
        Retrieves a product by its numeric ID.
        The productId parameter MUST be a JSON integer, not a string.
        Example:
        {"productId":18}
        """
    )
    public ProductResponse getProductById(@ToolParam(description = "Product id") Long productId) {
        return productApiClient.getById(productId);
    }


    @Tool(
            name = "getProductByName",
            description = "Retrieves a product by its exact name"
    )
    public ProductResponse getProductByName(@ToolParam(description = "Product name") String name) {
        return productApiClient.getByName(name);

    }
}

package rag.mcp.mcp_server.tools;


import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;
import rag.mcp.mcp_server.clients.ShoppingCartApiClient;
import rag.mcp.mcp_server.records.CartResponse;
import rag.mcp.mcp_server.records.CartToolResponse;

@Component
public class ShoppingCartTool {

    private final ShoppingCartApiClient cartApiClient;

    public ShoppingCartTool(ShoppingCartApiClient cartApiClient) {
        this.cartApiClient = cartApiClient;
    }

    @Tool(
            name = "getShoppingCart",
            description = "Retrieves a shopping cart"
    )
    public CartToolResponse getShoppingCart(@ToolParam(description = "shopping cart email") String email){

        return cartApiClient.getCartByEmail(email);
    }

}

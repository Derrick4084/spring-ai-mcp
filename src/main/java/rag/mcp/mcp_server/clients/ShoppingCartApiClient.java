package rag.mcp.mcp_server.clients;


import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import rag.mcp.mcp_server.components.TokenGenerator;
import rag.mcp.mcp_server.enums.UserType;
import rag.mcp.mcp_server.records.CartItem;
import rag.mcp.mcp_server.records.CartResponse;
import rag.mcp.mcp_server.records.CartToolResponse;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShoppingCartApiClient {

    private final RestClient restClient;
    private final TokenGenerator tokenGenerator;


    public ShoppingCartApiClient(RestClient restClient, TokenGenerator tokenGenerator) {
        this.restClient = restClient;
        this.tokenGenerator = tokenGenerator;
    }


    public CartToolResponse getCartByEmail(String email) {

        String token = tokenGenerator.generate(
                "agent@example.com",
                "abc12345",
                UserType.USER
        );


        CartResponse response = restClient.get().uri("/cart/{email}", email)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .body(CartResponse.class);

        if (response == null) {
            throw new RuntimeException("No cart found");
        }


        return new CartToolResponse(
                response.customerEmail(),
                response.totalAmount(),
                response.items().size(),
                response.items().stream().mapToDouble(CartItem::quantity).sum(),
                response.items()

        );

    }
}

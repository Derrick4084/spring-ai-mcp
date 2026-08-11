package rag.mcp.mcp_server.clients;


import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import rag.mcp.mcp_server.components.TokenGenerator;
import rag.mcp.mcp_server.enums.UserType;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShoppingCartApiClient {

    private final RestClient restClient;
    private final TokenGenerator tokenGenerator;

    public record CartRequest(
            String email
    ) {
    }

    public record CartResponse(
            String customerEmail,
            BigDecimal totalAmount,
            List<CartItem> items
    ){}

    public record CartItem(
            String name,
            Double quantity,
            BigDecimal total
    ) {
    }



    public ShoppingCartApiClient(RestClient.Builder restClient, TokenGenerator tokenGenerator) {
        this.restClient = restClient.baseUrl("http://localhost:8079").build();
        this.tokenGenerator = tokenGenerator;
    }


    public CartResponse getCartByEmail(String email) {

        String token = tokenGenerator.generate(
                "agent@example.com",
                "abc123",
                UserType.USER
        );

        return restClient.get().uri("/cart/{email}", email)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .body(CartResponse.class);

    }
}

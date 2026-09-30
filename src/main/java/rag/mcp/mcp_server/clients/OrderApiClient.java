package rag.mcp.mcp_server.clients;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import rag.mcp.mcp_server.components.TokenGenerator;
import rag.mcp.mcp_server.enums.UserType;
import rag.mcp.mcp_server.records.OrderResponse;


@Service
public class OrderApiClient {

    private final RestClient restClient;
    private final TokenGenerator tokenGenerator;


    public OrderApiClient(RestClient restClient, TokenGenerator tokenGenerator) {
        this.restClient = restClient;
        this.tokenGenerator = tokenGenerator;
    }


    public OrderResponse getOrderByReference(String reference){

        String token = tokenGenerator.generate(
                "agent@example.com",
                "abc12345",
                UserType.USER
        );

        return restClient.get().uri(
                "/order/reference/{reference}", reference)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve().body(OrderResponse.class);

    }
}

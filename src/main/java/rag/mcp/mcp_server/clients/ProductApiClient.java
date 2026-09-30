package rag.mcp.mcp_server.clients;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import rag.mcp.mcp_server.components.TokenGenerator;
import rag.mcp.mcp_server.enums.UserType;
import rag.mcp.mcp_server.records.ProductResponse;


@Service
public class ProductApiClient {


    private final RestClient restClient;

    private final TokenGenerator tokenGenerator;

    public ProductApiClient(RestClient restClient, TokenGenerator tokenGenerator) {
        this.restClient = restClient;
        this.tokenGenerator = tokenGenerator;
    }

    public ProductResponse getById(Long id) {

        String token = tokenGenerator.generate(
                "agent@example.com",
                "abc12345",
                UserType.USER
        );

        return restClient.get()
                .uri("/product/{id}", id)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .body(ProductResponse.class);
    }


    public ProductResponse getByName(String name) {

        String token = tokenGenerator.generate(
                "agent@example.com",
                "abc12345",
                UserType.USER
        );

        return restClient.get().uri(uriBuilder ->
                        uriBuilder.path("/product")
                                .queryParam("name", name)
                                .build())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .body(ProductResponse.class);
    }

}

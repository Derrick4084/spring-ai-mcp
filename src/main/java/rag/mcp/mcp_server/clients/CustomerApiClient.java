package rag.mcp.mcp_server.clients;


import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import rag.mcp.mcp_server.components.TokenGenerator;
import rag.mcp.mcp_server.enums.UserType;
import rag.mcp.mcp_server.records.CustomerResponse;


@Service
public class CustomerApiClient {

    private final RestClient restClient;
    private final TokenGenerator tokenGenerator;


    public CustomerApiClient(RestClient.Builder restClient, TokenGenerator tokenGenerator) {
        this.restClient = restClient.baseUrl("http://localhost:8079").build();
        this.tokenGenerator = tokenGenerator;
    }


    public CustomerResponse getCustomerByEmail(String email) {

        String token = tokenGenerator.generate(
                "agent@example.com",
                "abc12345",
                UserType.USER
        );

        return restClient.get().uri("/customer/email/{email}", email)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .body(CustomerResponse.class);
    }


    public CustomerResponse getCustomerById(Long id) {
        String token = tokenGenerator.generate(
                "agent@example.com",
                "abc12345",
                UserType.USER
        );

        return restClient.get().uri("/customer/{id}", id)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .body(CustomerResponse.class);
    }

}

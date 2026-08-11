package rag.mcp.mcp_server.components;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import rag.mcp.mcp_server.enums.UserType;

@Component
public class TokenGenerator {


    private final RestClient restClient;

    public TokenGenerator(RestClient.Builder restClient) {
        this.restClient = restClient.baseUrl("http://localhost:8079").build();
    }

    private record LoginRequest(
            String email,
            String password
    ){ }

    public String generate(String email, String password, UserType type){

        LoginRequest request = new LoginRequest(email, password);
        return restClient.post().uri("/{type}/authenticate", type.getValue())
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(String.class);
    }
}




package rag.mcp.mcp_server.clients;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestClient;

import java.net.URI;

@Configuration
public class CustomRestClient {

    @Bean(name = "restClient")
    @Profile({"home", "remote"})
    RestClient remoteRestClient(){
        return RestClient.create(
                URI.create("http://localhost:8079")
        );
    }

    @Bean(name = "restClient")
    @Profile("prod")
    RestClient prodRestClient(){
        return RestClient.create(
                URI.create("http://ecomm-app-svc:8079")
        );
    }

}

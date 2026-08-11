package rag.mcp.mcp_server.tools;


import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;
import rag.mcp.mcp_server.clients.CustomerApiClient;
import rag.mcp.mcp_server.records.CustomerRequestByEmail;
import rag.mcp.mcp_server.records.CustomerRequestById;
import rag.mcp.mcp_server.records.CustomerResponse;

@Component
public class CustomerTool {

    private final CustomerApiClient customerApiClient;

    public CustomerTool(CustomerApiClient customerApiClient) {
        this.customerApiClient = customerApiClient;
    }


    @Tool(
            name = "getCustomerByEmail",
            description = "Retrieves a customer by its email"
    )
    public CustomerResponse getCustomerByEmail(@ToolParam(description = "Customer email address") String email) {

        return customerApiClient.getCustomerByEmail(email);
    }

    @Tool(
            name = "getCustomerById",
            description = "Retrieves a customer by its id"
    )
    public CustomerResponse getCustomerById(@ToolParam(description = "Customer id") Long id) {

        return customerApiClient.getCustomerById(id);


    }



}

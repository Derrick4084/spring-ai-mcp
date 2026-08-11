package rag.mcp.mcp_server.tools;


import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;
import rag.mcp.mcp_server.clients.OrderApiClient;
import rag.mcp.mcp_server.records.OrderResponse;

@Component
public class OrderTool {

    private final OrderApiClient orderApiClient;


    public OrderTool(OrderApiClient orderApiClient) {
        this.orderApiClient = orderApiClient;
    }


    @Tool(
            name = "getOrderByReference",
            description = "Retrieves an order by its reference number"
    )
    public OrderResponse getByRef(@ToolParam(description = "Order reference number")String reference){

        return orderApiClient.getOrderByReference(reference);
    }


}

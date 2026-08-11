package rag.mcp.mcp_server.records;

import rag.mcp.mcp_server.clients.OrderApiClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        String reference,
        String status,
        BigDecimal totalAmount,
        String paymentMethod,
        LocalDateTime orderDate,
        String customerEmail,
        List<OrderLineResponse> products
) {
}

package rag.mcp.mcp_server.records;

import java.math.BigDecimal;

public record OrderLineResponse(
        Long productId,
        String productName,
        BigDecimal price,
        double quantity,
        BigDecimal lineTotal
) {
}

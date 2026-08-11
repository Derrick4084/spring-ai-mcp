package rag.mcp.mcp_server.records;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        String description,
        Double availableQuantity,
        BigDecimal price,
        String category
) {
}

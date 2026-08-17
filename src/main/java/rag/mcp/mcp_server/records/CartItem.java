package rag.mcp.mcp_server.records;

import java.math.BigDecimal;

public record CartItem(
        String name,
        Double quantity,
        BigDecimal total
) {
}

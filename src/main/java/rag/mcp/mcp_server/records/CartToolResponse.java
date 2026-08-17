package rag.mcp.mcp_server.records;

import java.math.BigDecimal;
import java.util.List;

public record CartToolResponse(
        String customerEmail,
        BigDecimal totalAmount,
        Integer productCount,
        Double itemCount,
        List<CartItem> items

) {
}

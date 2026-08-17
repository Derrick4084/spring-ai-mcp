package rag.mcp.mcp_server.records;

import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NonNull;
import rag.mcp.mcp_server.clients.ShoppingCartApiClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public record CartResponse(
        String customerEmail,
        BigDecimal totalAmount,
        List<CartItem> items

) {
}


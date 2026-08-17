package rag.mcp.mcp_server.records;

import java.util.List;

public record CustomerResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        List<CustomerResponseAddress> addresses
) {
}

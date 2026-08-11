package rag.mcp.mcp_server.records;

public record CustomerResponseAddress(
        String houseNumber,
        String street,
        String city,
        String state,
        String zipCode
) {
}

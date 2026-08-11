package rag.mcp.mcp_server.enums;

import lombok.Getter;

@Getter
public enum UserType {

    USER("user");

    private final String value;

    UserType(String value) {
        this.value = value;
    }

}

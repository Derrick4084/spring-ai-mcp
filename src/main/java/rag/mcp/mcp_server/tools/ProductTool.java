package rag.mcp.mcp_server.tools;


import org.jspecify.annotations.NonNull;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;
import rag.mcp.mcp_server.entities.Product;
import rag.mcp.mcp_server.services.ProductService;

import java.math.BigDecimal;

@Component
public class ProductTool {

    private final ProductService productService;

    public ProductTool(ProductService productService) {
        this.productService = productService;
    }

    public record ProductRequestById(
            Long productId
    ) {}

    public record ProductRequestByName(
            String name
    ) {}

    public record ProductResponse(
            Long id,
            String name,
            String description,
            Double qty,
            BigDecimal price,
            String categoryName
    ) {}

    @Tool(
            name = "getProductById",
            description = "Retrieves a product by its numeric ID"
    )
    public ProductResponse getProductById(
            @NonNull ProductRequestById productId) {

        return productService
                .getProductById(productId.productId())
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getAvailableQuantity(),
                        product.getPrice(),
                        product.getCategory().getName()
                ))
                .orElseThrow(() ->
                        new RuntimeException(
                                "Product not found: " +
                                        productId.productId()
                        )
                );
    }

    @Tool(
            name = "getProductByName",
            description = "Retrieves a product by its exact name"
    )
    public ProductResponse getProductByName(
            @NonNull ProductRequestByName name) {

        Product product = productService
                .getProductByName(name.name());

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getName()
        );
    }
}

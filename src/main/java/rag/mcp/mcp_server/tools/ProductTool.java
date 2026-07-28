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


    public record ProductRequestById(Long productId) {}
    public record ProductRequestByName(String name) {}
    public record ProductResponse(
            Long id,
            String name,
            String description,
            Double qty,
            BigDecimal price
    ) {}

    @Tool(
            name = "getProductById",
            description = "Retrieves a product by its ID"
    )
    public ProductResponse getProductById(ProductTool.ProductRequestById productRequest) {
        return productService.getProductById(productRequest.productId())
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getAvailableQuantity(),
                        product.getPrice()
                ))
                .orElseThrow(() ->
                        new RuntimeException(
                                "Product not found: " + productRequest.productId()
                        )
                );
    }


    @Tool(
            name = "getProductByName",
            description = "Retrieves a product by its name"
    )
    public ProductResponse getProductByName(ProductTool.ProductRequestByName productRequest) {

        Product product = productService.getProductByName(productRequest.name());

        return new ProductResponse(
                product.getId(),
                productRequest.name(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice()
        );

    }
}

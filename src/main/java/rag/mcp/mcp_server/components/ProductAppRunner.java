package rag.mcp.mcp_server.components;

import org.jspecify.annotations.NonNull;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import rag.mcp.mcp_server.entities.Product;
import rag.mcp.mcp_server.repositories.ProductRepository;

import java.math.BigDecimal;

//@Component
public class ProductAppRunner implements ApplicationRunner {

    private final ProductRepository productRepository;

    public ProductAppRunner(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(@NonNull ApplicationArguments args) throws Exception {

        Product product1 = Product.builder()
                .name("Mechanical Keyboard 1")
                .description("Mechanical keyboard with RGB lighting")
                .availableQuantity(10)
                .price(BigDecimal.valueOf(99.99))
                .build();

        Product product2 = Product.builder()
                .name("4K Monitor 1")
                .description("27-inch IPS monitor with 4K resolution")
                .availableQuantity(30)
                .price(BigDecimal.valueOf(399.99))
                .build();

        Product product3 = Product.builder()
                .name("Curved OLED Gaming Screen 1")
                .description("Curved OLED gaming screen with 240Hz refresh rate")
                .availableQuantity(15)
                .price(BigDecimal.valueOf(799.99))
                .build();

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

    }
}

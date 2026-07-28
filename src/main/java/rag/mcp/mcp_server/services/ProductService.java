package rag.mcp.mcp_server.services;


import org.springframework.stereotype.Service;
import rag.mcp.mcp_server.entities.Product;
import rag.mcp.mcp_server.repositories.ProductRepository;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

    public Product getProductByName(String name) {
        return productRepository.getProductByName(name).orElseThrow(
                ()-> new RuntimeException("Product not found")
        );
    }
}

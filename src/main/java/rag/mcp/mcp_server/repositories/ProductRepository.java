package rag.mcp.mcp_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rag.mcp.mcp_server.entities.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> getProductByName(String name);

}

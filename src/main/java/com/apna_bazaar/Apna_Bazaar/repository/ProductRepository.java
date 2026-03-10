package com.apna_bazaar.Apna_Bazaar.repository;

import com.apna_bazaar.Apna_Bazaar.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    boolean existsByName(@NotBlank(message = "Product name is required") @Size(max = 50, message = "Product name must be at most 50 characters") String name);
}

package com.apna_bazaar.Apna_Bazaar.controller;

import com.apna_bazaar.Apna_Bazaar.payload.request.ProductRequestDTO;
import com.apna_bazaar.Apna_Bazaar.payload.response.ProductResponseDTO;
import com.apna_bazaar.Apna_Bazaar.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // ----------- ADMIN -----------

    @PostMapping("admin/products")
    ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO){
        ProductResponseDTO response = productService.createProduct(productRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
//    ProductResponseDTO updateProduct(Long productId, ProductRequestDTO productRequestDTO);
//    void deleteProduct(Long productId);
//    ProductResponseDTO getProductById(Long productId);
}

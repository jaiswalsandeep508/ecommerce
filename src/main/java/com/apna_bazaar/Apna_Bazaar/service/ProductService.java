package com.apna_bazaar.Apna_Bazaar.service;

import com.apna_bazaar.Apna_Bazaar.payload.request.ProductRequestDTO;
import com.apna_bazaar.Apna_Bazaar.payload.response.ProductResponseDTO;

public interface ProductService {
    ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);
    ProductResponseDTO updateProduct(Long productId, ProductRequestDTO productRequestDTO);
    void deleteProduct(Long productId);
    ProductResponseDTO getProductById(Long productId);
}

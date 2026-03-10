package com.apna_bazaar.Apna_Bazaar.service.Impl;

import com.apna_bazaar.Apna_Bazaar.exception.ResourceAlreadyExistsException;
import com.apna_bazaar.Apna_Bazaar.exception.ResourceNotExistException;
import com.apna_bazaar.Apna_Bazaar.model.Category;
import com.apna_bazaar.Apna_Bazaar.payload.request.ProductRequestDTO;
import com.apna_bazaar.Apna_Bazaar.payload.response.ProductResponseDTO;
import com.apna_bazaar.Apna_Bazaar.repository.CategoryRepository;
import com.apna_bazaar.Apna_Bazaar.repository.ProductRepository;
import com.apna_bazaar.Apna_Bazaar.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {

        if(productRepository.existsByName(productRequestDTO.getName())){
            throw new ResourceAlreadyExistsException(
                    "Product already exists with name " + productRequestDTO.getName());
        }
        Category findCategory = getCategoryIfExists(productRequestDTO.getCategoryId());

        return null;
    }

    @Override
    public ProductResponseDTO updateProduct(Long productId, ProductRequestDTO productRequestDTO) {
        return null;
    }

    @Override
    public void deleteProduct(Long productId) {

    }

    @Override
    public ProductResponseDTO getProductById(Long productId) {
        return null;
    }

    private Category getCategoryIfExists(Long categoryId){
        return categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotExistException("Category not exist with id " + categoryId));
    }
}

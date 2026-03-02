package com.apna_bazaar.Apna_Bazaar.service;

import com.apna_bazaar.Apna_Bazaar.exception.ResourceAlreadyExistsException;
import com.apna_bazaar.Apna_Bazaar.model.Category;
import com.apna_bazaar.Apna_Bazaar.payload.request.CategoryRequestDTO;
import com.apna_bazaar.Apna_Bazaar.payload.response.CategoryResponseDTO;
import com.apna_bazaar.Apna_Bazaar.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;


    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO) {
        categoryExistsByName(categoryRequestDTO.getName());
        Category category =modelMapper.map(categoryRequestDTO, Category.class);
        Category savedCategory = categoryRepository.save(category);

        return modelMapper.map(savedCategory,CategoryResponseDTO.class);
    }

    @Override
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO categoryRequestDTO) {
        return null;
    }

    @Override
    public void deleteCategory(Long id) {

    }

    @Override
    public CategoryResponseDTO getCategoryById(Long id) {
        return null;
    }

    //    ---------------------------------- Helper methods -------------------------------------------
    void categoryExistsByName(String name){
        if(categoryRepository.existsByNameIgnoreCase(name)){
            throw new ResourceAlreadyExistsException("Category already exists with categoryName " + name);
        }
    }
}

package com.apna_bazaar.Apna_Bazaar.service;

import com.apna_bazaar.Apna_Bazaar.exception.ResourceAlreadyExistsException;
import com.apna_bazaar.Apna_Bazaar.exception.ResourceNotExistException;
import com.apna_bazaar.Apna_Bazaar.model.Category;
import com.apna_bazaar.Apna_Bazaar.payload.request.CategoryRequestDTO;
import com.apna_bazaar.Apna_Bazaar.payload.response.CategoryResponseDTO;
import com.apna_bazaar.Apna_Bazaar.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;


    @Override
    @Transactional
    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO) {
        categoryExistsByName(categoryRequestDTO.getName());
        Category category =modelMapper.map(categoryRequestDTO, Category.class);
        Category savedCategory = categoryRepository.save(category);

        return modelMapper.map(savedCategory,CategoryResponseDTO.class);
    }

    @Override
    @Transactional
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO categoryRequestDTO) {
       Category existingCategory = findCategoryById(id);
       String existingName = existingCategory.getName();
       String newName = categoryRequestDTO.getName();
       if(!existingName.equalsIgnoreCase(newName)){
           categoryExistsByName(newName);
           existingCategory.setName(newName);
       }
       existingCategory.setDescription(categoryRequestDTO.getDescription());
       Category updatedCategory = categoryRepository.save(existingCategory);

       return modelMapper.map(updatedCategory,CategoryResponseDTO.class);
    }

    @Override
    public void deleteCategory(Long id) {

    }

    @Override
    public CategoryResponseDTO getCategoryById(Long id) {
        return null;
    }

    //    ---------------------------------- Helper methods -------------------------------------------
    private void categoryExistsByName(String name){
        if(categoryRepository.existsByNameIgnoreCase(name)){
            throw new ResourceAlreadyExistsException("Category already exists with categoryName " + name);
        }
    }

    private Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotExistException("Category not exist with categoryId " + id));
    }
}

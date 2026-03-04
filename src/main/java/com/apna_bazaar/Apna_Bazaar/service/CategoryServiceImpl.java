package com.apna_bazaar.Apna_Bazaar.service;

import com.apna_bazaar.Apna_Bazaar.exception.ResourceAlreadyExistsException;
import com.apna_bazaar.Apna_Bazaar.exception.ResourceNotExistException;
import com.apna_bazaar.Apna_Bazaar.model.Category;
import com.apna_bazaar.Apna_Bazaar.payload.request.CategoryRequestDTO;
import com.apna_bazaar.Apna_Bazaar.payload.response.CategoryResponseDTO;
import com.apna_bazaar.Apna_Bazaar.payload.response.CategoryResponsePageDTO;
import com.apna_bazaar.Apna_Bazaar.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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
    @Transactional
    public void deleteCategory(Long id) {
       Category findCategory = findCategoryById(id);
       categoryRepository.delete(findCategory);
    }

    @Override
    public CategoryResponseDTO getCategoryById(Long id) {
        return modelMapper.map(findCategoryById(id),CategoryResponseDTO.class);
    }

    @Override
    public CategoryResponsePageDTO getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc")
                ?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        List<CategoryResponseDTO> categoryResponseDTOList = categoryPage.getContent()
                .stream()
                .map(category -> modelMapper.map(category,CategoryResponseDTO.class))
                .toList();

        return categoryResponseDTO_To_CategoryPageResponseDTO(categoryResponseDTOList,categoryPage);
    }

    private CategoryResponsePageDTO categoryResponseDTO_To_CategoryPageResponseDTO(List<CategoryResponseDTO> categoryResponseDTOList, Page<Category> categoryPage) {
        CategoryResponsePageDTO categoryResponsePageDTO = new CategoryResponsePageDTO();
        categoryResponsePageDTO.setCategories(categoryResponseDTOList);
        categoryResponsePageDTO.setPageNumber(categoryPage.getNumber());
        categoryResponsePageDTO.setPageSize(categoryPage.getSize());
        categoryResponsePageDTO.setTotalPages(categoryPage.getTotalPages());
        categoryResponsePageDTO.setTotalElements(categoryPage.getTotalElements());
        categoryResponsePageDTO.setLast(categoryPage.isLast());

        return categoryResponsePageDTO;
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

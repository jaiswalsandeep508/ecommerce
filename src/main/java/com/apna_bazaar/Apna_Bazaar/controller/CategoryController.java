package com.apna_bazaar.Apna_Bazaar.controller;

import com.apna_bazaar.Apna_Bazaar.payload.request.CategoryRequestDTO;
import com.apna_bazaar.Apna_Bazaar.payload.response.CategoryResponseDTO;
import com.apna_bazaar.Apna_Bazaar.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/admin/categories")
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody @Valid CategoryRequestDTO categoryRequestDTO){
        CategoryResponseDTO categoryResponseDTO = categoryService.createCategory(categoryRequestDTO);
        return new ResponseEntity<>(categoryResponseDTO,HttpStatus.CREATED);
    }

    @PutMapping("/admin/categories/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable Long id,@RequestBody @Valid CategoryRequestDTO categoryRequestDTO){
        CategoryResponseDTO categoryResponseDTO = categoryService.updateCategory(id,categoryRequestDTO);
        return new ResponseEntity<>(categoryResponseDTO,HttpStatus.OK);
    }

    @DeleteMapping("/admin/categories/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Delete Category successfully");
    }

    // ----------- PUBLIC -----------

    @GetMapping("/public/categories/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }
}

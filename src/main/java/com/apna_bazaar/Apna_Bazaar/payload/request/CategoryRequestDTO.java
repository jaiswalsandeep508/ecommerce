package com.apna_bazaar.Apna_Bazaar.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryRequestDTO {


    @Size(max = 50)
    @NotBlank(message = "Category Name should not be blank")
    private String name;

    @NotBlank(message = "Category Description Should not be blank")
    private String description;
}

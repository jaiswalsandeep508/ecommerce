package com.apna_bazaar.Apna_Bazaar.payload.response;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponsePageDTO {
    private List<CategoryResponseDTO> categories;
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private long totalElements;
    private boolean last;
}

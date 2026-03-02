package com.apna_bazaar.Apna_Bazaar.repository;

import com.apna_bazaar.Apna_Bazaar.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    boolean existsByNameIgnoreCase( String newName);
}

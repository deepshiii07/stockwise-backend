package com.stockwise.backend.repository;

import com.stockwise.backend.model.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //custom queries here

     List<Product> findByQuantityLessThan(int quantity);
     
     List<Product> findByExpirationDateBefore(java.time.LocalDate date);
     
     @Query("SELECT DISTINCT p.category FROM Product p")
     List<String> findDistinctCategories();

     List<Product> findByCategoryIgnoreCase(String category);
     
  // Search products by name
     List<Product> findByNameContainingIgnoreCase(String keyword);

     // Search categories by keyword
     @Query("SELECT DISTINCT p.category FROM Product p WHERE LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))")
     List<String> searchCategoriesByKeyword(@Param("keyword") String keyword);
     
     //Search products in category
     List<Product> findByCategoryIgnoreCaseAndNameContainingIgnoreCase(String category, String keyword);

     //supplier filter
     List<Product> findBySupplierIgnoreCase(String supplier);


}
package com.stockwise.backend.controller;

import com.stockwise.backend.dto.DeleteAllRequest;
import com.stockwise.backend.model.Product;
import com.stockwise.backend.repository.ProductRepository;
import java.util.List;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    
    //Takes new Product and saves it in Database
    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }
    
    //Get all products
    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    //Find a product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
    	        .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }
    
    //update product with ID
    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setQuantity(updatedProduct.getQuantity());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setSupplier(updatedProduct.getSupplier());
        existingProduct.setExpirationDate(updatedProduct.getExpirationDate());

        return productRepository.save(existingProduct);
    }
    
    //delete product by ID
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }

        productRepository.deleteById(id);
        return "Product deleted successfully!";
    }
    
    //Delete all users 
    @DeleteMapping("/delete/all")
    public String deleteAllProducts(@RequestBody DeleteAllRequest request) {
        if (!"DELETE_ALL".equals(request.getConfirm())) {
            throw new RuntimeException("Dangerous operation. Type DELETE_ALL to confirm.");
        }

        productRepository.deleteAll();
        return "All products have been deleted from the inventory.";
    }
    
    //get products less than given threshold
    @GetMapping("/low-stock")
    public List<Product> getLowStockProducts(@RequestParam int threshold) {
        return productRepository.findByQuantityLessThan(threshold);
    }
    
    //Get expired date 
    @GetMapping("/expired")
    public List<Product> getExpiredProducts() {
        LocalDate today = LocalDate.now();
        return productRepository.findByExpirationDateBefore(today);
    }
    
    //Get all categories
    @GetMapping("/categories")
    public List<String> getAllCategories() {
        return productRepository.findDistinctCategories();
    }

    //Get Product by opening category
    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return productRepository.findByCategoryIgnoreCase(category);
    }
    
    //Search products in the main product category page
    @GetMapping("/search")
    public Map<String, Object> searchProductsAndCategories(@RequestParam String keyword) {
        List<Product> matchingProducts = productRepository.findByNameContainingIgnoreCase(keyword);
        List<String> matchingCategories = productRepository.searchCategoriesByKeyword(keyword);

        Map<String, Object> response = new HashMap<>();
        response.put("products", matchingProducts);
        response.put("categories", matchingCategories);

        return response;
    }
    
    //Search bar inside the categories
    @GetMapping("/category/{category}/search")
    public List<Product> searchProductsInCategory(
        @PathVariable String category,
        @RequestParam String keyword
    ) {
        return productRepository.findByCategoryIgnoreCaseAndNameContainingIgnoreCase(category, keyword);
    }

    //supplier filter
    @GetMapping("/supplier/{supplier}")
    public List<Product> getProductsBySupplier(@PathVariable String supplier) {
        return productRepository.findBySupplierIgnoreCase(supplier);
    }


    
}

package com.stockwise.backend.service;

import com.stockwise.backend.dto.DeleteAllRequest;
import com.stockwise.backend.model.Product;
import com.stockwise.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    // CRUD + Filters will go here
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
    
    //get all products 
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //get product by ID
    public Product getProductById(Long id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    //update product
    public Product updateProduct(Long id, Product updatedProduct) {
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

    //delete products
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
    	if (!productRepository.existsById(id)) {
    		throw new RuntimeException("Product not found with id: " + id);
    	}
    	productRepository.deleteById(id);
    	return "Product deleted successfully!";
    }

    //delete all products
    @DeleteMapping("/delete/all")
    public String deleteAllProducts(@RequestBody DeleteAllRequest request) {
    	if (!"DELETE_ALL".equals(request.getConfirm())) {
    		throw new RuntimeException("Dangerous operation. Type DELETE_ALL to confirm.");
    	}
    	productRepository.deleteAll();
    	return "All products have been deleted from the inventory.";
    }


}

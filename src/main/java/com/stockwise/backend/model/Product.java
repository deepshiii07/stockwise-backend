package com.stockwise.backend.model;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDate;


@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private int quantity;
	private double price;
	private String category;
	private String supplier;
	private LocalDate expirationDate;
	
	public Product() {
	}
	
	public Product(String name, int quantity,double price, String category, String supplier, LocalDate expirationDate) {
		this.name=name;
		this.quantity=quantity;
		this.price=price;
		this.category=category;
		this.supplier=supplier;
		this.expirationDate=expirationDate;
	}
	
	//Getter for ID
	public Long getId() {
		return id;
	}
	
	//Getter and Setter for Name
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	//Getter and Setter for Quantity
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity=quantity;
	}
	
	//Getter and Setter for Price
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price=price;
	}
	
	//Getter and Setter for Category
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category=category;
	}
	
	//Getter and Setter for Supplier
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier=supplier;
	}
	
	//Getter and setter for Expiration Date	
	public LocalDate getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate=expirationDate;
	}
	
}

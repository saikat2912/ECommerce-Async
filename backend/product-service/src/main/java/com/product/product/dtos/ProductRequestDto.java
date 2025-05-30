package com.product.product.dtos;

import com.product.product.enums.ProductCategory;
import com.product.product.model.Product;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProductRequestDto {
    private String title;
    private String description;
    private ProductCategory category;
    private String price;
    private String imageUrl;
    private int quantity;
    private LocalDateTime expiryDate;


    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product productDtoToProduct(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setProductImage(productRequestDto.imageUrl);
        product.setProductName(productRequestDto.title);
        product.setQuantity(productRequestDto.quantity);

        return product;
    }

}

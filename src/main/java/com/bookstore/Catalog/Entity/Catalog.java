package com.bookstore.Catalog.Entity;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Catalog {
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    @Column(name = "uuid")
//    private String id;

    @Id
    @Column(name = "uuid")
    @Type(type = "uuid-char")
    private UUID id = UUID.randomUUID();
    @Column(name = "CODE", unique = true)
    @NotEmpty(message = "Product code must not be null/empty")

    private String code;
    @NotEmpty(message = "Product name must not be null/empty")
    private String name;
    private String description;
    private String image_url;
    @NotNull(message = "Product price must not be null/empty")
    @DecimalMin("0.1")
    private BigDecimal price;
    private BigDecimal discount;
    private double salePrice;

    public UUID getId() {
        return id;
    }

    public void setId(String id) {
        this.id = UUID.fromString(id);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }
}

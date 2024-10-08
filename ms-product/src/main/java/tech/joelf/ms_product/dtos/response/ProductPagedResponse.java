package tech.joelf.ms_product.dtos.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductPagedResponse {
    private Long id;
    private String name;
    private String description;
    private String imgUrl;
    private Double price;

    public ProductPagedResponse() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Double getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

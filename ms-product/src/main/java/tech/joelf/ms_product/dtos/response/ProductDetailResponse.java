package tech.joelf.ms_product.dtos.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductDetailResponse {
    private Long id;
    private String name;
    private String description;
    private String imgUrl;
    private Double price;
    private List<CategoryResponse> categories = new ArrayList<>();

    public ProductDetailResponse() {
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

    public List<CategoryResponse> getCategories() {
        return Collections.unmodifiableList(categories);
    }

    public void setCategories(List<CategoryResponse> categories) {
        this.categories = categories;
    }
}

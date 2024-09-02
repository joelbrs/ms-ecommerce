package tech.joelf.ms_relations.dtos.request;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AssociateProductsToCategoryRequest {
    private Long categoryId;
    private List<Long> products = new ArrayList<>();

    public AssociateProductsToCategoryRequest() {
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<Long> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public void setProducts(List<Long> products) {
        this.products = products;
    }
}

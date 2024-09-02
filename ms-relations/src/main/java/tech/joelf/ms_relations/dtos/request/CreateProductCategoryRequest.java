package tech.joelf.ms_relations.dtos.request;

import javax.validation.constraints.NotNull;

public class CreateProductCategoryRequest {
    @NotNull
    private Long categoryId;

    @NotNull
    private Long productId;

    public CreateProductCategoryRequest() {
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}

package tech.joelf.ms_product.dtos.response;

public class ProductCategoryResponse {
    private Long id;
    private Long productId;
    private Long categoryId;

    public ProductCategoryResponse() {
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}

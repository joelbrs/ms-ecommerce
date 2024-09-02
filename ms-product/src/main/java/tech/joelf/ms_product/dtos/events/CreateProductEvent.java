package tech.joelf.ms_product.dtos.events;

import java.util.ArrayList;
import java.util.List;

public class CreateProductEvent {
    private Long productId;
    private List<Long> categories = new ArrayList<>();

    public CreateProductEvent() {
    }

    public CreateProductEvent(Long productId, List<Long> categories) {
        this.productId = productId;
        this.categories = categories;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public List<Long> getCategories() {
        return categories;
    }

    public void setCategories(List<Long> categories) {
        this.categories = categories;
    }
}

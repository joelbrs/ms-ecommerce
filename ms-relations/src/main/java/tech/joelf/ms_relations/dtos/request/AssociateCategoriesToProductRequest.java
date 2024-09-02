package tech.joelf.ms_relations.dtos.request;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AssociateCategoriesToProductRequest {
    private Long productId;
    private List<Long> categories = new ArrayList<>();

    public AssociateCategoriesToProductRequest() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public List<Long> getCategories() {
        return Collections.unmodifiableList(categories);
    }

    public void setCategories(List<Long> categories) {
        this.categories = categories;
    }
}

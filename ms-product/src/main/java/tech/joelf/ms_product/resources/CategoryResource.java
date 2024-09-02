package tech.joelf.ms_product.resources;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import tech.joelf.ms_product.dtos.response.CategoryResponse;

@FeignClient(name = "ms-category")
public interface CategoryResource {

    @GetMapping("/product/{productId}")
    public List<CategoryResponse> findCategoriesByProduct(@PathVariable Long productId);

    @GetMapping("/{id}")
    public CategoryResponse findById(@PathVariable Long id);
}

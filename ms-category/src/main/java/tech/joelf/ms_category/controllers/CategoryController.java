package tech.joelf.ms_category.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import tech.joelf.ms_category.dtos.request.CreateCategoryRequest;
import tech.joelf.ms_category.dtos.request.UpdateCategoryRequest;
import tech.joelf.ms_category.dtos.response.CategoryResponse;
import tech.joelf.ms_category.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody @Valid CreateCategoryRequest request) {
        return ResponseEntity.ok(categoryService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id,
            @RequestBody @Valid UpdateCategoryRequest request) {
        return ResponseEntity.ok(categoryService.update(id, request));
    }

    @GetMapping
    public ResponseEntity<Page<CategoryResponse>> findCategories(Pageable pageable,
            @RequestParam(required = false) String name) {
        return ResponseEntity.ok(categoryService.findCategories(pageable, name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<CategoryResponse>> findCategoriesByProduct(
            @PathVariable Long productId) {
        return ResponseEntity.ok(categoryService.findCategoriesByProduct(productId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

package tech.joelf.ms_relations.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.joelf.ms_relations.dtos.request.AssociateCategoriesToProductRequest;
import tech.joelf.ms_relations.dtos.request.AssociateProductsToCategoryRequest;
import tech.joelf.ms_relations.dtos.request.CreateProductCategoryRequest;
import tech.joelf.ms_relations.dtos.response.ProductCategoryDetailResponse;
import tech.joelf.ms_relations.services.ProductCategoryService;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/product-category")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @PostMapping
    public ResponseEntity<ProductCategoryDetailResponse> create(
            @RequestBody @Valid CreateProductCategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productCategoryService.create(request));
    }

    @PostMapping("/associate-products")
    public ResponseEntity<Void> associateProductsToCategory(
            @RequestBody @Valid AssociateProductsToCategoryRequest request) {
        productCategoryService.associateProductsToCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/associate-categories")
    public ResponseEntity<Void> associateCategoriesToProduct(
            @RequestBody @Valid AssociateCategoriesToProductRequest request) {
        productCategoryService.associateCategoriesToProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductCategoryDetailResponse>> findByCategoryId(Long categoryId) {
        return ResponseEntity.ok(productCategoryService.findByCategoryId(categoryId));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductCategoryDetailResponse>> findByProductId(Long productId) {
        return ResponseEntity.ok(productCategoryService.findByProductId(productId));
    }
}

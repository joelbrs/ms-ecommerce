package tech.joelf.ms_product.controllers;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import tech.joelf.ms_product.dtos.request.CreateProductRequest;
import tech.joelf.ms_product.dtos.request.UpdateProductRequest;
import tech.joelf.ms_product.dtos.response.ProductResponse;
import tech.joelf.ms_product.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody @Valid CreateProductRequest request) {
        return ResponseEntity.ok(productService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id,
            @RequestBody @Valid UpdateProductRequest request) {
        return ResponseEntity.ok(productService.update(id, request));
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> findProducts(Pageable pageable,
            @RequestParam(required = false) String name) {
        return ResponseEntity.ok(productService.findProducts(pageable, name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

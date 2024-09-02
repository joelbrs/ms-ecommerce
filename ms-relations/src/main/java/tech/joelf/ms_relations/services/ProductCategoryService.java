package tech.joelf.ms_relations.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import tech.joelf.ms_relations.dtos.request.AssociateCategoriesToProductRequest;
import tech.joelf.ms_relations.dtos.request.AssociateProductsToCategoryRequest;
import tech.joelf.ms_relations.dtos.request.CreateProductCategoryRequest;
import tech.joelf.ms_relations.dtos.response.ProductCategoryDetailResponse;
import tech.joelf.ms_relations.model.ProductCategory;
import tech.joelf.ms_relations.queues.ProductCategoryPublisher;
import tech.joelf.ms_relations.repositories.ProductCategoryRepository;

@Service
public class ProductCategoryService {

    private final ModelMapper modelMapper;
    private final ProductCategoryPublisher productCategoryPublisher;
    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryService(ProductCategoryRepository productCategoryRepository, ModelMapper modelMapper,
            ProductCategoryPublisher productCategoryPublisher) {
        this.productCategoryRepository = productCategoryRepository;
        this.modelMapper = modelMapper;
        this.productCategoryPublisher = productCategoryPublisher;
    }

    @Transactional
    public ProductCategoryDetailResponse create(CreateProductCategoryRequest request) {
        ProductCategory productCategory = productCategoryRepository
                .save(modelMapper.map(request, ProductCategory.class));

        productCategoryPublisher.sendCreateRelation(List.of(productCategory));
        return modelMapper.map(productCategory, ProductCategoryDetailResponse.class);
    }

    @Transactional
    public void associateProductsToCategory(AssociateProductsToCategoryRequest request) {
        List<ProductCategory> productCategories = request.getProducts().stream()
                .map(productId -> new ProductCategory(productId, request.getCategoryId()))
                .collect(Collectors.toList());
        productCategoryRepository.saveAll(productCategories);
        productCategoryPublisher.sendCreateRelation(productCategories);
    }

    @Transactional
    public void associateCategoriesToProduct(AssociateCategoriesToProductRequest request) {
        List<ProductCategory> productCategories = request.getCategories().stream()
                .map(categoryId -> new ProductCategory(request.getProductId(), categoryId))
                .collect(Collectors.toList());
        productCategoryRepository.saveAll(productCategories);
        productCategoryPublisher.sendCreateRelation(productCategories);
    }

    public List<ProductCategoryDetailResponse> findByCategoryId(Long categoryId) {
        List<ProductCategory> productCategories = productCategoryRepository.findByCategoryId(categoryId);
        return productCategories.stream()
                .map(productCategory -> modelMapper.map(productCategory, ProductCategoryDetailResponse.class))
                .collect(Collectors.toList());
    }

    public List<ProductCategoryDetailResponse> findByProductId(Long productId) {
        List<ProductCategory> productCategories = productCategoryRepository.findByProductId(productId);
        return productCategories.stream()
                .map(productCategory -> modelMapper.map(productCategory, ProductCategoryDetailResponse.class))
                .collect(Collectors.toList());
    }
}

package tech.joelf.ms_product.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import tech.joelf.ms_product.dtos.response.ProductCategoryResponse;
import tech.joelf.ms_product.model.ProductCategory;
import tech.joelf.ms_product.repositories.ProductCategoryRepository;

@Service
public class ProductCategoryService {

    private final ModelMapper modelMapper;
    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryService(ProductCategoryRepository productCategoryRepository, ModelMapper modelMapper) {
        this.productCategoryRepository = productCategoryRepository;
        this.modelMapper = modelMapper;
    }

    public List<ProductCategoryResponse> save(List<ProductCategory> productCategory) {
        List<ProductCategory> entity = productCategoryRepository.saveAll(productCategory);
        return entity.stream().map(pc -> modelMapper.map(pc, ProductCategoryResponse.class))
                .collect(Collectors.toList());
    }
}

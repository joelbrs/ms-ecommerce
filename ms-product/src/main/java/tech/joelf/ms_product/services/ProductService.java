package tech.joelf.ms_product.services;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tech.joelf.ms_product.dtos.request.CreateProductRequest;
import tech.joelf.ms_product.dtos.request.UpdateProductRequest;
import tech.joelf.ms_product.dtos.response.ProductDetailResponse;
import tech.joelf.ms_product.dtos.response.ProductPagedResponse;
import tech.joelf.ms_product.model.Product;
import tech.joelf.ms_product.repositories.ProductRepository;
import tech.joelf.ms_product.resources.CategoryResource;

@Service
public class ProductService {

    private final ModelMapper modelMapper;
    private final CategoryResource categoryResource;
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository, ModelMapper modelMapper,
            CategoryResource categoryResource) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryResource = categoryResource;
    }

    @Transactional
    public ProductDetailResponse create(CreateProductRequest request) {
        Product product = productRepository.save(modelMapper.map(request, Product.class));
        return modelMapper.map(product, ProductDetailResponse.class);
    }

    @Transactional
    public ProductDetailResponse update(Long id, UpdateProductRequest request) {
        Product product = productRepository.getById(id);
        BeanUtils.copyProperties(request, product, "id");

        return modelMapper.map(productRepository.save(product), ProductDetailResponse.class);
    }

    public Page<ProductPagedResponse> findProducts(Pageable pageable, String name) {
        Page<Product> products = productRepository.findProducts(pageable, name);
        return products.map(product -> modelMapper.map(product, ProductPagedResponse.class));
    }

    public ProductDetailResponse findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(RuntimeException::new);
        ProductDetailResponse response = modelMapper.map(product, ProductDetailResponse.class);

        findCategoriesByProduct(response);
        return response;
    }

    public void findCategoriesByProduct(ProductDetailResponse product) {
        product.setCategories(categoryResource.findCategoriesByProduct(product.getId()));
    }

    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException();
        }

        try {
            productRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException();
        }
    }
}

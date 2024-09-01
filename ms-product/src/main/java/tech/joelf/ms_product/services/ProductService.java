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
import tech.joelf.ms_product.dtos.response.ProductResponse;
import tech.joelf.ms_product.model.Product;
import tech.joelf.ms_product.repositories.ProductRepository;

@Service
public class ProductService {

    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public ProductResponse create(CreateProductRequest request) {
        Product product = productRepository.save(modelMapper.map(request, Product.class));
        return modelMapper.map(product, ProductResponse.class);
    }

    @Transactional
    public ProductResponse update(Long id, UpdateProductRequest request) {
        Product product = productRepository.getById(id);
        BeanUtils.copyProperties(request, product, "id");

        return modelMapper.map(productRepository.save(product), ProductResponse.class);
    }

    public Page<ProductResponse> findProducts(Pageable pageable, String name) {
        Page<Product> products = productRepository.findProducts(pageable, name);
        return products.map(product -> modelMapper.map(product, ProductResponse.class));
    }

    public ProductResponse findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(RuntimeException::new);
        return modelMapper.map(product, ProductResponse.class);
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

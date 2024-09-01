package tech.joelf.ms_category.services;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tech.joelf.ms_category.dtos.request.CreateCategoryRequest;
import tech.joelf.ms_category.dtos.request.UpdateCategoryRequest;
import tech.joelf.ms_category.dtos.response.CategoryResponse;
import tech.joelf.ms_category.model.Category;
import tech.joelf.ms_category.repositories.CategoryRepository;

@Service
public class CategoryService {

    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public CategoryResponse create(CreateCategoryRequest request) {
        Category category = categoryRepository.save(modelMapper.map(request, Category.class));
        return modelMapper.map(category, CategoryResponse.class);
    }

    @Transactional
    public CategoryResponse update(Long id, UpdateCategoryRequest request) {
        Category category = categoryRepository.getById(id);
        BeanUtils.copyProperties(request, category, "id");

        return modelMapper.map(categoryRepository.save(category), CategoryResponse.class);
    }

    public Page<CategoryResponse> findCategories(Pageable pageable, String name) {
        Page<Category> categories = categoryRepository.findCategories(pageable, name);
        return categories.map(product -> modelMapper.map(product, CategoryResponse.class));
    }

    public CategoryResponse findById(Long id) {
        Category product = categoryRepository.findById(id).orElseThrow(RuntimeException::new);
        return modelMapper.map(product, CategoryResponse.class);
    }

    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException();
        }

        try {
            categoryRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException();
        }
    }
}

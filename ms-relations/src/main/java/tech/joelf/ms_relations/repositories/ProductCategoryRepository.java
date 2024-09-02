package tech.joelf.ms_relations.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.joelf.ms_relations.model.ProductCategory;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    @Query("SELECT p FROM ProductCategory p WHERE p.categoryId = :categoryId")
    List<ProductCategory> findByCategoryId(Long categoryId);

    @Query("SELECT p FROM ProductCategory p WHERE p.productId = :productId")
    List<ProductCategory> findByProductId(Long productId);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM ProductCategory p WHERE p.categoryId = :categoryId AND p.productId = :productId")
    Boolean existsByCategoryIdAndProductId(Long categoryId, Long productId);
}

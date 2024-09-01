package tech.joelf.ms_category.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.joelf.ms_category.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE LOWER(c.name) LIKE CONCAT('%', LOWER(:name), '%')")
    Page<Category> findCategories(Pageable pageable, String name);

    @Query("SELECT c FROM Category c WHERE c.productId = :productId")
    List<Category> findCategoriesByProduct(Long productId);
}

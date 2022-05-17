package edu.uoc.epcsd.showcatalog.domain.repository;

import edu.uoc.epcsd.showcatalog.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    List<Category> findAllCategories();

    Optional<Category> findCategoryById(Long id);

    Long createCategory(Category category);

    void deleteCategory(Long id);
}

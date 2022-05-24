package edu.uoc.epcsd.showcatalog;

import edu.uoc.epcsd.showcatalog.domain.Category;
import edu.uoc.epcsd.showcatalog.domain.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class CatalogRepositoryIntegrationTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @DisplayName("Create new category in database")
    @Test
    void testCreateAndGetCategoryById() {
        Category category = new Category();
        category.setId(7L);
        category.setName("Test Category Name");
        category.setDescription("Test Category description");
        categoryRepository.createCategory(category);
        Optional<Category> categoryReturn = categoryRepository.findCategoryById(7L);
        assertThat(categoryReturn.get().getName()).isEqualTo(category.getName());
        assertThat(1L).isEqualTo(1L);
    }

}

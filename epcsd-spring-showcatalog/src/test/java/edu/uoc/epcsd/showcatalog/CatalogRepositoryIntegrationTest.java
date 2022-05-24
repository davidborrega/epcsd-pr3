package edu.uoc.epcsd.showcatalog;

import edu.uoc.epcsd.showcatalog.domain.Category;
import edu.uoc.epcsd.showcatalog.domain.repository.CategoryRepository;
import edu.uoc.epcsd.showcatalog.infrastructure.repository.jpa.CategoryEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@ContextConfiguration(classes = {CatalogRepositoryIntegrationTestConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CatalogRepositoryIntegrationTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TestEntityManager entityManager;

    @DisplayName("Create new category in database")
    @Test
    void testCreateAndGetCategoryById() {
        CategoryEntity category = new CategoryEntity();
        category.setName("Test Category Name");
        category.setDescription("Test Category description");
        entityManager.persistAndFlush(category);
        Optional<Category> categoryReturn = categoryRepository.findCategoryById(7L);
        assertThat(categoryReturn.get().getName()).isEqualTo(category.getName());
        assertThat(1L).isEqualTo(1L);
    }

}

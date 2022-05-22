package edu.uoc.epcsd.showcatalog;

import edu.uoc.epcsd.showcatalog.domain.Category;
import edu.uoc.epcsd.showcatalog.domain.repository.CategoryRepository;
import edu.uoc.epcsd.showcatalog.infrastructure.repository.jpa.SpringDataCategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@DataJpaTest
class ShowCatalogApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void test1() {
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

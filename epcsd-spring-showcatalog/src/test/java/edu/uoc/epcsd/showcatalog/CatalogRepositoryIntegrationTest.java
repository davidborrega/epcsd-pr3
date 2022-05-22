package edu.uoc.epcsd.showcatalog;

import edu.uoc.epcsd.showcatalog.domain.Category;
import edu.uoc.epcsd.showcatalog.domain.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CatalogRepositoryIntegrationTest {

    //@Autowired
    //private TestEntityManager entityManager;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testGetCategories() {
        Category category = new Category();
        category.setId(7L);
        category.setName("Test Category Name");
        category.setDescription("Test Category description");
        categoryRepository.createCategory(category);
        //entityManager.persistAndFlush(category);
        Optional<Category> categoryReturn = categoryRepository.findCategoryById(7L);
        assertThat(categoryReturn.get().getName()).isEqualTo(category.getName());
    }

}

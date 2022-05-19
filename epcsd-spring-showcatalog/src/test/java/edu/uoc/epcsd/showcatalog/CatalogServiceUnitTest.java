package edu.uoc.epcsd.showcatalog;

import edu.uoc.epcsd.showcatalog.domain.Category;
import edu.uoc.epcsd.showcatalog.domain.Show;
import edu.uoc.epcsd.showcatalog.domain.Status;
import edu.uoc.epcsd.showcatalog.domain.repository.CategoryRepository;
import edu.uoc.epcsd.showcatalog.domain.repository.ShowRepository;
import edu.uoc.epcsd.showcatalog.domain.service.CatalogService;
import edu.uoc.epcsd.showcatalog.domain.service.CatalogServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class CatalogServiceUnitTest {

    @TestConfiguration
    static class CatalogServiceTestConfig {
        @Bean
        public CatalogService catalogService() {
            return new CatalogServiceImpl();
        }
    }
    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private ShowRepository showRepository;

    @MockBean
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private CatalogService catalogService;

    private static Long DEFAULT_ID = 1L;

    private static Long DEFAULT_NOT_FOUND_ID = 2L;

    private static Long DEFAULT_CAPACITY = 100L;

    @Before
    public void setUp() {
        Mockito.when(categoryRepository.findCategoryById(DEFAULT_ID)).thenReturn(Optional.of(getCategory()));
        Mockito.when(showRepository.findShowById(DEFAULT_ID)).thenReturn(Optional.of(getShow()));
    }

    @Test
    public void testFindShowByIdFound() {
        assertThat(catalogService.findShowById(DEFAULT_ID).get().getId()).isEqualTo(getShow().getId());
    }

    @Test(expected = NoSuchElementException.class)
    public void testFindShowByIdNotFound() {
        catalogService.findShowById(DEFAULT_NOT_FOUND_ID).get();
    }

    private Category getCategory() {
        Category category = new Category();
        category.setId(DEFAULT_ID);
        category.setName("Category test name");
        category.setDescription("Category test description");
        return category;
    }

    private Show getShow() {
        Show show = new Show();
        show.setId(DEFAULT_ID);
        show.setCategory(getCategory());
        show.setName("New show");
        show.setStatus(Status.CREATED);
        show.setDescription("Random description");
        show.setCapacity(DEFAULT_CAPACITY);
        show.setDuration(Math.random());
        show.setPrice(Math.random());
        return show;
    }

}

package edu.uoc.epcsd.showcatalog;

import edu.uoc.epcsd.showcatalog.domain.Category;
import edu.uoc.epcsd.showcatalog.domain.Show;
import edu.uoc.epcsd.showcatalog.domain.Status;
import edu.uoc.epcsd.showcatalog.domain.repository.CategoryRepository;
import edu.uoc.epcsd.showcatalog.domain.repository.ShowRepository;
import edu.uoc.epcsd.showcatalog.domain.service.CatalogServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class CatalogServiceUnitTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ShowRepository showRepository;

    @Mock
    private KafkaTemplate kafkaTemplate;

    @InjectMocks
    private CatalogServiceImpl catalogService;

    private static Long DEFAULT_ID = 1L;

    private static Long DEFAULT_NOT_FOUND_ID = 2L;

    private static Long DEFAULT_CAPACITY = 100L;

    @BeforeEach
    public void setUp() {
        Mockito.when(categoryRepository.findCategoryById(DEFAULT_ID)).thenReturn(Optional.of(getCategory()));
        Mockito.when(showRepository.findShowById(DEFAULT_ID)).thenReturn(Optional.of(getShow()));
    }

    @Test
    public void testFindShowByIdFound() {
        assertThat(catalogService.findShowById(DEFAULT_ID).get().getId()).isEqualTo(getShow().getId());
    }

    @Test()
    public void testFindShowByIdNotFound() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            catalogService.findShowById(DEFAULT_NOT_FOUND_ID).get();
        });
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

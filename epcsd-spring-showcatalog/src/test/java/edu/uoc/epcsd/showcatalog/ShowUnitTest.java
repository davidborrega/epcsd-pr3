package edu.uoc.epcsd.showcatalog;

import edu.uoc.epcsd.showcatalog.domain.Category;
import edu.uoc.epcsd.showcatalog.domain.Show;
import edu.uoc.epcsd.showcatalog.domain.Status;
import edu.uoc.epcsd.showcatalog.domain.repository.CategoryRepository;
import edu.uoc.epcsd.showcatalog.domain.repository.ShowRepository;
import edu.uoc.epcsd.showcatalog.domain.service.CatalogServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(SpringRunner.class)
public class ShowUnitTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ShowRepository showRepository;

    @Mock
    private KafkaTemplate kafkaTemplate;

    //@Autowired
    @InjectMocks
    private CatalogServiceImpl catalogService;

    private static Long DEFAULT_ID = 1L;

    private static Long DEFAULT_CAPACITY = 100L;

    @Before
    public void setUp() {
        Show show = getShow();
        Mockito.when(categoryRepository.findCategoryById(anyLong())).thenReturn(Optional.of(getCategory()));
        Mockito.when(showRepository.findShowById(anyLong())).thenReturn(Optional.of(show));
        Mockito.when(showRepository.createShow(show)).thenReturn(show.getId());
    }

    @Test
    public void testShowWithoutCancelled() {
        Long showId = catalogService.createShow(getShow());
        Optional<Show> show = catalogService.findShowById(showId);
        assertThat(show.get().getStatus()).isNotEqualTo(Status.CANCELLED);
    }

    @Test
    public void testShowWithCancelled() {
        Long showId = catalogService.createShow(getShow());
        Optional<Show> show = catalogService.findShowById(showId);
        catalogService.cancelShow(showId);
        assertThat(show.get().getStatus()).isEqualTo(Status.CANCELLED);
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

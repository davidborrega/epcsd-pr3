package edu.uoc.epcsd.showcatalog;

import edu.uoc.epcsd.showcatalog.domain.Category;
import edu.uoc.epcsd.showcatalog.domain.Show;
import edu.uoc.epcsd.showcatalog.domain.Status;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class ShowUnitTest {

    private static Long DEFAULT_ID = 1L;

    private static Long DEFAULT_CAPACITY = 100L;

    @Before
    public void setUp() {

    }

    @DisplayName("Test show without cancel")
    @Test
    public void testShowWithoutCancelled() {
        assertThat(getShow().getStatus()).isNotEqualTo(Status.CANCELLED);
    }

    @DisplayName("Test show with cancel")
    @Test
    public void testShowWithCancelled() {
        Show show = getShow();
        show.cancel();
        assertThat(show.getStatus()).isEqualTo(Status.CANCELLED);
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

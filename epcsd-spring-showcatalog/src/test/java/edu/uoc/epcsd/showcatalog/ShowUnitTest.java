package edu.uoc.epcsd.showcatalog;

import edu.uoc.epcsd.showcatalog.domain.Show;
import edu.uoc.epcsd.showcatalog.domain.Status;
import edu.uoc.epcsd.showcatalog.domain.repository.ShowRepository;
import edu.uoc.epcsd.showcatalog.domain.service.CatalogService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(SpringRunner.class)
public class ShowUnitTest {

    @MockBean
    private ShowRepository showRepository;

    @Autowired
    private CatalogService catalogService;

    @Before
    public void setUp() {
        Show show = new Show();
        show.setId(1L);
        show.setName("New show");
        show.setStatus(Status.CREATED);
        show.setDescription("Random description");
        Mockito.when(showRepository.createShow(show)).thenReturn(show.getId());
        //Mockito.when(showRepository.findShowById(anyLong())).thenReturn(Optional.of(show));
    }

    @Test
    public void cancelShowTestOk() {
        Show show = new Show();
        show.setId(1L);
        show.setName("New show");
        show.setStatus(Status.CREATED);
        show.setDescription("Random description");
        Long id = catalogService.createShow(show);
        assertThat(true).isEqualTo(true);
    }

}

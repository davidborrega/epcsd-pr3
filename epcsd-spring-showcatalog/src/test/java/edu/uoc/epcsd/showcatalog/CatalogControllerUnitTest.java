package edu.uoc.epcsd.showcatalog;

import edu.uoc.epcsd.showcatalog.application.rest.CatalogRESTController;
import edu.uoc.epcsd.showcatalog.domain.Category;
import edu.uoc.epcsd.showcatalog.domain.service.CatalogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class)
@WebMvcTest(CatalogRESTController.class)
public class CatalogControllerUnitTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CatalogService catalogService;

    @Test
    public void testGetCategories() throws Exception {
        given(catalogService.findAllCategories()).willReturn(getCategories());

        mvc.perform(get("/categories").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(catalogService, VerificationModeFactory.times(1)).findAllCategories();
        reset(catalogService);
    }

    private List<Category> getCategories() {
        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("Name Category 1");
        category1.setDescription("Description Category 1");

        Category category2 = new Category();
        category2.setId(1L);
        category2.setName("Name Category 2");
        category2.setDescription("Description Category 2");

        return Arrays.asList(category1, category2);
    }

}

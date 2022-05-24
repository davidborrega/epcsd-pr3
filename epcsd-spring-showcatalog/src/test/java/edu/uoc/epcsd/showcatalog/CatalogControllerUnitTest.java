package edu.uoc.epcsd.showcatalog;

import edu.uoc.epcsd.showcatalog.application.rest.CatalogRESTController;
import edu.uoc.epcsd.showcatalog.domain.Category;
import edu.uoc.epcsd.showcatalog.domain.service.CatalogService;
import edu.uoc.epcsd.showcatalog.infrastructure.repository.jpa.SpringDataCategoryRepository;
import edu.uoc.epcsd.showcatalog.infrastructure.repository.jpa.SpringDataShowRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


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
@ExtendWith(SpringExtension.class)
@WebMvcTest(CatalogRESTController.class)
public class CatalogControllerUnitTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SpringDataCategoryRepository springDataCategoryRepository;

    @MockBean
    private SpringDataShowRepository springDataShowRepository;

    @MockBean
    private CatalogService catalogService;

    @Test
    public void testGetCategories() throws Exception {
        given(catalogService.findAllCategories()).willReturn(getCategories());

        mvc.perform(get("/categories").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(getCategories().size())))
                .andExpect(jsonPath("$[0].description", is("Description Category 1")))
                .andExpect(jsonPath("$[1].description", is("Description Category 2")));

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

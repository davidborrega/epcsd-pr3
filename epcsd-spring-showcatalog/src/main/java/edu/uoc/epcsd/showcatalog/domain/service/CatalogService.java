package edu.uoc.epcsd.showcatalog.domain.service;

import edu.uoc.epcsd.showcatalog.domain.Category;
import edu.uoc.epcsd.showcatalog.domain.Performance;
import edu.uoc.epcsd.showcatalog.domain.Show;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CatalogService {

    // shows
    List<Show> findAllShows();

    Optional<Show> findShowById(Long id);

    List<Show> findShowsByName(String name);

    List<Show> findShowsByCategory(Long id);

    Long createShow(Show show);

    Set<Performance> getShowPerformances(Long id);

    void cancelShow(Long id);

    // categories
    List<Category> findAllCategories();

    Optional<Category> findCategoryById(Long id);

    Long createCategory(Category category);

    void deleteCategory(Long id);

    // performances
    void createShowPerformance(Long id, Performance performance);

}

package edu.uoc.epcsd.showcatalog.domain.service;

import edu.uoc.epcsd.showcatalog.domain.Category;
import edu.uoc.epcsd.showcatalog.domain.Performance;
import edu.uoc.epcsd.showcatalog.domain.Show;
import edu.uoc.epcsd.showcatalog.domain.repository.CategoryRepository;
import edu.uoc.epcsd.showcatalog.domain.repository.ShowRepository;
import edu.uoc.epcsd.showcatalog.infrastructure.kafka.KafkaConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class CatalogServiceImpl implements CatalogService {

    private final ShowRepository showRepository;

    private final CategoryRepository categoryRepository;

    private final KafkaTemplate<String, Show> kafkaTemplate;

    @Override
    public List<Show> findAllShows() {
        return showRepository.findAllShows();
    }

    @Override
    public Optional<Show> findShowById(Long id) {

        return showRepository.findShowById(id);
    }

    @Override
    public List<Show> findShowsByName(String name) {
        return showRepository.findShowsByName(name);
    }

    @Override
    public List<Show> findShowsByCategory(Long id) {

        return showRepository.findShowsByCategory(id);

    }

    @Override
    public Set<Performance> getShowPerformances(Long id) {

        return showRepository.findShowById(id).get().getPerformances();
    }

    @Override
    public Long createShow(Show show) {

        Category category = categoryRepository.findCategoryById(show.getCategory().getId()).get();

        show.setCategory(category);

        Long id = showRepository.createShow(show);

        log.trace("Sending " + show + " to " + KafkaConstants.SHOW_TOPIC + KafkaConstants.SEPARATOR + KafkaConstants.COMMAND_ADD + " topic.");
        kafkaTemplate.send(KafkaConstants.SHOW_TOPIC + KafkaConstants.SEPARATOR + KafkaConstants.COMMAND_ADD, showRepository.findShowById(id).get());

        return id;
    }

    @Override
    public void cancelShow(Long id) {

        Show show = showRepository.findShowById(id).get();
        show.cancel();
        showRepository.updateShow(show);
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAllCategories();
    }

    @Override
    public Optional<Category> findCategoryById(Long id) {

        return categoryRepository.findCategoryById(id);

    }

    @Override
    public Long createCategory(Category category) {

        category.setId(null);
        return categoryRepository.createCategory(category);
    }

    @Override
    public void deleteCategory(Long id) {

        categoryRepository.deleteCategory(id);
    }

    @Override
    public void createShowPerformance(Long id, Performance performance) {

        showRepository.addShowPerformance(id, performance);
    }
}

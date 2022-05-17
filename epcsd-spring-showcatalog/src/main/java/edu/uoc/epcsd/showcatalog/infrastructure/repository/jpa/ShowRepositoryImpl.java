package edu.uoc.epcsd.showcatalog.infrastructure.repository.jpa;

import edu.uoc.epcsd.showcatalog.domain.Performance;
import edu.uoc.epcsd.showcatalog.domain.Show;
import edu.uoc.epcsd.showcatalog.domain.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShowRepositoryImpl implements ShowRepository {

    private final SpringDataShowRepository jpaRepository;

    private final SpringDataCategoryRepository categoryJpaRepository;

    @Override
    public List<Show> findAllShows() {
        return jpaRepository.findAll().stream().map(ShowEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Show> findShowById(Long id) {
        return jpaRepository.findById(id).map(ShowEntity::toDomain);
    }

    @Override
    public List<Show> findShowsByCategory(Long id) {
        CategoryEntity categoryEntity = categoryJpaRepository.getById(id);
        return jpaRepository.findAll(Example.of(ShowEntity.builder().category(categoryEntity).build())).stream().map(ShowEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Show> findShowsByName(String name) {

        Show show = Show.builder().name(name).build();
        return jpaRepository.findAll(Example.of(ShowEntity.fromDomain(show))).stream().map(ShowEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public Long createShow(Show show) {

        ShowEntity showEntity = ShowEntity.fromDomain(show);
        showEntity.setCategory(categoryJpaRepository.getById(show.getCategory().getId()));
        return jpaRepository.save(showEntity).getId();
    }

    @Override
    public void updateShow(Show show) {

        ShowEntity showEntity = ShowEntity.fromDomain(show);
        showEntity.setCategory(categoryJpaRepository.getById(show.getCategory().getId()));
        jpaRepository.save(showEntity);
    }

    @Override
    public void addShowPerformance(Long id, Performance performance) {

        ShowEntity showEntity = jpaRepository.findById(id).get();
        showEntity.getPerformances().add(PerformanceEntity.fromDomain(performance));
        jpaRepository.save(showEntity);
    }
}

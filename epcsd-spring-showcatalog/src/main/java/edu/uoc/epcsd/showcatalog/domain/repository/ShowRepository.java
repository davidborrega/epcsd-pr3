package edu.uoc.epcsd.showcatalog.domain.repository;

import edu.uoc.epcsd.showcatalog.domain.Performance;
import edu.uoc.epcsd.showcatalog.domain.Show;

import java.util.List;
import java.util.Optional;

public interface ShowRepository {

    List<Show> findAllShows();

    Optional<Show> findShowById(Long id);

    List<Show> findShowsByName(String name);

    List<Show> findShowsByCategory(Long id);

    Long createShow(Show show);

    void updateShow(Show show);

    void addShowPerformance(Long id, Performance performance);

}

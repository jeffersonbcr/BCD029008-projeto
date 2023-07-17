package engtelecom.bcd.jpa.worldcup.repositores;

import engtelecom.bcd.jpa.worldcup.entities.Season;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SeasonRepository extends CrudRepository<Season, Integer> {
    Optional<Season> findByYear(Integer year);
}

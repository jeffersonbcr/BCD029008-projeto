package engtelecom.bcd.jpa.worldcup.repositores;


import engtelecom.bcd.jpa.worldcup.entities.MatchHasSeason;
import engtelecom.bcd.jpa.worldcup.entities.Season;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;
public interface MatchHasSeasonRepository extends CrudRepository<MatchHasSeason, Integer> {
    Optional<Iterable<MatchHasSeason>> findByEdicao(Season season);
}

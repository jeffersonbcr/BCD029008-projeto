package engtelecom.bcd.jpa.worldcup.repositores;

import engtelecom.bcd.jpa.worldcup.entities.Match;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<Match, Integer> {
}

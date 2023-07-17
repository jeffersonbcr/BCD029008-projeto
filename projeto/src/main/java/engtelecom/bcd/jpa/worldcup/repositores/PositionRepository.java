package engtelecom.bcd.jpa.worldcup.repositores;

import engtelecom.bcd.jpa.worldcup.entities.Match;
import engtelecom.bcd.jpa.worldcup.entities.Position;
import org.springframework.data.repository.CrudRepository;

public interface PositionRepository extends CrudRepository<Position, Integer> {
}

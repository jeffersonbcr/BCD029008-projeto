package engtelecom.bcd.jpa.worldcup.repositores;

import engtelecom.bcd.jpa.worldcup.entities.ClassificationHasStage;
import engtelecom.bcd.jpa.worldcup.entities.Match;
import org.springframework.data.repository.CrudRepository;

public interface ClassificationHasStageRepository extends CrudRepository<ClassificationHasStage, Integer> {
}

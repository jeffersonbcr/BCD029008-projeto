package engtelecom.bcd.jpa.worldcup.repositores;

import engtelecom.bcd.jpa.worldcup.entities.Match;
import engtelecom.bcd.jpa.worldcup.entities.MemberHasMatchHasEvent;
import org.springframework.data.repository.CrudRepository;

public interface MemberHasMatchHasEventRepository extends CrudRepository<MemberHasMatchHasEvent, Integer> {
}

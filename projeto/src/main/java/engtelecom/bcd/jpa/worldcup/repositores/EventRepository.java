package engtelecom.bcd.jpa.worldcup.repositores;

import engtelecom.bcd.jpa.worldcup.entities.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Integer> {
}

package engtelecom.bcd.jpa.worldcup.repositores;

import engtelecom.bcd.jpa.worldcup.entities.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Integer> {
}

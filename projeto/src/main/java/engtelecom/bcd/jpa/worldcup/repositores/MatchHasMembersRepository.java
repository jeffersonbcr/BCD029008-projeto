package engtelecom.bcd.jpa.worldcup.repositores;

import engtelecom.bcd.jpa.worldcup.entities.Match;
import engtelecom.bcd.jpa.worldcup.entities.MatchHasMembers;
import engtelecom.bcd.jpa.worldcup.entities.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MatchHasMembersRepository extends CrudRepository<MatchHasMembers, Integer> {
    Optional<MatchHasMembers> findByMemberAndMatch(Member jogador, Match partida);

    Optional<Iterable<MatchHasMembers>> findByPartida(Match partida);
}

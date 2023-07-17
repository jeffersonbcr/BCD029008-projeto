package engtelecom.bcd.jpa.worldcup.entities;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@ToString(exclude={"team_n","membersSet"})
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"team_n", "membersSet"})
@Entity
@RequiredArgsConstructor
@Table(name = "Member")
public class Member {

    @Id
    @OneToOne(fetch = FetchType.EAGER)
    private Person idPerson;

    @OneToOne(fetch = FetchType.EAGER)
    private Nation idNation;

    @ManyToMany(mappedBy = "member_n")
    private Set<Team> team_n = new HashSet<>();

    @OneToMany(mappedBy = "member")
    public Set<MatchHasMembers> membersSet = new HashSet<>();
}

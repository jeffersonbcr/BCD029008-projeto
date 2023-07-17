package engtelecom.bcd.jpa.worldcup.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@ToString(exclude = {"teams"})
@EqualsAndHashCode(exclude = {"teams"})
@NoArgsConstructor
@Entity
@RequiredArgsConstructor
@Table(name = "Coach")
public class Coach {
    @Id
    @OneToOne(fetch = FetchType.EAGER)
    private Person idPerson;

    @Id
    @OneToOne(fetch = FetchType.EAGER)
    private Nation idNation;

    @ManyToMany(mappedBy = "coach_n", fetch = FetchType.EAGER)
    private Set<Team> teams = new HashSet<>();
}

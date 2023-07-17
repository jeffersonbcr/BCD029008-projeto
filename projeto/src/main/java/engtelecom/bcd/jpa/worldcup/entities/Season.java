package engtelecom.bcd.jpa.worldcup.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(exclude={"matchHasSeasonSet","team_n"})
@EqualsAndHashCode(exclude = {"matchHasSeasonSet", "team_n"})
@Entity
@RequiredArgsConstructor
@Table(name = "Season")
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSeason;
    @NonNull
    private Integer year;
    @OneToOne
    @JoinColumn(name = "idNation", nullable = false)
    private Nation idNation;

    @OneToMany(mappedBy="season")
    private Set <MatchHasSeason> matchHasSeasonSet= new HashSet<>();

    @OneToMany(mappedBy="season", fetch = FetchType.EAGER)
    private Set <Team> team_n;


}

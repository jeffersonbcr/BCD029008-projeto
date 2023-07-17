package engtelecom.bcd.jpa.worldcup.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString(exclude={"match_n"})
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "Stadium")
public class Stadium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStadium;

    @NonNull private String Name;

    @OneToMany(mappedBy = "stadium", fetch = FetchType.EAGER )
    private Set<Match> match_n;

}

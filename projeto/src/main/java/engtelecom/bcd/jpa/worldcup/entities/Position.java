package engtelecom.bcd.jpa.worldcup.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString(exclude={"matchHasMembers"})
@NoArgsConstructor
@Entity
@RequiredArgsConstructor
@Table(name = "Position")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPosition;
    @Column(nullable = false)
    @NonNull private String name;

    @OneToMany(mappedBy = "position", fetch = FetchType.EAGER)
    private Set<MatchHasMembers> matchHasMembers;
}

package engtelecom.bcd.jpa.worldcup.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString(exclude={"matchHasSeason"})
@NoArgsConstructor
@Entity
@RequiredArgsConstructor
@Table(name = "Stage")
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStage;

    @Column(nullable = false)
    @NonNull private String name;

    @OneToOne(mappedBy = "Stage", fetch = FetchType.EAGER, optional = false)
    private MatchHasSeason matchHasSeason;

    @OneToMany(mappedBy="Stage", fetch = FetchType.EAGER)
    private Set<ClassificationHasStage> classificationHasStages;
}

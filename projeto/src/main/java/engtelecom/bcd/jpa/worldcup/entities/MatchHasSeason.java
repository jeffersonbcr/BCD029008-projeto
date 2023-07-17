package engtelecom.bcd.jpa.worldcup.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@RequiredArgsConstructor
@IdClass(ClassficationHasStageID.class)
public class MatchHasSeason {
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    private Match match;

    @Id
    @ManyToOne(fetch =  FetchType.EAGER)
    private Season season;

    @Id
    @OneToOne(fetch = FetchType.EAGER)
    private Stage stage;

}

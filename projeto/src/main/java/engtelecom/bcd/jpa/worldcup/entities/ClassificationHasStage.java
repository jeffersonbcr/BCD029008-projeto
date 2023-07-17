package engtelecom.bcd.jpa.worldcup.entities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Entity
@ToString
@IdClass(ClassficationHasStageID.class)
public class ClassificationHasStage {
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    private Classification classification;

    @Id
    @ManyToOne
    private Stage stage;
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    private Team team;


}

package engtelecom.bcd.jpa.worldcup.entities;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ClassficationHasStageID {
    private Integer classfication;
    private Integer team;

    private Integer stage;
}

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
@IdClass(MemberHasMatchHasEventID.class)
public class MemberHasMatchHasEvent {

    @Id
    @ManyToOne
    @NonNull
    private MatchHasMembers matchHasMembers;

    @Id
    @ManyToOne
    private Event evento;

    @Id
    @Column(name = "timeEvent", nullable = false)
    private double timeEvent;

}

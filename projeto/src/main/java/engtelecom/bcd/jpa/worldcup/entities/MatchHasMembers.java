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
public class MatchHasMembers {

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    private Member member;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    private Match match;

    @ManyToOne(fetch = FetchType.EAGER)
    private Position position;
}

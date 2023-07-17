package engtelecom.bcd.jpa.worldcup.entities;

import jakarta.persistence.*;
import lombok.*;

import java.security.PrivateKey;
import java.util.Set;

@Getter
@Setter
@ToString(exclude={"memberHasMatchHasEvent"})
@NoArgsConstructor
@Entity
@RequiredArgsConstructor
@Table(name = "Event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEvent;
    @NonNull
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "event")
    private Set<MemberHasMatchHasEvent> memberHasMatchHasEvent;

}

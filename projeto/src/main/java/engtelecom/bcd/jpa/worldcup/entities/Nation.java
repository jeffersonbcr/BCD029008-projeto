package engtelecom.bcd.jpa.worldcup.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString(exclude={""})
@NoArgsConstructor
@Entity
@RequiredArgsConstructor
@Table(name = "Nation")
public class Nation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNation;
    @NonNull
    @Column(nullable = false)
    private String country;

    @OneToOne(mappedBy = "nation")
    private Season season;

    @OneToOne(mappedBy = "nation")
    private Match match;

    @OneToOne(mappedBy = "nation")
    private Coach coach;

    @OneToOne(mappedBy = "nation")
    private Member member;

}

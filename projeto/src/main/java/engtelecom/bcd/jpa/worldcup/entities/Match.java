package engtelecom.bcd.jpa.worldcup.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@RequiredArgsConstructor
@Table(name = "Match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMatch;

    @NonNull
    @Column(nullable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="idStadium",nullable = false)
    @NonNull private Stadium stadium;

    @ManyToOne
    @JoinColumn(name ="idTeam1",nullable = false)
    @NonNull private Team idTeam1;

    @ManyToOne
    @JoinColumn(name ="idTeam2",nullable = false)
    @NonNull private Team idTeam2;

    @OneToMany(mappedBy="match", fetch = FetchType.EAGER)
    private Set<MatchHasSeason> matchHasSeasonSet= new HashSet<>();

    @OneToMany(mappedBy="match", fetch = FetchType.EAGER)
    private Set<MatchHasMembers> matchHasMembersSet= new HashSet<>();



}

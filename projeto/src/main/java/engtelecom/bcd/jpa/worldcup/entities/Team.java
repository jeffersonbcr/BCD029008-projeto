package engtelecom.bcd.jpa.worldcup.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(exclude={""})
@NoArgsConstructor
@Entity
@RequiredArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTeam;

    @Id
    @OneToOne(fetch = FetchType.EAGER)
    private Nation idNation;

    @ManyToOne
    @JoinColumn(name = "idSeason", nullable = false)
    @NonNull
    private Season season;

    @OneToMany(mappedBy = "team1")
    private Set<Match> partidasSelecao1 = new HashSet<>();

    @OneToMany(mappedBy = "team2")
    private Set<Match> partidasSelecao2 = new HashSet<>();

    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    public Set<Member> team_n = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "TeamHasMember",
            joinColumns = {
                    @JoinColumn(name="idTeam",
                            referencedColumnName = "idTeam",
                            nullable = false, updatable = false),
            },
            inverseJoinColumns = {
                    @JoinColumn(name="idPerson",
                            referencedColumnName = "idPerson",
                            nullable = false, updatable = false)
            })
    private Set<Member> member_n = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "TeamTeamHasCoach",
            joinColumns = {
                    @JoinColumn(name="idTeam",
                            referencedColumnName = "idTeam",
                            nullable = false, updatable = false),
            },
            inverseJoinColumns = {
                    @JoinColumn(name="idPerson",
                            referencedColumnName = "idPerson",
                            nullable = false, updatable = false)
            })
    private Set<Coach> coach_n = new HashSet<>();
    public boolean addMember(Member member){
        return this.member_n.add(member);
    }

    public boolean addCoach(Coach coach) {
        return this.coach_n.add(coach);

    }
}

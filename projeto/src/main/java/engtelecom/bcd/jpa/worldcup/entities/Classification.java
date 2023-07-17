package engtelecom.bcd.jpa.worldcup.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@RequiredArgsConstructor
@Table(name = "Classification")
public class Classification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClassification;

    @NonNull
    @Column(name= "classification", nullable = false)
    private Integer position;

    @OneToMany(mappedBy = "classification", fetch = FetchType.EAGER)
    public Set<ClassificationHasStage> classificaoDasSelecoesSet = new HashSet<>();
}

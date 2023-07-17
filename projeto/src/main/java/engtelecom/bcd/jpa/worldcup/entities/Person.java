package engtelecom.bcd.jpa.worldcup.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@RequiredArgsConstructor
@Table(name = "Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPerson;

    @Column(nullable = false)
    @NonNull
    private String name;

    @Column(nullable = false)
    @NonNull
    private String lastName;

    @Column(nullable = false)
    @NonNull
    private Date birthDate;


}

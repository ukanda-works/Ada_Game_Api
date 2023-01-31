package es.eukariotas.apiservice.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "party")
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Long id;

    @Column(name = "winner", nullable = false)
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Long ganador;

    @Column(name = "date", nullable = false)
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    private LocalDateTime comienzo_partida;

    @ManyToMany(mappedBy = "parties")
    private Collection<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "party", orphanRemoval = true)
    private List<Turn> turns = new ArrayList<>();

}
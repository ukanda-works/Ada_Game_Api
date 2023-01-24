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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "partida")
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Long id;

    @Column(name = "ganada", nullable = false)
    @JdbcTypeCode(SqlTypes.TINYINT)
    private Boolean ganada;

    @Column(name = "comienzo_partida", nullable = false)
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    private LocalDateTime comienzo_partida;

    @ManyToMany(mappedBy = "partidas")
    private Collection<User> users = new ArrayList<>();

}
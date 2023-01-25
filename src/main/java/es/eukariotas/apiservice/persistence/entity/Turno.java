package es.eukariotas.apiservice.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "turno")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Long id;

    @Column(name = "informacion", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String informacion;

    @Column(name = "jugador", nullable = false)
    @JdbcTypeCode(SqlTypes.TINYINT)
    private Boolean jugador;

    @Column(name = "final_turno", length = 1, nullable = false)
    @JdbcTypeCode(SqlTypes.TINYINT)
    private String final_turno;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "partida_id")
    private Partida partida;

    //TODO metodos para el calculo de la puntuacion
}

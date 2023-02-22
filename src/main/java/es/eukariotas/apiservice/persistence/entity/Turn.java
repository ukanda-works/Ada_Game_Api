package es.eukariotas.apiservice.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "turn")
public class Turn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "party_id")
    private Party party;

    @Column(name = "NUMTURNO", nullable = false)
    private int numTurno;

    @Column(name = "jugador", nullable = false)
    private String jugador;

    @Column(name = "movimiento", nullable = false)
    private String movimiento;


}

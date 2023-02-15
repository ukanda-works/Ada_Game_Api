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
@Table(name = "turn")
public class Turn {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "party_id")
    private Party party;

    @Column(name = "numTurno", nullable = false)
    private int numTurno;

    @Column(name = "jugador", nullable = false)
    private String jugador;

    @Column(name = "movimiento", nullable = false)
    private String movimiento;

    @Column(name = "last")
    private boolean last;


}

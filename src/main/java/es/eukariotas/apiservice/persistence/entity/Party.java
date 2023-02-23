package es.eukariotas.apiservice.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "party")
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "resultado", nullable = false)
    private String resultado;

    @Column(name = "jugador_color", nullable = false)
    private String jugador_color;

    @Column(name = "date", nullable = false)
    private Date comienzo_partida;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "party")
    private List<Turn> turns = new ArrayList<>();

    public void addTurn(Turn turn) {
        turns.add(turn);
        turn.setParty(this);
    }

    @Override
    public String toString() {
        return "Party{" +
                "id=" + id +
                ", resultado='" + resultado + '\'' +
                ", jugador_color='" + jugador_color + '\'' +
                ", comienzo_partida=" + comienzo_partida.toString() +
                ", user=" + user +
                ", turns=" + turns +
                '}';

    }
}
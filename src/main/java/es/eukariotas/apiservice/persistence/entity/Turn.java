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
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Long id;

    @Column(name = "player", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String informacion;

    @Column(name = "next")
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Integer next;

    @Column(name = "end")
    @JdbcTypeCode(SqlTypes.TINYINT)
    private Boolean end;

    @ManyToOne
    @JoinColumn(name = "party_id")
    private Party party;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

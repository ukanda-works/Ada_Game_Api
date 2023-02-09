package es.eukariotas.apiservice.persistence.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "stadistics")
public class Stadistics {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "losses")
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Integer losses;

    @Column(name = "victories")
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Integer victories;

    @Column(name = "hours")
    @JdbcTypeCode(SqlTypes.DOUBLE)
    private Double hours;



}

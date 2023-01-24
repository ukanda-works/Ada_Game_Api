package es.eukariotas.apiservice.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    @Column(name = "user_name",nullable = false, unique = true, length = 45)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String userName;

    @Column(name = "user_email", nullable = false, unique = true, length = 45)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String userEmail;

    @Column(name = "user_pass",nullable = false, unique = true, length = 45)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String userPass;

    @Column(name = "user_profile_img", length = 45)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String user_profile_img;

    @Column(name = "user_description", length = 45)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String userDescription;

    @Column(name = "user_country", length = 45)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String userCountry;

    @ManyToMany
    @JoinTable(name = "user_partidas",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "partidas_id"))
    private Collection<Partida> partidas = new ArrayList<>();

}

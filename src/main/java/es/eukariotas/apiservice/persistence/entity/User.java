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
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "puntucacion", nullable = false)
    private int puntucacion;

    @Column(name = "userName",nullable = false, unique = true, length = 45)
    private String userName;

    @Column(name = "email", nullable = false, unique = true, length = 45)
    private String userEmail;

    @Column(name = "password",nullable = false, unique = true, length = 45)
    private String password;

    @Column(name = "description", length = 240)
    private String userDescription;

    @Column(name = "country", length = 45)
    private String userCountry;


    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Party> parties = new ArrayList<>();

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Token token;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(userName, user.userName) && Objects.equals(userEmail, user.userEmail) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, userEmail, password);
    }
}

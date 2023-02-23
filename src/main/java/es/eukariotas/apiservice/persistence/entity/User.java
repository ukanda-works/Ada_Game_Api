package es.eukariotas.apiservice.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "puntuacion", nullable = false)
    private int puntuacion;

    @Column(name = "USERNAME",nullable = false, unique = true, length = 45)
    private String userName;

    @Column(name = "email", nullable = false, unique = true, length = 45)
    private String userEmail;

    @Column(name = "password",nullable = false, length = 45)
    private String password;

    @Column(name = "description", length = 240)
    private String userDescription;

    @Column(name = "country", length = 45)
    private String userCountry;

    @JsonIgnore
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Party> parties = new ArrayList<>();

    @Column(name = "last_login")
    private Date lastLogin;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Token token;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(userName, user.userName) && Objects.equals(userEmail, user.userEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, userEmail);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                "userName" + userName +
                "userEmail" + userEmail +
                '}';
    }
}

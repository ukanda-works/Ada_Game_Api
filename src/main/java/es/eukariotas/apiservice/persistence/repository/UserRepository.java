package es.eukariotas.apiservice.persistence.repository;

import es.eukariotas.apiservice.persistence.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    List<User> findAll();
    Page<User> findAll(Pageable pageable);
    Optional<User> findById(Long id);
    Optional<User> findByUserName(String userName);
    Optional<User> findByUserNameAndPassword(String userName, String password);


    Boolean existsByUserNameAndPassword(String userName, String password);
    Boolean existsByUserEmail(String userEmail);
    Boolean existsByUserName(String userName);

    User findTopByOrderByPuntuacionDesc();

}
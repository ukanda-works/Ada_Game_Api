package es.eukariotas.apiservice.persistence.repository;

import es.eukariotas.apiservice.persistence.entity.Turn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TurnoRepository extends JpaRepository<Turn, Long> {
    List<Turn> findAll();
    Optional<Turn> findById(Long aLong);
}
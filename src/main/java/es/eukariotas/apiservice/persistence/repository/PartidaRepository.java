package es.eukariotas.apiservice.persistence.repository;

import es.eukariotas.apiservice.persistence.entity.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PartidaRepository extends JpaRepository<Partida, Long> {
    List<Partida> findAll();
    Optional<Partida> findById(Long aLong);
}
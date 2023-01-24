package es.eukariotas.apiservice.persistence.repository;

import es.eukariotas.apiservice.persistence.entity.Stadistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StadisticsRepository extends JpaRepository<Stadistics, Long> {
    List<Stadistics> findAll();

    @Override
    Optional<Stadistics> findById(Long aLong);
}
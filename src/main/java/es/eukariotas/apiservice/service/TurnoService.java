package es.eukariotas.apiservice.service;

import es.eukariotas.apiservice.persistence.entity.Turn;
import es.eukariotas.apiservice.persistence.repository.TurnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService {
    private final TurnoRepository turnoRepository;

    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    public void saveTurno(Turn turn) {
        turnoRepository.save(turn);
    }

    public void deleteTurno(Turn turn) {
        turnoRepository.delete(turn);
    }

    public Turn getTurnoById(Long id) {
        return turnoRepository.findById(id).orElse(null);
    }

    public List<Turn> getAllTurnos() {
        return turnoRepository.findAll();
    }
}

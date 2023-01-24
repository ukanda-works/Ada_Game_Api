package es.eukariotas.apiservice.service;

import es.eukariotas.apiservice.persistence.entity.Turno;
import es.eukariotas.apiservice.persistence.repository.TurnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService {
    private final TurnoRepository turnoRepository;

    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    public void saveTurno(Turno turno) {
        turnoRepository.save(turno);
    }

    public void deleteTurno(Turno turno) {
        turnoRepository.delete(turno);
    }

    public Turno getTurnoById(Long id) {
        return turnoRepository.findById(id).orElse(null);
    }

    public List<Turno> getAllTurnos() {
        return turnoRepository.findAll();
    }
}

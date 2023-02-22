package es.eukariotas.apiservice.controller;

import es.eukariotas.apiservice.persistence.entity.Turn;
import es.eukariotas.apiservice.service.TurnoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turno")
public class TurnoController{

    private final TurnoService turnoService;
    @Autowired
    private HttpServletRequest request;
    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("/save-list")
    public ResponseEntity<List<Turn>> saveTurnos(@RequestBody List<Turn> turnos, @RequestParam("partyId") String partyId) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        HttpHeaders headers = new HttpHeaders();
        List<Turn> savedTurnos = null;
        try {
            savedTurnos = turnoService.saveTurnos(turnos, Long.valueOf(partyId));
            status = HttpStatus.OK;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(savedTurnos, headers, status);

    }

    @PostMapping("/save")
    public ResponseEntity<Turn> saveTurno(@RequestBody Turn turno, @RequestParam("partyId") String partyId) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        HttpHeaders headers = new HttpHeaders();
        Turn savedTurno = null;
        try {
            savedTurno = turnoService.saveTurno(turno, Long.valueOf(partyId));
            status = HttpStatus.OK;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(savedTurno, headers, status);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTurno(@PathVariable("id") Long id) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        HttpHeaders headers = new HttpHeaders();
        String body ="";
        try {
            turnoService.deleteTurno(id);
            status = HttpStatus.OK;
            body = "Turno eliminado correctamente";
        } catch (Exception e) {
            body = "Error al eliminar el turno"+e.getMessage();
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(body, headers, status);
    }

    @GetMapping("num/{id_partida}/{num}")
    public ResponseEntity<Turn> getNumByParty(@PathVariable("id_partida") Long id_partida, @PathVariable("num") int num) {
        Turn turno = null;
        HttpStatus status = HttpStatus.BAD_REQUEST;
        HttpHeaders headers = new HttpHeaders();
        try {
            turno = turnoService.getNumByParty(id_partida, num);
            status = HttpStatus.OK;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(turno, headers, status);
    }

}

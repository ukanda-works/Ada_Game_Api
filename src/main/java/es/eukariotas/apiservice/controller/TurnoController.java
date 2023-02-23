package es.eukariotas.apiservice.controller;

import es.eukariotas.apiservice.persistence.entity.Turn;
import es.eukariotas.apiservice.service.TurnoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

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

    @Operation(summary = "Almacena una lista de turnos pasada como body")
    @ApiResponse(responseCode = "200", description = "La lista de turnos se ha almacenado correctamente")
    @ApiResponse(responseCode = "400", description = "Error al intentar almacenar la lista de turnos")
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

    @Operation(summary = "Almacena un turno pasado como body en la partyId pasada como parámetro")
    @ApiResponse(responseCode = "200", description = "El turno se ha almacenado correctamente")
    @ApiResponse(responseCode = "400", description = "Error al intentar almacenar el turno")
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

    @Operation(summary = "Elimina un turno por su id")
    @ApiResponse(responseCode = "200", description = "El turno se ha eliminado correctamente")
    @ApiResponse(responseCode = "400", description = "Error al intentar eliminar el turno")
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

    @Operation(summary = "Devuelve un turno por su id de partida y su número de turno")
    @ApiResponse(responseCode = "200", description = "El turno se ha obtenido correctamente")
    @ApiResponse(responseCode = "400", description = "Error al intentar obtener el turno")
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

    @Operation(summary = "Almacena todos los turnos de la base de datos en un fichero JSON")
    @ApiResponse(responseCode = "200", description = "Todos los turnos se han almacenado en un archivo JSON")
    @ApiResponse(responseCode = "400", description = "Error al intentar almacenar los turnos en el archivo JSON")
    @GetMapping("/all/to-json")
    public ResponseEntity<String> getPartidasToJson() {
        String location = null;
        HttpStatus status = HttpStatus.BAD_REQUEST;
        HttpHeaders headers = new HttpHeaders();
        try {
            location = turnoService.saveToJson();
            status = HttpStatus.OK;
        } catch (Exception e) {
            throw new RuntimeException(e);
            //Todo controlar excepciones
        }
        return new ResponseEntity<>(location, headers, status);
    }

}

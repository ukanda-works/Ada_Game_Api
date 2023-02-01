package es.eukariotas.apiservice.controller;

import es.eukariotas.apiservice.persistence.entity.Turn;
import es.eukariotas.apiservice.service.TurnoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    public List<Turn> getTurnos() {
        return turnoService.getAllTurnos();
    }
}

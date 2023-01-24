package es.eukariotas.apiservice.controller;

import es.eukariotas.apiservice.persistence.entity.Partida;
import es.eukariotas.apiservice.service.PartidaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/partida")
public class PartidaController {
    private final PartidaService partidaService;

    public PartidaController(PartidaService partidaService) {
        this.partidaService = partidaService;
    }

    @GetMapping
    public List<Partida> getPartidas() {
        return partidaService.getAllPartidas();
    }


}

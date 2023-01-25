package es.eukariotas.apiservice.controller;

import es.eukariotas.apiservice.exceptions.CustomExceptions;
import es.eukariotas.apiservice.persistence.entity.Partida;
import es.eukariotas.apiservice.service.PartidaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/partida")
public class PartidaController{
    private final PartidaService partidaService;

    @Autowired
    private HttpServletRequest request;

    public PartidaController(PartidaService partidaService) {
        this.partidaService = partidaService;
        ;
    }

    @GetMapping
    public List<Partida> getPartidas() {
        return partidaService.getAllPartidas();
    }

    @GetMapping("/iniciar")
    public void iniciarPartida(){
        try {
            partidaService.verifyHeader(request);
            System.out.println("Iniciando partida");
        } catch (Exception e) {
            //lanzar respuesta con error
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }


}

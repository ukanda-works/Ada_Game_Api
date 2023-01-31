package es.eukariotas.apiservice.controller;

import es.eukariotas.apiservice.persistence.entity.Party;
import es.eukariotas.apiservice.service.PartidaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    }

    @GetMapping
    public List<Party> getPartidas() {
        return partidaService.getAllPartidas();
    }

    @GetMapping("/iniciar")
    public ResponseEntity<String> iniciarPartida(){
        String body ="";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        HttpHeaders headers = new HttpHeaders();
        try {
            partidaService.verifyHeader(request);
            body = "partida iniciada";
            status = HttpStatus.OK;
        } catch (Exception e) {
            //lanzar respuesta con error
            body = "error al iniciar -"+e.getMessage();
        }
        return new ResponseEntity<>(body, headers, status);
    }
}

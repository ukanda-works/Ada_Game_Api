package es.eukariotas.apiservice.controller;

import es.eukariotas.apiservice.persistence.entity.Party;
import es.eukariotas.apiservice.service.PartidaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<Party> getPartida(@PathVariable("id") Long id) {
        Party party = null;
        HttpStatus status = HttpStatus.BAD_REQUEST;
        HttpHeaders headers = new HttpHeaders();
        try {
            party = partidaService.getPartida(id);
            status = HttpStatus.OK;
        } catch (Exception e) {
            throw new RuntimeException(e);
            //Todo controlar excepciones
        }
        return new ResponseEntity<>(party, headers, status);
    }

     @PostMapping("/create")
    public ResponseEntity<Party> createPartida(@RequestBody Party party, @RequestParam("userId") String userId) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        HttpHeaders headers = new HttpHeaders();
        Party savedParty = null;
        try {
            savedParty = partidaService.savePartida(party, Long.valueOf(userId));
            status = HttpStatus.OK;
        } catch (Exception e) {
            throw new RuntimeException(e);
            //Todo controlar excepciones
        }
        return new ResponseEntity<>(savedParty, headers, status);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Party>> getPartidasPage(@RequestParam("page") int page, @RequestParam("size") int size) {
        Page<Party> parties = null;
        HttpStatus status = HttpStatus.BAD_REQUEST;
        HttpHeaders headers = new HttpHeaders();
        try {
            parties = partidaService.getAllPartidasPage(PageRequest.of(page, size));
            status = HttpStatus.OK;
        } catch (Exception e) {
            throw new RuntimeException(e);
            //Todo controlar excepciones
        }
        return new ResponseEntity<>(parties, headers, status);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Party>> getPartidasByUserId(@PathVariable("userId") Long userId) {
        List<Party> parties = null;
        HttpStatus status = HttpStatus.BAD_REQUEST;
        HttpHeaders headers = new HttpHeaders();
        try {
            parties = partidaService.getPartidasByUserId(userId);
            status = HttpStatus.OK;
        } catch (Exception e) {
            throw new RuntimeException(e);
            //Todo controlar excepciones
        }
        return new ResponseEntity<>(parties, headers, status);
    }

    @GetMapping("/page/{userId}")
    public ResponseEntity<Page<Party>> getPartidasByUserIdPage(@PathVariable("userId") Long userId, @RequestParam("page") int page, @RequestParam("size") int size) {
        Page<Party> parties = null;
        HttpStatus status = HttpStatus.BAD_REQUEST;
        HttpHeaders headers = new HttpHeaders();
        try {
            parties = partidaService.getPartidasByUserIdPage(userId, PageRequest.of(page, size));
            status = HttpStatus.OK;
        } catch (Exception e) {
            throw new RuntimeException(e);
            //Todo controlar excepciones
        }
        return new ResponseEntity<>(parties, headers, status);
    }

    @GetMapping("/win/{userId}")
    public ResponseEntity<List<Party>> getPartidasByUserIdWin(@PathVariable("userId") Long userId) {
        List<Party> parties = null;
        HttpStatus status = HttpStatus.BAD_REQUEST;
        HttpHeaders headers = new HttpHeaders();
        try {
            parties = partidaService.getPartidasByUserIdWin(userId);
            status = HttpStatus.OK;
        } catch (Exception e) {
            throw new RuntimeException(e);
            //Todo controlar excepciones
        }
        return new ResponseEntity<>(parties, headers, status);
    }
}

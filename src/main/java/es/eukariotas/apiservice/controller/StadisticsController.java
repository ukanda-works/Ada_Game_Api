package es.eukariotas.apiservice.controller;

import es.eukariotas.apiservice.service.PartidaService;
import es.eukariotas.apiservice.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stadistics")
public class StadisticsController {
    private final UserService userService;
    private final PartidaService partidaService;

    @Autowired
    private HttpServletRequest request;

    public StadisticsController(UserService userService, PartidaService partidaService) {
        this.userService = userService;
        this.partidaService = partidaService;
    }

    @GetMapping("/win/{user_id}")
    public ResponseEntity<Integer> numWinParites(@PathVariable("user_id") Long userId) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        HttpHeaders headers = new HttpHeaders();
        Integer numWin = null;
        try {
            numWin = partidaService.getPartidasByUserIdWin(userId).size();
            status = HttpStatus.OK;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(numWin, headers, status);
    }
}

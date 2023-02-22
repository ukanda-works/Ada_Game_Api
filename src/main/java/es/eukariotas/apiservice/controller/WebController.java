package es.eukariotas.apiservice.controller;

import es.eukariotas.apiservice.service.TurnoService;
import es.eukariotas.apiservice.service.UserService;
import es.eukariotas.apiservice.service.PartidaService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class WebController {

    private final UserService userService;
    private final PartidaService partidaService;
    private final TurnoService turnoService;

    public WebController(UserService userService, PartidaService partidaService, TurnoService turnoService) {
        this.userService = userService;
        this.partidaService = partidaService;
        this.turnoService = turnoService;
    }




}

package es.eukariotas.apiservice.controller;

import es.eukariotas.apiservice.persistence.entity.Stadistics;
import es.eukariotas.apiservice.service.StadisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stadistics")
public class StadisticsController{
    private final StadisticsService stadisticsService;

    public StadisticsController(StadisticsService stadisticsService) {
        this.stadisticsService = stadisticsService;
    }

    @GetMapping
    public List<Stadistics> getStadistics() {
        return stadisticsService.getAllStadistics();
    }
}

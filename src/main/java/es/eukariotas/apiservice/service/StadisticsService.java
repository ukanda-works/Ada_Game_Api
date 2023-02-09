package es.eukariotas.apiservice.service;

import es.eukariotas.apiservice.persistence.entity.Stadistics;
import es.eukariotas.apiservice.persistence.repository.StadisticsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StadisticsService {
    private final StadisticsRepository stadisticsRepository;

    public StadisticsService(StadisticsRepository stadisticsRepository) {
        this.stadisticsRepository = stadisticsRepository;
    }


    public void saveStadistics(Stadistics stadistics) {
        stadisticsRepository.save(stadistics);
    }

    public Stadistics getStadisticsById(Long id) {
        return stadisticsRepository.findById(id).orElse(null);
    }
    public List<Stadistics> getAllStadistics() {
        return stadisticsRepository.findAll();
    }


}

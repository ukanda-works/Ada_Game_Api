package es.eukariotas.apiservice.service;

import es.eukariotas.apiservice.persistence.entity.Party;
import es.eukariotas.apiservice.persistence.repository.PartidaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartidaService{
    private final PartidaRepository partidaRepository;

    public PartidaService( PartidaRepository partidaRepository){

        this.partidaRepository = partidaRepository;
    }

    public void savePartida(Party party) {
        partidaRepository.save(party);
    }

    public void deletePartida(Party party) {
        partidaRepository.delete(party);
    }

    public Party getPartidaById(Long id) {
        return partidaRepository.findById(id).orElse(null);
    }

    public List<Party> getAllPartidas() {
        return partidaRepository.findAll();
    }


}

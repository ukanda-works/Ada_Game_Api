package es.eukariotas.apiservice.service;

import es.eukariotas.apiservice.exceptions.CustomExceptions;
import es.eukariotas.apiservice.persistence.entity.Partida;
import es.eukariotas.apiservice.persistence.repository.GenericRepository;
import es.eukariotas.apiservice.persistence.repository.PartidaRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PartidaService extends GenericService{
    @Autowired
    private GenericRepository genericRepository;
    private final PartidaRepository partidaRepository;

    public PartidaService( PartidaRepository partidaRepository){

        this.partidaRepository = partidaRepository;
    }

    public void savePartida(Partida partida) {
        partidaRepository.save(partida);
    }

    public void deletePartida(Partida partida) {
        partidaRepository.delete(partida);
    }

    public Partida getPartidaById(Long id) {
        return partidaRepository.findById(id).orElse(null);
    }

    public List<Partida> getAllPartidas() {
        return partidaRepository.findAll();
    }

    public void verifyHeader(HttpServletRequest request) throws CustomExceptions {
        Map<String,String> headers = headers(request);
        if (!genericRepository.verifyToken(headers.get("user"),headers.get("token"))){
            throw new CustomExceptions("token invalido");
        }
    }
}

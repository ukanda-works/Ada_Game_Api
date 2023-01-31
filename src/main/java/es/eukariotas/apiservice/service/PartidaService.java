package es.eukariotas.apiservice.service;

import es.eukariotas.apiservice.exceptions.CustomExceptions;
import es.eukariotas.apiservice.persistence.entity.Party;
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

    public void verifyHeader(HttpServletRequest request) throws CustomExceptions {
        Map<String,String> headers = headers(request);
        if (!genericRepository.verifyToken(headers.get("user"),headers.get("token"))){
            throw new CustomExceptions("token invalido");
        }
    }
}

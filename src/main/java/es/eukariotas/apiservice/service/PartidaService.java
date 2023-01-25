package es.eukariotas.apiservice.service;

import es.eukariotas.apiservice.persistence.entity.Partida;
import es.eukariotas.apiservice.persistence.repository.GenericRepository;
import es.eukariotas.apiservice.persistence.repository.PartidaRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartidaService{
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

    public void verifyHeader(HttpServletRequest request){
        if (genericRepository.verifyUser("nombre")){
            System.out.println("Usuario correcto");
        }else {
            System.out.println("Usuario incorrecto");
        }
    }
}

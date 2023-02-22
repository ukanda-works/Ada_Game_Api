package es.eukariotas.apiservice.service;

import es.eukariotas.apiservice.persistence.entity.Party;
import es.eukariotas.apiservice.persistence.entity.User;
import es.eukariotas.apiservice.persistence.repository.PartidaRepository;
import es.eukariotas.apiservice.persistence.repository.TurnoRepository;
import es.eukariotas.apiservice.persistence.repository.UserRepository;
import jakarta.servlet.http.Part;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartidaService{
    private final PartidaRepository partidaRepository;
    private final UserRepository userRepository;
    private final TurnoRepository turnoRepository;

    public PartidaService( PartidaRepository partidaRepository, UserRepository userRepository, TurnoRepository turnoRepository) {

        this.partidaRepository = partidaRepository;
        this.userRepository = userRepository;
        this.turnoRepository = turnoRepository;
    }

    public Party savePartida(Party party, Long userId){

        if (partidaRepository.existsById(party.getId())) {
            throw new RuntimeException("Ya existe una partida con ese id");
        }else{
            User user = userRepository.findById(userId).orElse(null);
            if (user == null) {
                throw new RuntimeException("No existe un usuario con ese id");
            }

            party.setUser(user);
            Party savedParty = partidaRepository.save(party);
            turnoRepository.saveAll(savedParty.getTurns());
            return savedParty;
        }
    }

    public void deletePartida(Party party) {
        partidaRepository.delete(party);
    }

    public List<Party> getAllPartidas() {
        return partidaRepository.findAll();
    }


    public Party getPartida(Long id) {
        return partidaRepository.findById(id).orElse(null);
    }


    public Page<Party> getAllPartidasPage(Pageable pageable) {
        return partidaRepository.findAll(pageable);
    }


    public List<Party> getPartidasByUserId(Long userId) {
        List<Party> parties = partidaRepository.findAll();
       return parties.stream().filter(party -> party.getUser().getId()==userId).collect(Collectors.toList());
    }

    public Page<Party> getPartidasByUserIdPage(Long userId, Pageable pageable) {
        return partidaRepository.findAll(pageable);
        //Todo terminar de implementar
    }

    public List<Party> getPartidasByUserIdWin(Long userId) {
        List<Party> parties = partidaRepository.findAll();
        return parties.stream().filter(party -> party.getUser().getId()==userId && party.getResultado()==party.getJugador_color()).collect(Collectors.toList());
    }
}

package es.eukariotas.apiservice.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.eukariotas.apiservice.persistence.HibernateProxyTypeAdapter;
import es.eukariotas.apiservice.persistence.entity.Party;
import es.eukariotas.apiservice.persistence.entity.Turn;
import es.eukariotas.apiservice.persistence.repository.PartidaRepository;
import es.eukariotas.apiservice.persistence.repository.TurnoRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class TurnoService {
    private final TurnoRepository turnoRepository;
    private final PartidaRepository partidaRepository;
    public TurnoService(TurnoRepository turnoRepository, PartidaRepository partidaRepository) {
        this.turnoRepository = turnoRepository;
        this.partidaRepository = partidaRepository;
    }

    public Turn saveTurno(Turn turn, Long partyId) {
        Party party = partidaRepository.findById(partyId).orElse(null);
        if (party == null) {
            throw new RuntimeException("No existe una partida con ese id");
        }
        party.addTurn(turn);
        turn.setParty(party);
        Turn savedTurn = turnoRepository.save(turn);
        party.addTurn(savedTurn);
        Party savedParty = partidaRepository.save(party);

        return savedTurn;
    }

    public void deleteTurno(Long id) {
        Turn turn = turnoRepository.findById(id).orElse(null);
        if (turn == null) {
            throw new RuntimeException("No existe un turno con ese id");
        }
        turnoRepository.delete(turn);
    }

    public Turn getTurnoById(Long id) {
        return turnoRepository.findById(id).orElse(null);
    }

    public List<Turn> getAllTurnos() {
        return turnoRepository.findAll();
    }

    public List<Turn> saveTurnos(List<Turn> turnos, Long partyId) {
        Party party = partidaRepository.findById(partyId).orElse(null);
        if (party == null) {
            throw new RuntimeException("No existe una partida con ese id");
        }
        for (Turn turn : turnos) {
            turn.setParty(party);
        }
        party.setTurns(turnos);

        List<Turn> turnsSaved = turnoRepository.saveAll(turnos);
        Party savedParty = partidaRepository.save(party);
        return turnsSaved;
    }

    public Turn getNumByParty(Long idPartida, int num) {
        List<Turn> turns = turnoRepository.findByParty_IdAndNumTurno(idPartida, num);
        if (turns.size() == 0) {
            throw new RuntimeException("No existe un turno con ese numero");
        }
        for (Turn turn : turns) {
            if(turn.getNumTurno() == num){
                return turn;
            }

        }
        return null;
    }

    public String saveToJson() {
        String url = "src/main/resources/saved/turnos.json";
        File file = new File(url);

        GsonBuilder b = new GsonBuilder();
        b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        Gson gson = b.create();

        List<Turn> turnos = turnoRepository.findAll();
        turnos.stream().forEach(turn -> {
            turn.setParty(null);

        });
        String json = gson.toJson(turnos);
        try(FileWriter fileWriter = new FileWriter(file)){

            fileWriter.write(json);
            fileWriter.flush();
        } catch (IOException e){
           e.printStackTrace();
        }
        return url;
    }
}

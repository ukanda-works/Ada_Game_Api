package es.eukariotas.apiservice.persistence.repository;

import es.eukariotas.apiservice.persistence.entity.Partida;

import java.util.List;

public interface GenericRepository {
    Boolean verifyUser(String user);
    Boolean verifyToken(String user, String token);

}

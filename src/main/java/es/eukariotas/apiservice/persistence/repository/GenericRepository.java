package es.eukariotas.apiservice.persistence.repository;

public interface GenericRepository {
    Boolean verifyUser(String user);
    Boolean verifyToken(String user, String token);

}

package es.eukariotas.apiservice.persistence.repository;

import es.eukariotas.apiservice.persistence.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

@Repository
public class GenericRepositoryImp implements GenericRepository{
    @PersistenceContext

    private EntityManager entityManager;
    @Override
    public Boolean verifyUser(String user) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        Predicate userNamePredicate = builder.equal(root.get("userName"), user);
        criteria.where(userNamePredicate);
        TypedQuery<User> query = entityManager.createQuery(criteria);
        User userResult = query.getSingleResult();
        if (userResult != null) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * Verifica si el token es correcto
     * @param user usuario que se quiere verificar
     * @param token token que se quiere verificar
     * @return true si el token es correcto, false si no lo es
     */
    @Override
    public Boolean verifyToken(String user, String token){
        if (verifyUser(user)){
            return true;
        }else{
            return false;
        }
    }
}

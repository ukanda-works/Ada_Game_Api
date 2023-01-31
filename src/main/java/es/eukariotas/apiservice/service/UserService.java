package es.eukariotas.apiservice.service;

import es.eukariotas.apiservice.exceptions.CustomExceptions;
import es.eukariotas.apiservice.persistence.entity.Token;
import es.eukariotas.apiservice.persistence.entity.User;
import es.eukariotas.apiservice.persistence.repository.TokenRepository;
import es.eukariotas.apiservice.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public UserService(UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    public boolean existsByUsername(User user) {
       if (getUserById(user.getId()) == null) {
           return false;
       }
         return true;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Creates a new user if the username is not taken
     * @param user the user to be created
     */
    public void createUser(User user) {
        if (user.getUserEmail() != null && user.getPassword() != null && user.getUserName() != null) {
            if (existsByUsername(user)) {
                throw new IllegalStateException("Username already taken");
            } else {
                saveUser(user);
            }
        }
    }

    /**
     * Updates the user email using the username and password
     * @param userName the username of the user
     * @param pass the password of the user
     * @param email the new email of the user
     */
    public void updateUserEmail(String userName,String pass, String email) {
        User user = userRepository.findByUserName(userName).orElse(null);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }else {
            if (!user.getPassword().equals(pass)) {
                throw new IllegalStateException("Password incorrect");
            }else{
                user.setUserEmail(email);
                saveUser(user);
            }
        }
    }

    /**
     * Logs in the user and creates a token
     * @param userName the username of the user
     * @param pass the password of the user
     * @return new token for the user
     * @throws CustomExceptions for some incidents
     */
    public Token login(String userName, String pass) throws CustomExceptions {
        User user = userRepository.findByUserNameAndPassword(userName, pass).orElse(null);
        if (user == null) {
            throw new CustomExceptions("User not found");
        }else {
            if (user.getToken() != null) {
                user.setToken(null);
            }
           Token token = Token.createToken(user);
            user.setToken(token);
            user.setLastLogin(LocalDateTime.now());
           tokenRepository.save(token);
           userRepository.save(user);
            return token;
        }
    }


    public Boolean verifyTokenByUser(String userName, String token) throws CustomExceptions {
        User user = userRepository.findByUserName(userName).orElse(null);
        if (user == null) {
            throw new CustomExceptions("User not found");
        }else {
            if (user.getToken() == null) {
                throw new CustomExceptions("User not logged in");
            }else {
                if (!user.getToken().getToken().equals(token)) {
                    throw new CustomExceptions("Token incorrect");
                }else {
                    if (user.getToken().isExpired()) {
                        throw new CustomExceptions("Token expired");
                    }else {
                        return true;
                    }
                }
            }
        }
    }
}

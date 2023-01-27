package es.eukariotas.apiservice.service;

import es.eukariotas.apiservice.exceptions.CustomExceptions;
import es.eukariotas.apiservice.persistence.entity.User;
import es.eukariotas.apiservice.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        if (user.getUserEmail() != null && user.getUserPass() != null && user.getUserName() != null) {
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
            if (!user.getUserPass().equals(pass)) {
                throw new IllegalStateException("Password incorrect");
            }else{
                user.setUserEmail(email);
                saveUser(user);
            }
        }
    }
    public void login(String userName, String pass) throws CustomExceptions {
        User user = userRepository.findByUserName(userName).orElse(null);
        if (user == null) {
            throw new CustomExceptions("User not found");
        }else {
            if (!user.getUserPass().equals(pass)) {
                throw new IllegalStateException("Password incorrect");
            }
        }
    }
}

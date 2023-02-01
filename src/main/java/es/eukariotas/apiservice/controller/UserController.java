package es.eukariotas.apiservice.controller;

import es.eukariotas.apiservice.exceptions.CustomExceptions;
import es.eukariotas.apiservice.persistence.entity.Token;
import es.eukariotas.apiservice.persistence.entity.User;
import es.eukariotas.apiservice.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.event.ListDataEvent;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController{

    private final UserService userService;
    @Autowired
    private HttpServletRequest request;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    /**
     * Loguea un usuario
     * @param user nombre de usuario
     * @param password contraseña
     * @return 200 si se ha logueado correctamente, 400 si no se ha podido loguear
     */
    @GetMapping("/login/{userName}/{password}")
    public ResponseEntity<String> login(@RequestParam(value = "userName") String user, @RequestParam("password") String password){
        try {
          Token token = userService.login(user, password);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("token", token.getToken());
            return new ResponseEntity<>("", responseHeaders, HttpStatus.OK);

        } catch (CustomExceptions e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Verifica si el token es correcto o si ha expirado
     * @return 200 si es correcto, 401 si no es correcto
     */
    @GetMapping("/verify")
    public ResponseEntity<String> verify(){
        try {
            String token = request.getHeader("token");
            String userName = request.getHeader("userName");
            if (userService.verifyTokenByUser(userName, token)) {
                return new ResponseEntity<>("", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
            }
        } catch (CustomExceptions e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(){
        try {
            String token = request.getHeader("token");
            String userName = request.getHeader("userName");
            if (userService.verifyTokenByUser(userName, token)) {
                userService.logout(userName, token);
                return new ResponseEntity<>("", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
            }
        } catch (CustomExceptions e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/register")
    public ResponseEntity<String> register(@RequestBody String userName, @RequestParam("password") String password, @RequestParam("email") String email){
        User newUser = new User();
        newUser.setUserName(userName);
        newUser.setPassword(password);
        newUser.setUserEmail(email);

        try {
            userService.registerUser(newUser);
            return new ResponseEntity<>("", HttpStatus.OK);
        } catch (CustomExceptions e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

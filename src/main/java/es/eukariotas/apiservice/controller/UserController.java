package es.eukariotas.apiservice.controller;

import es.eukariotas.apiservice.persistence.entity.User;
import es.eukariotas.apiservice.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.event.ListDataEvent;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController{

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam String user, @RequestParam String pass){
        String body ="";
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        HttpHeaders headers = new HttpHeaders();
        try {
            userService.login(user,pass);
            status = HttpStatus.OK;
            body = "login correcto";
            //TODO añadir un token
        }catch (Exception e){
            //lanzar respuesta con error
            body = "error al iniciar sesion -"+e.getMessage();
        }
        return new ResponseEntity<>(body, headers, status);
    }
}

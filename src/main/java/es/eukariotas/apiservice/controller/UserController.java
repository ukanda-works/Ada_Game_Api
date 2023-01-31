package es.eukariotas.apiservice.controller;

import es.eukariotas.apiservice.exceptions.CustomExceptions;
import es.eukariotas.apiservice.persistence.entity.Token;
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
}

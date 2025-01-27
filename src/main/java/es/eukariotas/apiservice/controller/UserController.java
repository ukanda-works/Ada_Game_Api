package es.eukariotas.apiservice.controller;

import es.eukariotas.apiservice.exceptions.CustomExceptions;
import es.eukariotas.apiservice.persistence.entity.Token;
import es.eukariotas.apiservice.persistence.entity.User;
import es.eukariotas.apiservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.event.ListDataEvent;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    @Autowired
    private HttpServletRequest request;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @Operation(summary = "Devuelve una lista con todos los usuarios almacenados")
    @ApiResponse(responseCode = "200", description = "OK. Devuelve una lista con todos los usuarios almacenados")
    @GetMapping
    public List<User> getUsersList() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Devuelve los usuarios paginados y ordenados según los parámetros pasados")
    @ApiResponse(responseCode = "200", description = "OK. Devuelve los usuarios paginados y ordenados según los parámetros pasados")
    @GetMapping("/page")
    public ResponseEntity<Page<User>> getUsers(@RequestParam(name = "pNum") int page, @RequestParam(name = "pSize") int size, @RequestParam(name = "sort") String sort) {
        //TODO: implementar paginacion y ordenacion ORDER BY
        HttpStatus status = HttpStatus.BAD_REQUEST;
        HttpHeaders headers = new HttpHeaders();
        Page<User> userPage = null;
        try {
            userPage = userService.getAllUsers(PageRequest.of(page, size, Sort.by(sort).descending()));
            status = HttpStatus.OK;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(userPage, headers, status);
    }

    @Operation(summary = "Almacena un nuevo usuario en la base de datos y devuelve un token de autenticación")
    @ApiResponse(responseCode = "200", description = "OK. Almacena un nuevo usuario en la base de datos y devuelve un token de autenticación")
    @PostMapping("/register")
    public ResponseEntity<Token> registerUser(@RequestBody User user) {
        Token token = null;
        HttpStatus status = HttpStatus.BAD_REQUEST;
        HttpHeaders headers = new HttpHeaders();
        try {
            token = userService.registerUser(user);
            status = HttpStatus.OK;
        } catch (CustomExceptions e) {
            status = HttpStatus.BAD_REQUEST;
            //TODO controlar excepciones
        }
        return new ResponseEntity<>(token, headers, status);
    }

    @Operation(summary = "Modifica los datos de un usuario pasado por body")
    @ApiResponse(responseCode = "200", description = "OK. Modifica los datos de un usuario pasado por body")
    @PutMapping("/edit")
    public ResponseEntity<String> editUser(@RequestBody User user) {
        String body ="";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        HttpHeaders headers = new HttpHeaders();
        try {
            userService.updateUser(user.getId(),user);
            body = "usuario editado";
            status = HttpStatus.OK;
        } catch (Exception e) {
            body = "error al editar -"+e.getMessage();
        }
        return new ResponseEntity<>(body, headers, status);
    }

    @Operation(summary = "Devuelve el usuario con mayor puntuación")
    @ApiResponse(responseCode = "200", description = "OK. Devuelve el usuario con mayor puntuación")
    @GetMapping("/best")
    public ResponseEntity<User> getBestUsers() {
        User users = null;
        HttpStatus status = HttpStatus.BAD_REQUEST;
        HttpHeaders headers = new HttpHeaders();
        try {
            users = userService.getBestUsers();
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            //TODO controlar excepciones
        }
        return new ResponseEntity<>(users, headers, status);
    }



}
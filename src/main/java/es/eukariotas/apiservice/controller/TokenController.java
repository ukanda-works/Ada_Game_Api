package es.eukariotas.apiservice.controller;

import es.eukariotas.apiservice.persistence.entity.Token;
import es.eukariotas.apiservice.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/token")
public class TokenController {
    private final TokenService tokenService;

    @Autowired
    private HttpServletRequest request;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Operation(summary = "Genera un token para un usuario pasado por parámetro")
    @ApiResponse(responseCode = "200", description = "Token generado exitosamente", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Token.class))})
    @ApiResponse(responseCode = "400", description = "Error al generar el token")
    @GetMapping("/generate/{user_id}")
    public ResponseEntity<Token> generateToken(@PathVariable("user_id") Long userId) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        HttpHeaders headers = new HttpHeaders();
        Token token = null;
        try {
            token = tokenService.generateToken(userId);
            status = HttpStatus.OK;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(token, headers, status);
    }

    @Operation(summary = "Cierra la sesión de un usuario(da de baja el token de sesion)")
    @ApiResponse(responseCode = "200", description = "Token cerrado exitosamente")
    @ApiResponse(responseCode = "400", description = "Error al cerrar el token")
    @DeleteMapping("/close/{user_id}")
    public ResponseEntity<String> closeToken(@PathVariable("user_id") Long userId) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        HttpHeaders headers = new HttpHeaders();
        String body ="";
        try {
            tokenService.closeToken(userId);
            status = HttpStatus.OK;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(body, headers, status);
    }

    @Operation(summary = "Valida un token para un usuario pasado por parámetro")
    @ApiResponse(responseCode = "200", description = "Token validado exitosamente")
    @ApiResponse(responseCode = "400", description = "Error al validar el token")
    @GetMapping("/validate/{user_id}")
    public ResponseEntity<String> validateToken(@PathVariable("user_id") Long userId, @RequestParam("token") String token) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        HttpHeaders headers = new HttpHeaders();
        String body ="";
        try {
            tokenService.validateToken(token,userId);
            status = HttpStatus.OK;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(body, headers, status);
    }
}

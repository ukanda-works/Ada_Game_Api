package es.eukariotas.apiservice.service;

import es.eukariotas.apiservice.persistence.entity.Token;
import es.eukariotas.apiservice.persistence.entity.User;
import es.eukariotas.apiservice.persistence.repository.TokenRepository;
import es.eukariotas.apiservice.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service

public class TokenService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public TokenService(UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    public Token generateToken(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Token token = Token.createToken(user);
        user.setToken(token);
        userRepository.save(user);
        return tokenRepository.save(token);
    }

    public void closeToken(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Token token = user.getToken();
        tokenRepository.delete(token);
        user.setToken(null);
        userRepository.save(user);
    }

    public void validateToken(String token, Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Token userToken = user.getToken();
        if (userToken == null || !userToken.getToken().equals(token)) {
            throw new RuntimeException("Token no válido");
        }else if (userToken.isExpired()) {
            throw new RuntimeException("Token caducado");
        }
    }
}

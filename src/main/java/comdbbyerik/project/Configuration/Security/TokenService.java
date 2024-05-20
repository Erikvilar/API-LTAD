package comdbbyerik.project.Configuration.Security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.logging.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import comdbbyerik.project.entity.User.UserSession;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    private static final Logger logger = Logger.getLogger(TokenService.class.getName());
    public String generateToken(UserSession userSession) throws Exception{
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                            .withIssuer("API-LTAD")
                            .withSubject(userSession.getLogin())
                            .withExpiresAt(genExpirationDate())
                            .sign(algorithm);
                            return token;
        }catch(JWTVerificationException jwt){
            throw new RuntimeException("-> Error generation token <-", jwt);
        }catch (Exception e) {
          logger.log(Level.SEVERE, "Exceção não esperada ao validar o token", e);
            throw new RuntimeException("Ocorreu um erro inesperado");
        }
    }

    public String ValidateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API-LTAD")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception e) {
            throw new RuntimeException("-> Token Verificado não e valido <-");
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}

package comdbbyerik.project.controllers;

import org.springframework.web.bind.annotation.RestController;

import comdbbyerik.project.Configuration.Security.TokenService;
import comdbbyerik.project.entity.User.AuthenticatedDTOLogin;
import comdbbyerik.project.entity.User.AuthenticatedDTORegister;
import comdbbyerik.project.entity.User.LoginResponseDTO;
import comdbbyerik.project.entity.User.UserSession;
import comdbbyerik.project.repositories.UserRepository.UserRepository;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;
    @PostMapping("/login")
    public ResponseEntity <?> login(@RequestBody @Valid AuthenticatedDTOLogin data) throws Exception{
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((UserSession) auth.getPrincipal());
        return new ResponseEntity<>(new LoginResponseDTO(token), HttpStatus.CREATED);
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid AuthenticatedDTORegister data){
        if(this.userRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserSession newuUserSession = new UserSession(data.login(), encryptedPassword, data.role());

        this.userRepository.save(newuUserSession);
        return new ResponseEntity<>("Usuario Registrado", HttpStatus.ACCEPTED);
    }
    @GetMapping("/registers")
    public ResponseEntity<List<UserSession>> getRegisters(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }
    
}

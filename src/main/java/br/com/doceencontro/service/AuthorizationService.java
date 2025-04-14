package br.com.doceencontro.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.doceencontro.exception.exceptions.EmailEmUsoException;
import br.com.doceencontro.model.Usuario;
import br.com.doceencontro.model.dtos.AuthetinticationDTO;
import br.com.doceencontro.model.dtos.LoginResponseDTO;
import br.com.doceencontro.model.dtos.RegisterDTO;
import br.com.doceencontro.repository.UsuarioRepository;
import br.com.doceencontro.security.TokenService;
import jakarta.validation.Valid;

@Service
public class AuthorizationService implements UserDetailsService{
    @Autowired
    private ApplicationContext context;
    
    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private TokenService tokenService;

    private AuthenticationManager authenticationManager;
    
    private boolean verificarEmailExistente(String email) {
		return userRepository.buscarPorEmail(email).isPresent();
	}
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    } 

    public ResponseEntity<Object> login(AuthetinticationDTO data){
        authenticationManager = context.getBean(AuthenticationManager.class);

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }


    public ResponseEntity<Object> register (RegisterDTO registerDto){
    	if (verificarEmailExistente(registerDto.email())) {
			throw new EmailEmUsoException();
		}
    	
    	String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.senha());
        
        Usuario newUser = new Usuario(registerDto.nome(), registerDto.email(), encryptedPassword);
        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }



    
}
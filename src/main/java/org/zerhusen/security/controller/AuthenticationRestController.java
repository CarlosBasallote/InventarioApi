package org.zerhusen.security.controller;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerhusen.model.dto.LoginUser;
import org.zerhusen.model.dto.NewUserDto;
import org.zerhusen.model.dto.Usuario;
import org.zerhusen.model.security.Authority;
import org.zerhusen.model.security.AuthorityName;
import org.zerhusen.model.security.User;
import org.zerhusen.security.JwtAuthenticationRequest;
import org.zerhusen.security.JwtTokenUtil;
import org.zerhusen.security.JwtUser;
import org.zerhusen.security.service.JwtAuthenticationResponse;
import org.zerhusen.service.UserServicio;

@RestController
public class AuthenticationRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;
    
    @Autowired
    private UserServicio userServicio;
    
    

    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        // Reload password post-security so we can generate the token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }
    
    
    @PostMapping("/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody Usuario usuario) {
    	
    	// 1. Recibimos los datos del nuevo usuario
    	// 2. Lo almacenamos en la base de datos
    	User newUser = userServicio.registrarUsuario(usuario);
    	// 3. Lo autenticamos
    	authenticate(newUser.getUsername(), usuario.getPassword());
    	// 4. Devolvemos los datos necesarios
    	final UserDetails userDetails = userDetailsService.loadUserByUsername(newUser.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
    	
    	return ResponseEntity.ok(new NewUserDto(newUser.getId(), newUser.getFirstname(), newUser.getEmail(), token));
    }
    
   /* @PostMapping("/auth/login")
    public ResponseEntity<?> loginUser(@RequestBody Usuario auth) {
    	
    	authenticate(auth.getUsername(), auth.getPassword());
    	// 4. Devolvemos los datos necesarios
    	final UserDetails userDetails = userDetailsService.loadUserByUsername(auth.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
    	
    	return ResponseEntity.ok(new LoginUser(auth.getId(), auth.getFirstname(), auth.getEmail(), token));
    }*/
    
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException{

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		// Reload password post-security so we can generate the token
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);

		User e = userServicio.findByUsername(authenticationRequest.getUsername());
		System.out.println(e.getAuthorities().size());
		String rol = buscarRol(e.getAuthorities());

		// Return the token
		return ResponseEntity.ok(new NewUserDto(e.getId(), e.getFirstname(), e.getEmail(), token));
	}
    
    public String buscarRol(List<Authority> lista) {
		boolean encontrado = false;
		int i = 0;

		while (!encontrado && i < lista.size()) {
			if (lista.get(i).getName() == AuthorityName.ROLE_ADMIN) {
				encontrado = true;
			} else {
				i++;
			}

		}

		if (encontrado) {
			return "ADMIN";
		} else {
			return "USER";
		}
	}

    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    /**
     * Authenticates the user. If something is wrong, an {@link AuthenticationException} will be thrown
     */
    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("User is disabled!", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Bad credentials!", e);
        }
    }
}

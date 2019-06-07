package org.zerhusen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerhusen.model.dto.Usuario;
import org.zerhusen.model.security.Authority;
import org.zerhusen.model.security.AuthorityName;
import org.zerhusen.model.security.User;
import org.zerhusen.security.repository.AuthorityRepository;
import org.zerhusen.security.repository.UserRepository;
import org.zerhusen.service.base.BaseService;

@Service
public class UserServicio extends BaseService<User, Long, UserRepository> {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private AuthorityRepository authorityRepository;

	public User registrarUsuario(Usuario usuario) {
		User newUser = save(new User(usuario.getNombre(), usuario.getEmail(), encoder.encode(usuario.getPassword())));

		Authority authority = authorityRepository.findFirstByName(AuthorityName.ROLE_USER);

		newUser.getAuthorities().add(authority);

		newUser = edit(newUser);

		return newUser;
	}

	public User findByUsername(String username) {
		return repositorio.findByUsername(username);
	}

}

package org.kruger.service;

import java.util.ArrayList;
import java.util.List;

import org.kruger.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.kruger.model.Role;
import org.kruger.model.UserKruger;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	private Logger logger = LoggerFactory.getLogger(UserService.class);

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserKruger user = userRepository.findByUsername(username);
		if (user == null) {
			logger.error("Login failed: user '" + username + "' not found");
			throw new UsernameNotFoundException("Username: " + username + " not found");
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Role role : user.getRoles()) {
			logger.info("Role: ".concat(role.getAuthority()));
			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		}
		if (authorities.isEmpty()) {
			logger.error("Login failed: User '" + username + "' has no roles assigned");
			throw new UsernameNotFoundException("Login failed: usuario '" + username + "' has no roles assigned");
		}
		return new User(user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true, authorities);
	}

}

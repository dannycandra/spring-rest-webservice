package com.yourcompany.webservice.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yourcompany.webservice.model.UserDetailsImpl;
import com.yourcompany.webservice.model.entity.User;
import com.yourcompany.webservice.model.repository.UserRepository;

@Service
public class AppsUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
		final User user = this.userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(email));
		return new UserDetailsImpl(user);
	}

}
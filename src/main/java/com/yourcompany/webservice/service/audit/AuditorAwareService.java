package com.yourcompany.webservice.service.audit;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yourcompany.webservice.model.entity.User;
import com.yourcompany.webservice.model.repository.UserRepository;

@Service
public class AuditorAwareService implements AuditorAware<User> {

	@Autowired
	private UserRepository userRepository;

	@Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
	public Optional<User> getCurrentAuditor() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepository.findByEmail(email);
	}

}

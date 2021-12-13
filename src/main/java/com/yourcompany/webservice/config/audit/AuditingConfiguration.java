package com.yourcompany.webservice.config.audit;

import com.yourcompany.webservice.model.entity.User;
import com.yourcompany.webservice.service.audit.AuditorAwareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditingConfiguration {

	@Autowired
	private AuditorAwareService auditorAwareService;

	@Bean
	public AuditorAware<User> auditorAware() {
		return auditorAwareService;
	}
}
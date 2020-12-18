package com.yourcompany.webservice.config.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

import com.yourcompany.webservice.config.ConfigConstants;

@SuppressWarnings("deprecation")
@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private DefaultTokenServices defaultTokenService;

	@Override
	public void configure(ResourceServerSecurityConfigurer config) {
		config.tokenServices(defaultTokenService);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable()
				.authorizeRequests().antMatchers(ConfigConstants.AUTH_WHITELIST).permitAll().anyRequest()
				.authenticated();
	}
}

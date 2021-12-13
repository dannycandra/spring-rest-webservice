package com.yourcompany.webservice.model;

import java.util.Collection;

import com.yourcompany.webservice.model.entity.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;

	private final User user;

	public UserDetailsImpl(final User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	@Override
	public String getUsername() {
		return this.user.getEmail();
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	// Customize this as you see fit
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList("ROLE_USER");
	}

	@Override
	public boolean isAccountNonExpired() {
		return user.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return user.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return user.isCredentialNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}

}

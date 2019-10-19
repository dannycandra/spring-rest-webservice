package com.yourcompany.webservice.controller.v1;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yourcompany.webservice.model.dto.LoginDto;
import com.yourcompany.webservice.service.security.AuthService;

import io.swagger.annotations.Api;

@Api(tags = "Webservice - authentication endpoint")
@RestController()
@RequestMapping("v1/auth")
public class AuthRestController {

	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<OAuth2AccessToken> login(@RequestBody LoginDto loginDto) throws URISyntaxException {
		return new ResponseEntity<>(authService.authenticateUser(loginDto), HttpStatus.OK);
	}
}

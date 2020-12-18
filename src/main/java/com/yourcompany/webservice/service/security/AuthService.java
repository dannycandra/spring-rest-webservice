package com.yourcompany.webservice.service.security;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.yourcompany.webservice.error.exception.WrongUsernameOrPasswordException;
import com.yourcompany.webservice.model.dto.LoginDto;

/**
 * Authentication service
 * 
 * @author danny
 */
@SuppressWarnings("deprecation")
@Service
public class AuthService implements Serializable {

	private static final Logger log = LoggerFactory.getLogger(AuthService.class);

	private static final long serialVersionUID = 1L;

	@Value("${oauth.client.id}")
	private String oauthClientId;

	@Value("${oauth.secret.key}")
	private String oauthSecretKey;

	@Value("${oauth.server.url}")
	private String oauthServerUrl;

	@Value("${server.servlet.context-path}")
	private String contextPath;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * @param loginDto {@link LoginDto}
	 * @return OAuth2AccessToken {@link OAuth2AccessToken}
	 * @throws URISyntaxException
	 */
	public OAuth2AccessToken authenticateUser(LoginDto loginDto) throws URISyntaxException {

		log.info("authenticate username:{}", loginDto.getUsername());

		String basicAuthString = oauthClientId + ":" + oauthSecretKey;

		// build http request header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.add("Authorization", "Basic " + Base64.getEncoder().encodeToString(basicAuthString.getBytes()));

		// build request body
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("username", loginDto.getUsername());
		body.add("password", loginDto.getPassword());
		body.add("grant_type", "password");

		// create request entity
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(body, headers);

		// build url
		String oauthAuthorizeUrl = oauthServerUrl + contextPath + "/oauth/token";

		ResponseEntity<OAuth2AccessToken> responseEntity;
		try {
			responseEntity = restTemplate.exchange(oauthAuthorizeUrl, HttpMethod.POST, requestEntity,
					OAuth2AccessToken.class);

			if (responseEntity.getStatusCode() != HttpStatus.OK) {
				throw new WrongUsernameOrPasswordException();
			}

			return responseEntity.getBody();
		} catch (Exception e) {
			throw new WrongUsernameOrPasswordException();
		}

	}

}

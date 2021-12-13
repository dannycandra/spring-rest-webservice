package com.yourcompany.webservice.config.oauth;

import java.util.HashMap;
import java.util.Map;

import com.yourcompany.webservice.model.UserDetailsImpl;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

@SuppressWarnings("deprecation")
public class TokenEnhancerImpl implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getUserAuthentication().getPrincipal();

		Map<String, Object> additionalInfo = new HashMap<>();
		additionalInfo.put("userId", userDetailsImpl.getUser().getUserId());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
		return accessToken;
	}
}

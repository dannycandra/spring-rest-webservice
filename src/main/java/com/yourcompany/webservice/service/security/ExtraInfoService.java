package com.yourcompany.webservice.service.security;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;

@SuppressWarnings("deprecation")
@Service
public class ExtraInfoService {

	public Long getUserId(Authentication authentication) {
		Map<String, Object> extraInfos = getExtraInfo(authentication);
		Integer userId = (Integer) extraInfos.get("userId");
		return new Long(userId);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getExtraInfo(Authentication authentication) {
		OAuth2AuthenticationDetails oauthDetails = (OAuth2AuthenticationDetails) authentication.getDetails();
		return (Map<String, Object>) oauthDetails.getDecodedDetails();
	}
}

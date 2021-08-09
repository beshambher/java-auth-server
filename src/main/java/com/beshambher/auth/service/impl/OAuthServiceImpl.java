package com.beshambher.auth.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import com.beshambher.auth.model.SessionUser;
import com.beshambher.auth.service.OAuthService;

@Service
public class OAuthServiceImpl implements OAuthService {

	@Autowired
	private OAuth2AuthorizedClientService authorizedClientService;

	public SessionUser postLogin(Model model, OAuth2AuthenticationToken authentication) {
		 OAuth2AuthorizedClient client = authorizedClientService
			      .loadAuthorizedClient(
			        authentication.getAuthorizedClientRegistrationId(), 
			          authentication.getName());
		 
		 String userInfoEndpointUri = client.getClientRegistration()
				  .getProviderDetails().getUserInfoEndpoint().getUri();

		 RestTemplate restTemplate = new RestTemplate();
	     HttpHeaders headers = new HttpHeaders();
	     headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken().getTokenValue());
	     HttpEntity<String> entity = new HttpEntity<>("", headers);
	     ResponseEntity<Map> response = restTemplate
	      .exchange(userInfoEndpointUri, HttpMethod.GET, entity, Map.class);
	     
		 return getSessionUser(response.getBody());
	}
	
	private SessionUser getSessionUser(Map<String, String> userData) {
		String avatar = userData.getOrDefault("avatar_url", "") + userData.getOrDefault("picture", "");
		SessionUser user = new SessionUser(avatar, userData.get("email"), userData.get("name"));
		return user;
	}

}

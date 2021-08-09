package com.beshambher.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beshambher.auth.model.SessionUser;
import com.beshambher.auth.service.OAuthService;

@RestController
public class OAuthController {

	@Autowired
	private OAuthService oauthService;
	
	@GetMapping("/oauth2/login/success")
	public SessionUser getLoginInfo(Model model, OAuth2AuthenticationToken authentication) {
	   return oauthService.postLogin(model, authentication);
	}
}

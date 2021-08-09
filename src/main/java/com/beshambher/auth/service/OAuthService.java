package com.beshambher.auth.service;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.ui.Model;

import com.beshambher.auth.model.SessionUser;

public interface OAuthService {

	public SessionUser postLogin(Model model, OAuth2AuthenticationToken authentication);

}

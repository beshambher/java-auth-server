package com.beshambher.auth.controller;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.beshambher.auth.model.SessionUser;
import com.beshambher.auth.service.OAuthService;

@RestController
public class OAuthController {

	@Autowired
	private OAuthService oauthService;
	
	@GetMapping("/oauth2/login/success/v1")
	public SessionUser getLoginInfo(Model model, OAuth2AuthenticationToken authentication) {
	   return oauthService.postLogin(authentication);
	}

	@GetMapping("/oauth2/login/success")
	public ModelAndView postLoginRedirect(ModelMap model, OAuth2AuthenticationToken authentication) {
		return new ModelAndView("redirect:http://localhost", model);
	}

	@GetMapping("/session/user")
	public SessionUser getLoggedInUser(@AuthenticationPrincipal OAuth2User principal) {
		return new SessionUser(principal.getAttributes());
    }
}

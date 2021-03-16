package com.beshambher.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.authorizeRequests(
				a -> a.antMatchers("/", "/error", "/webjars/**")
				.permitAll().anyRequest().authenticated()
			)
			.logout(l -> l.logoutSuccessUrl("/").permitAll())
			.exceptionHandling(e -> e.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
			.oauth2Login(
				o -> o.failureHandler((request, response, exception) -> {
				    request.getSession().setAttribute("error.message", exception.getMessage());
				    // handler.onAuthenticationFailure(request, response, exception);
	            })
		    );
	}

}

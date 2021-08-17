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
		 	.csrf().disable()
			.authorizeRequests(
				a -> a.antMatchers("/", "/error", "/webjars/**")
				.permitAll().anyRequest().authenticated()
			)
			.exceptionHandling(e -> e.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
			.oauth2Login(
				o -> o.failureHandler((request, response, exception) -> {
					System.err.println(exception.getLocalizedMessage());
				    request.getSession().setAttribute("error.message", exception.getMessage());
				    // handler.onAuthenticationFailure(request, response, exception);
	            })
				.defaultSuccessUrl("/oauth2/login/success")
		    )
			.logout(l -> l
		            .logoutSuccessUrl("/oauth2/login/success").permitAll()
		            .deleteCookies("JSESSIONID")
		            .invalidateHttpSession(true)
		    );
	}

}

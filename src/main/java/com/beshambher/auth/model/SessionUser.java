package com.beshambher.auth.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionUser {

	private String avatar;

	private String email;
	
	private String name;

	public SessionUser(Map<String, Object> principalAttributes) {
		avatar = (String) principalAttributes.getOrDefault("avatar_url", "") + (String) principalAttributes.getOrDefault("picture", "");
		email = (String) principalAttributes.get("email");
		name = (String) principalAttributes.get("name");
	}
}

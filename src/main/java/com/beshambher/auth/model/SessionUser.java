package com.beshambher.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SessionUser {

	private String avatar;

	private String email;
	
	private String name;

}

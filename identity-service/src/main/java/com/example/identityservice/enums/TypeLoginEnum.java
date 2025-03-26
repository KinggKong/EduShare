package com.example.identityservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TypeLoginEnum {
	USERNAME_PASSWORD_LOGIN(1, "Login with username and password"),
	GOOGLE_LOGIN(2, "Login with Google");

	private final int key;
	private final String desc;

}

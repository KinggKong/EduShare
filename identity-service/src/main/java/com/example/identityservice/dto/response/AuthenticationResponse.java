package com.example.identityservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

public class AuthenticationResponse {

	@Getter
	@Setter
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	@Accessors(chain = true)
	public static class LoginResponse {
		private String accessToken;
		private String refreshToken;
		private String username;
		private Long accountId;
	}

	@Getter
	@Setter
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	@Accessors(chain = true)
	public static class RegisterResponse {
		private Long id;
		private String username;
		private String roleName;
	}
}

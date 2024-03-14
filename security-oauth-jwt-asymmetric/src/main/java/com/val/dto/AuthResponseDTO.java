package com.val.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponseDTO {
	
	@JsonProperty("access_token")
	private String accessToken;

	@JsonProperty("token_type")
	private TokenType tokenType;
	
	@JsonProperty("access_token_expiry")
	private int accessTokenExpiry;
	
	@JsonProperty("user_name")
	private String username;

}

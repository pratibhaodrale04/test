package com.val.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.val.config.jwtConfig.JwtTokenGenerator;
import com.val.dto.AuthResponseDTO;
import com.val.dto.TokenType;
import com.val.repo.IUserInfoRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {

	@Autowired
    private IUserInfoRepo userInfoRepo;
	@Autowired
    private JwtTokenGenerator jwtTokenGenerator;
	
    public AuthResponseDTO getJwtTokensAfterAuthentication(Authentication authentication) {
        try
        {
            var userInfo = userInfoRepo.findByUsername(authentication.getName())
                    .orElseThrow(()->{
                        log.error("[AuthService:userSignInAuth] User :{} not found",authentication.getName());
                        return new ResponseStatusException(HttpStatus.NOT_FOUND,"USER NOT FOUND ");});


            String accessToken = jwtTokenGenerator.generateAccessToken(authentication);

            log.info("[AuthService:userSignInAuth] Access token for user:{}, has been generated",userInfo.getUsername());
            return  AuthResponseDTO.builder()
                    .accessToken(accessToken)
                    .accessTokenExpiry(15 * 60)
                    .username(userInfo.getUsername())
                    .tokenType(TokenType.Bearer)
                    .build();


        }catch (Exception e){
            log.error("[AuthService:userSignInAuth]Exception while authenticating the user due to :"+e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Please Try Again");
        }
    }
}
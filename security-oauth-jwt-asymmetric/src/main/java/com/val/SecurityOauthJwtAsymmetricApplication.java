package com.val;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.val.config.RSA.RSAKeyRecord;

@EnableConfigurationProperties(RSAKeyRecord.class)
@SpringBootApplication
public class SecurityOauthJwtAsymmetricApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityOauthJwtAsymmetricApplication.class, args);
	}

}

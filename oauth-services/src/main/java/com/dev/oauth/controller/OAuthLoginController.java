package com.dev.oauth.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.dev.core.services.UserService;
import com.dev.oauth.model.SocialUser;
import com.dev.oauth.services.GoogleAuthenticationService;
import com.dev.oauth.services.SocialMediaCredentialsService;
import com.dev.oauth.services.SocialUserService;

@RestController
@RequestMapping(value = "oauth-login")
public class OAuthLoginController {

	@Autowired
	SocialMediaCredentialsService socialMediaCredentialsService;

	@Autowired
	GoogleAuthenticationService googleAuthenticationService;

	@Autowired
	SocialUserService socialUserService;

	@Autowired
	UserService userService;

	@RequestMapping(value = "/google/initiate")
	public RedirectView loginViaGoogleInitiate(
			@RequestParam(value = "state", required = false, defaultValue = "") String state) {

		String url = socialMediaCredentialsService.getGoogleAuthRedirectURL(state);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(url);
		return redirectView;
	}

	@RequestMapping(value = "/google/complete")
	public ResponseEntity<Void> loginViaGoogleComplete(@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "state", required = false, defaultValue = "") String state,
			HttpServletRequest request) throws IOException {

		if (code != null) {
			SocialUser socialUser = googleAuthenticationService.getAccessTokenByCode(code);
			socialUser = socialUserService.saveUserAndSocialUser(socialUser);
			socialUserService.authenticateUser(socialUser.getUser());
		}
		BodyBuilder bb = ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT);
		bb.header(HttpHeaders.LOCATION, "/spring-jpa-login-via-google/home");
		return bb.build();
	}

}

package com.dev.oauth.services;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dev.core.custom.exception.BadRequestException;
import com.dev.core.services.SpringRestTemplate;
import com.dev.oauth.enums.SocialMediaProvider;
import com.dev.oauth.model.SocialMediaCredentials;
import com.dev.oauth.model.SocialUser;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class GoogleAuthenticationService {

	@Autowired
	private SpringRestTemplate springRestTemplate;

	@Autowired
	private SocialMediaCredentialsService socialMediaCredentialsService;

	public void setSpringRestTemplate(SpringRestTemplate springRestTemplate) {
		this.springRestTemplate = springRestTemplate;
	}

	private SpringRestTemplate getSpringRestTemplate() {
		return springRestTemplate;
	}

	public SocialUser getUserInfo(String url, Map<String, ?> urlVariables, String accessToken)
			throws BadRequestException {
		ResponseEntity<String> responseEntity = getSpringRestTemplate().getForEntity(buildUri(url, accessToken),
				String.class, urlVariables);
		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			return buildGoogleUser(responseEntity.getBody(), accessToken);
		}
		throw new BadRequestException(responseEntity.getStatusCode().toString());
	}

	private SocialUser buildGoogleUser(String body, String accessToken) {

		JsonObject jo = JsonParser.parseString(body).getAsJsonObject();
		if (!jo.get("email_verified").getAsString().equals("true")) {
			throw new BadRequestException("Email not verified");
		}
		SocialUser userSocialAuthDetail = new SocialUser();
		userSocialAuthDetail.setAccessToken(accessToken);
		userSocialAuthDetail.setEmail(jo.get("email").getAsString());
		userSocialAuthDetail.setFirstName(jo.get("given_name").getAsString());
		userSocialAuthDetail.setLastName(jo.get("family_name").getAsString());
		userSocialAuthDetail.setPictureUrl(jo.get("picture").getAsString());
		userSocialAuthDetail.setSocialMediaProvider(SocialMediaProvider.GOOGLE);
		userSocialAuthDetail.setUserSocialId(jo.get("sub").getAsString());
		return userSocialAuthDetail;
	}

	private String buildUri(String url, String accessToken) {
		return url + accessToken;
	}

	public SocialUser getAccessTokenByCode(String code) throws IOException {
		LinkedHashMap<String, String> uriVariables = new LinkedHashMap<String, String>();
		SocialMediaCredentials credential = socialMediaCredentialsService
				.findBySocialMediaProviderAndEnabled(SocialMediaProvider.GOOGLE, true);
		GoogleTokenResponse c = new GoogleAuthorizationCodeTokenRequest(new NetHttpTransport(),
				JacksonFactory.getDefaultInstance(), credential.getClientId(), credential.getClientSecret(), code,
				credential.getAuthorizedRedirectURL()).execute();
		String accessToken = c.getAccessToken();

		SocialUser socialUser = getUserInfo(credential.getUserDetailAPI(), uriVariables, accessToken);
		return socialUser;
	}

}

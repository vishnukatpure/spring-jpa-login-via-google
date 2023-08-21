package com.dev.oauth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.dev.oauth.enums.SocialMediaProvider;
import com.dev.core.model.EntityBase;

@Table(name = "social_media_credentials")
@Entity
@EnableJpaAuditing
public class SocialMediaCredentials extends EntityBase {

	private static final long serialVersionUID = 2734653030248401544L;
	@Enumerated(EnumType.STRING)
	private SocialMediaProvider socialMediaProvider;
	@Column(columnDefinition = "TEXT")
	private String clientId;
	@Column(columnDefinition = "TEXT")
	private String clientSecret;
	@Column(columnDefinition = "TEXT")
	private String appId;
	private String scopes;
	private String authorizedRedirectURL;
	@Column(columnDefinition = "tinyint(1) default 1")
	private boolean enabled;
	private String userDetailAPI;
	private String accessRequestAPI;
	private String accessTokenAPI;
	private String userCancelApi;
	private String emailAddressAPI;

	public String getAccessTokenAPI() {
		return accessTokenAPI;
	}

	public void setAccessTokenAPI(String accessTokenAPI) {
		this.accessTokenAPI = accessTokenAPI;
	}

	public String getUserDetailAPI() {
		return userDetailAPI;
	}

	public String getAccessRequestAPI() {
		return accessRequestAPI;
	}

	public void setAccessRequestAPI(String accessRequestAPI) {
		this.accessRequestAPI = accessRequestAPI;
	}

	public void setUserDetailAPI(String userDetailAPI) {
		this.userDetailAPI = userDetailAPI;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAuthorizedRedirectURL() {
		return authorizedRedirectURL;
	}

	public void setAuthorizedRedirectURL(String authorizedRedirectURL) {
		this.authorizedRedirectURL = authorizedRedirectURL;
	}

	public String getScopes() {
		return scopes;
	}

	public void setScopes(String scopes) {
		this.scopes = scopes;
	}

	public SocialMediaProvider getSocialMediaProvider() {
		return socialMediaProvider;
	}

	public void setSocialMediaProvider(SocialMediaProvider socialMediaProvider) {
		this.socialMediaProvider = socialMediaProvider;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUserCancelApi() {
		return userCancelApi;
	}

	public void setUserCancelApi(String userCancelApi) {
		this.userCancelApi = userCancelApi;
	}

	public String getEmailAddressAPI() {
		return emailAddressAPI;
	}

	public void setEmailAddressAPI(String emailAddressAPI) {
		this.emailAddressAPI = emailAddressAPI;
	}

}

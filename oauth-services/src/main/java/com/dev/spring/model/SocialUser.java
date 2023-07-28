package com.dev.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.dev.spring.enums.SocialMediaProvider;

@Table(name = "social_user")
@Entity
@EnableJpaAuditing
public class SocialUser extends EntityBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -309616462130396581L;
	@OneToOne
	private User user;
	@Enumerated(EnumType.STRING)
	private SocialMediaProvider socialMediaProvider;
	private String userSocialId;
	private String email;
	private String firstName;
	private String lastName;
	private String url;
	@Column(columnDefinition = "TEXT")
	private String accessToken;
	private String pictureUrl;
	private String contactNumberCountryCode;
	private String contactNumber;
	private String middleName;

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public SocialMediaProvider getSocialMediaProvider() {
		return socialMediaProvider;
	}

	public void setSocialMediaProvider(SocialMediaProvider socialMediaProvider) {
		this.socialMediaProvider = socialMediaProvider;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserSocialId() {
		return userSocialId;
	}

	public void setUserSocialId(String userSocialId) {
		this.userSocialId = userSocialId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContactNumberCountryCode() {
		return contactNumberCountryCode;
	}

	public void setContactNumberCountryCode(String contactNumberCountryCode) {
		this.contactNumberCountryCode = contactNumberCountryCode;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

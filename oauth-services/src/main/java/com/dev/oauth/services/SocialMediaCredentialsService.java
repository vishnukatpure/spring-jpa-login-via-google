package com.dev.oauth.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.oauth.enums.SocialMediaProvider;
import com.dev.oauth.model.SocialMediaCredentials;
import com.dev.oauth.repository.SocialMediaCredentialsRepository;

@Service
public class SocialMediaCredentialsService {

	@Autowired
	SocialMediaCredentialsRepository mediaCredentialsRepository;

	@Transactional
	public List<SocialMediaCredentials> getAllSocialMediaCredentials() {
		return (List<SocialMediaCredentials>) mediaCredentialsRepository.findAll();
	}

	@Transactional
	public SocialMediaCredentials getById(Long id) {
		return mediaCredentialsRepository.findById(id).get();
	}

	@Transactional
	public void deleteSocialMediaCredentials(Long id) {
		mediaCredentialsRepository.deleteById(id);
	}

	@Transactional
	public boolean addSocialMediaCredentials(SocialMediaCredentials mediaCredentials) {
		return mediaCredentialsRepository.save(mediaCredentials) != null;
	}

	@Transactional
	public boolean updateSocialMediaCredentials(SocialMediaCredentials mediaCredentials) {
		return mediaCredentialsRepository.save(mediaCredentials) != null;
	}

	public String getGoogleAuthRedirectURL(String state) {
		List<SocialMediaCredentials> credentials = mediaCredentialsRepository
				.findBySocialMediaProviderAndEnabled(SocialMediaProvider.GOOGLE, true);
		SocialMediaCredentials credential = credentials.get(0);
		return credential.getAccessRequestAPI() + "&redirect_uri=" + credential.getAuthorizedRedirectURL()
				+ "&response_type=code&client_id=" + credential.getClientId() + "&state=" + state;
	}

	public SocialMediaCredentials findBySocialMediaProviderAndEnabled(SocialMediaProvider google, boolean b) {
		List<SocialMediaCredentials> credentials = mediaCredentialsRepository
				.findBySocialMediaProviderAndEnabled(SocialMediaProvider.GOOGLE, true);
		return credentials.get(0);
	}

}

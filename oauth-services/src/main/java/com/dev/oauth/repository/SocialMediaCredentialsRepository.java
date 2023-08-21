package com.dev.oauth.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dev.oauth.enums.SocialMediaProvider;
import com.dev.oauth.model.SocialMediaCredentials;

public interface SocialMediaCredentialsRepository extends CrudRepository<SocialMediaCredentials, Long> {

	public List<SocialMediaCredentials> findBySocialMediaProviderAndEnabled(SocialMediaProvider socialMediaProvider,
			boolean enabled);
}

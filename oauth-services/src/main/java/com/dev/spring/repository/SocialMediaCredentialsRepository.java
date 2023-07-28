package com.dev.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dev.spring.enums.SocialMediaProvider;
import com.dev.spring.model.SocialMediaCredentials;

public interface SocialMediaCredentialsRepository extends CrudRepository<SocialMediaCredentials, Long> {

	public List<SocialMediaCredentials> findBySocialMediaProviderAndEnabled(SocialMediaProvider socialMediaProvider,
			boolean enabled);
}

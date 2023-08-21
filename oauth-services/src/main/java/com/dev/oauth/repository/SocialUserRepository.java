package com.dev.oauth.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dev.oauth.model.SocialUser;

public interface SocialUserRepository extends CrudRepository<SocialUser, Long> {

	List<SocialUser> findByuserSocialIdOrEmail(String userSocialId, String email);

}

package com.dev.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dev.spring.model.SocialUser;

public interface SocialUserRepository extends CrudRepository<SocialUser, Long> {

	List<SocialUser> findByuserSocialIdOrEmail(String userSocialId, String email);

}

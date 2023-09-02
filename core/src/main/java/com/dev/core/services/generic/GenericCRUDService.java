package com.dev.core.services.generic;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.dev.core.dto.ResponseDTO;
import com.dev.core.enums.StatusEnum;
import com.dev.core.model.User;
import com.dev.core.services.UserService;

@Service
public abstract class GenericCRUDService {

	private static final ModelMapper mapper = new ModelMapper();

	@Autowired
	UserService userService;

	public ResponseDTO bindResponse(Object dto) {
		return new ResponseDTO().message("Success").object(dto).status(StatusEnum.SUCCESS);
	}

	public ModelMapper getMapper() {
		return mapper;
	}

	public User getLoggedInUser() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = null;
		if (userDetails != null) {
			if (userDetails instanceof User)
				user = (User) userDetails;
			else
				user = userService.findByUsername(userDetails.getUsername());
		}
		return user;
	}

}
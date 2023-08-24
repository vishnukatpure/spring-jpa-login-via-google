package com.dev.core.services.generic;

import org.springframework.stereotype.Service;

import com.dev.core.dto.ResponseDTO;
import com.dev.core.enums.StatusEnum;

@Service
public abstract class GenericCRUDService {


	public ResponseDTO bindResponse(Object dto) {
		return new ResponseDTO().message("Success").object(dto).status(StatusEnum.SUCCESS);
	}

}
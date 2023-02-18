package com.sct.convert;

import org.springframework.stereotype.Component;

import com.sct.Entity.UserEntity;
import com.sct.dto.UserDTO;

@Component
public class UserConert extends ConvertCommon<UserEntity, UserDTO>{

	@Override
	public UserEntity covertEntity(UserDTO d) {
		UserEntity userEntity = modelMapper.map(d, UserEntity.class);
		return userEntity;
	}

	@Override
	public UserDTO convertDto(UserEntity e) {
		UserDTO dto = modelMapper.map(e, UserDTO.class);
		return dto;
	}
}

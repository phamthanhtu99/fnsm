package com.sct.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDTO {
	
	private Long id;
	private String code;
	private String name;
}

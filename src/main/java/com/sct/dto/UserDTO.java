package com.sct.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserDTO {

	private Long id;

	private String name;

	private String userName;

	private String pass;

	private String address;

	private Integer check_login;

	private String opt;

	private Integer status;

	private Date date;

	private String ip;
}

package com.sct.Utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.sct.dto.RoleDTO;

@Component
public class Directional {

	public static String direction(HttpServletRequest request) {
		String url = "";
		List<RoleDTO> roleDTOs = (List<RoleDTO>) SessionUtils.getSession("role", request);
		for (RoleDTO roleDTO : roleDTOs) {
			switch (roleDTO.getName()) {
			case "ADMIN":
				System.out.println("ok");
				break;
			case "USER":

				break;
			case "STAFF":
				break;

			case "GUEST":
				break;
			default:
				break;
			}

		}
		return null;

	}

	public static void main(String[] args) {
		System.out.println(Auth.Role.ADMIN.toString());
	}
}

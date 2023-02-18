package com.sct.config;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sct.Utils.Auth;
import com.sct.Utils.Auth.Role;
import com.sct.dto.RoleDTO;

@Component
public class AuthInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();

			Auth roleAnnotation = AnnotationUtils.findAnnotation(method,
					Auth.class);
			Auth.Role role = roleAnnotation != null ? roleAnnotation.role()
					: null;

			HttpSession session = request.getSession();
			boolean isLogined = session.getAttribute("isLogin") != null ? (Boolean) session
					.getAttribute("isLogin") : false;

			if (role != null) {
				// chưa đăng nhập chuyển hướng sang trang login để đăng nhập
				if (!isLogined) {
					response.sendRedirect("login");
					return false;
				} else {
					List<RoleDTO> loginRole = (List<RoleDTO>) session.getAttribute("role");
					boolean chk_role = checkRole(loginRole, role);
					// - không thoả mãn điều kiện dưới chuyển hướng sang trang
					if (role != Auth.Role.LOGIN && !chk_role) {
						// response.sendRedirect("deny?url=\""+
						// request.getRequestURL().toString() + "?"+
						// request.getQueryString() + "\"&role=" + role);
						return false;
					}
				}
			}
		}

		return true;
	}

	private boolean checkRole(List<RoleDTO> roles, Role role) {
		for (RoleDTO roleDTO : roles) {
			if (roleDTO.getName().contains(role.toString())) {
				return true;
			}
		}
		return false;

	}
}

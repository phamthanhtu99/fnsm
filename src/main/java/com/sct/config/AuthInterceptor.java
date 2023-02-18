package com.sct.config;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

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
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();

			Auth roleAnnotation = AnnotationUtils.findAnnotation(method, Auth.class);
			// roleAnnotation.role()
			
			if(roleAnnotation == null || "LOGIN".equals(roleAnnotation.role().toString())) {
				return true;
			}
			
			Auth.Role[] roles = roleAnnotation.roles();
			List<Auth.Role> list = new ArrayList<>();

			if (roles.length == 1) {
				Auth.Role role = roleAnnotation.role();
				getListRole(role, list);
			}else {
				getListRole(roles, list);
			}
			

			HttpSession session = request.getSession();
			boolean isLogined = session.getAttribute("isLogin") != null ? (Boolean) session.getAttribute("isLogin") : false;
			
			
			if (list != null || !list.isEmpty()) {
				// chưa đăng nhập chuyển hướng sang trang login để đăng nhập
				if (!isLogined) {
					response.sendRedirect("login");
					return false;
				} else {
					List<RoleDTO> loginRole = (List<RoleDTO>) session.getAttribute("role");
					
					if("LOGIN".equals(list.get(0).toString()) && loginRole == null) {
						return true;
					}
					boolean chk_role = checkRole(loginRole, list);
					
					if (!chk_role ) {
						return false;
					}
					// - không thoả mãn điều kiện dưới chuyển hướng sang trang
//					if (role != Auth.Role.LOGIN && !chk_role ) {
//						// response.sendRedirect("deny?url=\""+
//						// request.getRequestURL().toString() + "?"+
//						// request.getQueryString() + "\"&role=" + role);
//						return false;
//					}
				}
			}
		}

		return true;
	}

	private boolean checkRole(List<RoleDTO> roles,  List<Auth.Role> role) {
		for (RoleDTO item : roles) {
			for (Role item2 : role) {
				String role_login = item.getName();
				String role_auth = item2.toString().trim();
				if(role_login.equals(role_auth.toString())) {
					return true;
				}
			}
		}
		return false;

	}

	private void getListRole(Object ob, List<Auth.Role> list) {
		if (ob instanceof Object[]) {
			Auth.Role[] roles = (Role[]) ob;
			Stream<Auth.Role> streams = Arrays.stream(roles);
			streams.forEach(x->{
				list.add(x);
			});
		}else {
		    list.add((Role) ob);
		}
	}
}

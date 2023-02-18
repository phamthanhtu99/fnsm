package com.sct.Utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;




@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD})
public @interface Auth {
	 public enum Role {
	        LOGIN, ADMIN, USER , STAFF
	    };
	    public Role[] roles()  default Role.LOGIN;
	    public Role role() default Role.LOGIN; // default = @Auth()
}

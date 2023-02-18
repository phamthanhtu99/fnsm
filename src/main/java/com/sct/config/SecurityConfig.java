package com.sct.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.sct.security.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;

	@Autowired
	UserDetailsServiceImpl userDt;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/user").permitAll();
		
		http.authorizeRequests().antMatchers("/api/**").hasAuthority("USER_ROLE");
		http.authorizeRequests().antMatchers("/loginSuccess").hasAnyAuthority("USER_ROLE","ADMIN_ROLE");
		http.authorizeRequests().antMatchers("/admin/**").hasAuthority("ADMIN_ROLE");

		http.authorizeRequests().and().formLogin().loginPage("/login")
				.loginProcessingUrl("/login_handle")
				.usernameParameter("userName").passwordParameter("pass")
				.defaultSuccessUrl("/loginSuccess")
				.failureUrl("/login?error=true").permitAll();
		
		http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true).sessionRegistry(sessionRegistry());
		
	}

	@Bean
	public SessionRegistry sessionRegistry() {
	    SessionRegistry sessionRegistry = new SessionRegistryImpl();
	    return sessionRegistry;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public static ServletListenerRegistrationBean httpSessionEventPublisher() {
	    return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
	}

	@Bean
	public PersistentTokenRepository tokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
		jdbcTokenRepository.setDataSource(dataSource);
		return jdbcTokenRepository;
	}
	
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userDt).passwordEncoder(passwordEncoder());
	}
}

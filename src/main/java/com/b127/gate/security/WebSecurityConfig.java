package com.b127.gate.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.b127.gate.security.service.SecuredUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAccessDeniedHandler customAccessDeniedHandler;

	@Autowired
	private SecuredUserDetailsService securedUserDetailsService;

	@Autowired
	private CustomAuthenicationSuccessHandler customAuthenicationSuccessHandler;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/", "/login", "/logout", "/register", "**/css/**", "**/js/**", "**/scss/**", "**/vendor/**")
				.permitAll().and().authorizeRequests().antMatchers("/user/**")
				.access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')").and().authorizeRequests().antMatchers("/admin/**")
				.access("hasRole('ROLE_ADMIN')").and().formLogin().loginPage("/").loginProcessingUrl("/login")
				.usernameParameter("username").passwordParameter("password")
				.successHandler(customAuthenicationSuccessHandler).failureUrl("/login?error").permitAll().and().logout()
				.invalidateHttpSession(true).clearAuthentication(true).logoutUrl("/logout").logoutSuccessUrl("/").permitAll().and()
				.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler).and().csrf().disable();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(securedUserDetailsService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
	
	

}

package com.jrp.sma.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


	
	@Bean
	public BCryptPasswordEncoder passwordEncoder () {
		
	
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		
		return new  ManageUserDetailService();
	}
	
	
	
	
	
	
	public DaoAuthenticationProvider authenticationProvider () {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		 
		return authProvider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		
		auth.authenticationProvider(authenticationProvider());
	}
	
	 
	 
	 @Override
	    protected void configure (HttpSecurity http) throws Exception {
	    	 http.csrf().ignoringAntMatchers("/api/**","/students/**").and()
	    	.authorizeRequests()    	
	    	.antMatchers("/activities/new").hasAuthority("ADMIN")
	    	.antMatchers("/actvities/save").hasAuthority("ADMIN")
	        .antMatchers("/", "/**").permitAll()//everyone can access homepage if they are already authenticated and everything after '/'
	        .and().formLogin()
	        
			.usernameParameter("email")
			.permitAll()
			.and().logout().permitAll()
			.and()
			.rememberMe()
			.key("Abcdefghi_1")
			.tokenValiditySeconds(7 * 86400);
	    	 
	    	 //.antMatchers("/students/save").hasAuthority("ADMIN")//1st priority
	    	// csrf().ignoringAntMatchers("/api/**","/students/**").and()
	    	
	    }    

	
	
	
}

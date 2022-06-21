package com.jrp.sma.security;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;

import com.allanditzel.springframework.security.web.csrf.CsrfTokenResponseHeaderBindingFilter;


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

	UserDetailsService userDetails;
	
	
	
	
	
	public DaoAuthenticationProvider authenticationProvider () {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		 
		return authProvider;
	}
	
	//@Autowired
    //private CustomAuthenticationProvider authProvider;
//
//    @Autowired
//    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authProvider);
//    }
//	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		
		auth.authenticationProvider(authenticationProvider());
	}
	
	 
	 
	 @Override
	    protected void configure (HttpSecurity http) throws Exception {
		 
		 //CsrfTokenResponseHeaderBindingFilter csrfFilter = new CsrfTokenResponseHeaderBindingFilter(); 
		 CookieCsrfTokenRepository csrfTokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
		 

	    	 http.httpBasic().and().csrf().csrfTokenRepository(csrfTokenRepository).and().headers().frameOptions()
	    	 .deny().and()
	    	 .authorizeRequests()
	    	.antMatchers("/api/**").hasAuthority("ADMIN")
	    	.antMatchers("/students/**").hasAuthority("ADMIN")
	    	.antMatchers("/activities/new").hasAuthority("ADMIN")
	    	.antMatchers("/activities/save").hasAuthority("ADMIN")
	        .antMatchers("/", "/**").permitAll()//everyone can access homepage if they are already authenticated and everything after '/'
	        .and().formLogin()
	        
			.usernameParameter("email")
			.permitAll()
			.and()
			.rememberMe()
			
			.tokenValiditySeconds(7 * 86400)
			.and().logout().permitAll();
			
	    	
	    	 //System.out.println("------------------------------" + csrfTokenRepository.);
	    	 //http.addFilterAfter(csrfFilter, CsrfFilter.class);
	    	 //.antMatchers("/students/save").hasAuthority("ADMIN")//1st priority
	    	// csrf().ignoringAntMatchers("/api/**","/students/**").and()
	    	 //.ignoringAntMatchers("/api/**","/students/**")
	    	
	    }    
	 
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//	  
//	  CookieCsrfTokenRepository csrfTokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
//	http.httpBasic().and().authorizeRequests().antMatchers("/api/users/**").hasRole("ADMIN").and()
//              .csrf().csrfTokenRepository(csrfTokenRepository);
// }
//	
    
    
	
	
}

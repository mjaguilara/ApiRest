package com.examen.demo.security;

import java.util.Date;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity; 

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		prePostEnabled = true,
		securedEnabled = true,
		jsr250Enabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private String secretKey = "mySecretKey";

		
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic()
    		.and()
			.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/usuarios").permitAll()
			.anyRequest().authenticated();
		
		http.csrf().disable();
	}

	
    
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	  	auth.inMemoryAuthentication()
	              .withUser("user").password("{noop}password").authorities("USER")
	              .and()
	              .withUser("admin").password("{noop}password").authorities("USER", "ADMIN");
	  }
	
	public String createToken(String username, List<String> roles) {
		
		Claims claims = Jwts.claims().setSubject(username);
	    claims.put("authorities", roles);

		
		String token = Jwts
				.builder()
				.setSubject(username)
				.setClaims(claims)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1500000))
				.signWith(SignatureAlgorithm.HS256,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}

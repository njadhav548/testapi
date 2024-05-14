package com.javainuse.bootecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import com.javainuse.bootecommerce.service.CustomUserDetailsService;

//@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
/*	//>>InMemmoryAuthentication
 
  	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("user1").password("password1").roles("USER");
	}

	//Security For All API
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable();
//		http.authorizeHttpRequests().anyRequest().authenticated().and().httpBasic();
//	}
	
	//Security URL Based
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable();
//		http.authorizeHttpRequests().antMatchers("/users/**").authenticated().and().httpBasic();
//	}	
	
	//Security Based On Role
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeHttpRequests().antMatchers("/users/**").hasAnyRole("ADMIN").anyRequest()
		.authenticated().and().httpBasic();
	}
	
	//No Out PasswordEncoder
	@Bean
	public static NoOpPasswordEncoder passwordEncoder(){
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	} 
*/

/*  //>>JPA User Authentication
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encodePWD());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
//		http
//			.authorizeRequests()
//				.antMatchers("/users/**")
//				.authenticated()
//				.anyRequest()
//				.permitAll()		
//				.and()
//			.authorizeRequests()
//				.antMatchers("/secure/**")
//				.authenticated()
//				.anyRequest()
//				.hasAnyRole("ADMIN")
//				.and()
//			.formLogin()
//				.permitAll();
		http
		.authorizeRequests()
			.antMatchers("/users/**").authenticated()
			.antMatchers("/users/add").authenticated()
			.and()
		.httpBasic()
			.and()
		.formLogin()
			.permitAll();		
	}
	
	//Bcrypt Password Encoder
	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
		
	}
*/
	
/*	//>> LDAP Authentication
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
	      .ldapAuthentication()
	        .userDnPatterns("uid={0},ou=people")
	        .groupSearchBase("ou=groups")
	        .contextSource()
	          .url("ldap://localhost:8389/dc=springframework,dc=org")
	          .and()
	        .passwordCompare()
	          .passwordEncoder(new BCryptPasswordEncoder())
	          .passwordAttribute("userPassword");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
	      .authorizeRequests()
	        .anyRequest().fullyAuthenticated()
	        .and()
	      .formLogin();
	}
	
	//Bcrypt Password Encoder
		@Bean
		public BCryptPasswordEncoder encodePWD() {
			return new BCryptPasswordEncoder();
			
		}
*/	
	
/*	//>> JWT Token  ** Not Done Need to study again
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService);
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable()
				.authorizeRequests().antMatchers("/authenticate").permitAll().
						anyRequest().authenticated();
//						.and().
//						exceptionHandling().and().sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}

	
	@Bean
	public static NoOpPasswordEncoder passwordEncoder(){
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	} 
	
	//Bcrypt Password Encoder
		@Bean
		public BCryptPasswordEncoder encodePWD() {
			return new BCryptPasswordEncoder();
			
		}
*/
}

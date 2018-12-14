package cn.com.taiji.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cn.com.taiji.support.AfterLoginFilter;
import cn.com.taiji.support.AtLoginFilter;
import cn.com.taiji.support.BeforeLoginFilter;
import cn.com.taiji.support.CustomUserDetailService;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
	private CustomUserDetailService customUserDetailService;
   
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {	
//		auth.inMemoryAuthentication()
////		    .passwordEncoder(new BCryptPasswordEncoder())
//		    .withUser("user")
//		    .password(passwordEncoder().encode("123456"))
//		    .roles("USER");
//		auth.inMemoryAuthentication()
////		    .passwordEncoder(new BCryptPasswordEncoder())
//		    .withUser("admin")
//		    .password(passwordEncoder().encode("123456"))
//		    .roles("USER","ADMIN");
		auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
		
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		super.configure(web);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.formLogin()
		    .loginPage("/login")
		    ;
		
		http.addFilterAfter(new AfterLoginFilter(), UsernamePasswordAuthenticationFilter.class)
		    .addFilterBefore(new BeforeLoginFilter(), UsernamePasswordAuthenticationFilter.class)
		    .addFilterAt(new AtLoginFilter(), UsernamePasswordAuthenticationFilter.class);
	}

}

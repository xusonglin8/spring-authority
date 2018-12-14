package cn.com.taiji.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cn.com.taiji.config.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import cn.com.taiji.validate.code.image.ImageCodeFilter;
import cn.com.taiji.validate.code.sms.SmsCodeFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserService myUserService;

    @Autowired
    private MyAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailHandler authenticationFailHandler;

    @Autowired
    private ImageCodeFilter imageCodeFilter;

    @Autowired
    private SmsCodeFilter smsCodeFilter;

    // 注入短信登录的相关配置
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(imageCodeFilter, UsernamePasswordAuthenticationFilter.class) // 将ImageCodeFilter过滤器设置在UsernamePasswordAuthenticationFilter之前
                .addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/authentication/*","/login/*","/code/*") // 不需要登录就可以访问
                .permitAll()
                .antMatchers("/user/**").hasAnyRole("USER") // 需要具有ROLE_USER角色才能访问
                .antMatchers("/admin/**").hasAnyRole("ADMIN") // 需要具有ROLE_ADMIN角色才能访问
                .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/authentication/login") // 访问需要登录才能访问的页面，如果未登录，会跳转到该地址来
                    .loginProcessingUrl("/authentication/form")
                    .successHandler(authenticationSuccessHandler)
                    .failureHandler(authenticationFailHandler)
                ;
        http.apply(smsCodeAuthenticationSecurityConfig);
    }

    // 密码加密方式
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    // 重写方法，自定义用户
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("lzc").password(new BCryptPasswordEncoder().encode("123456")).roles("ADMIN","USER");
//        auth.inMemoryAuthentication().withUser("zhangsan").password(new BCryptPasswordEncoder().encode("123456")).roles("USER");
        auth.userDetailsService(myUserService); // 注入MyUserService，这样SpringSecurity会调用里面的loadUserByUsername(String s)
    }
}
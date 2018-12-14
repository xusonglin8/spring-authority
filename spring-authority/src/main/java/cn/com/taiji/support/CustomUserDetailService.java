package cn.com.taiji.support;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import cn.com.taiji.domain.Role;
import cn.com.taiji.domain.UserInfo;
import cn.com.taiji.service.UserInfoService;

@Component
public class CustomUserDetailService implements UserDetailsService{
	@Autowired
    private UserInfoService userInfoService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		
		UserInfo userInfo=userInfoService.findByUsername(username);
		if(userInfo==null) {
			throw new UsernameNotFoundException("user is not found");
		}
		String password=userInfo.getPassword();
		List<GrantedAuthority> authorities=new ArrayList();
//		GrantedAuthority authority=new SimpleGrantedAuthority(userInfo.getRole().name());
//		authorities.add(authority);
		
		for(Role role:userInfo.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		UserDetails user=new User(username,password,authorities);
		return user;
	}
}

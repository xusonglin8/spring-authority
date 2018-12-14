package cn.com.taiji.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.domain.UserInfo;
import cn.com.taiji.respository.UserInfoRespository;
import cn.com.taiji.service.UserInfoService;
@Service
public class UserInfoServceImpl implements UserInfoService{
    @Autowired
	 private UserInfoRespository  userInfoRespository;

	@Override
	public UserInfo findByUsername(String username) {
		return userInfoRespository.findByUsername(username);
		
	}
	 
}

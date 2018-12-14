package cn.com.taiji.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.com.taiji.domain.UserInfo;

public interface UserInfoRespository extends JpaRepository<UserInfo,Long>{
     UserInfo findByUsername(String username);
}

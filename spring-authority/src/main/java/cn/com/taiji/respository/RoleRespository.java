package cn.com.taiji.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.com.taiji.domain.Role;

public interface RoleRespository extends JpaRepository<Role, Long>{
  
}

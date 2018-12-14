package cn.com.taiji.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.com.taiji.domain.Permission;

public interface PermissionRespository extends JpaRepository<Permission, Long>{

}

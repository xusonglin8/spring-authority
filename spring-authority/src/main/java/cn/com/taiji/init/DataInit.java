package cn.com.taiji.init;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cn.com.taiji.domain.Permission;
import cn.com.taiji.domain.Role;
import cn.com.taiji.domain.UserInfo;
import cn.com.taiji.respository.PermissionRespository;
import cn.com.taiji.respository.RoleRespository;
import cn.com.taiji.respository.UserInfoRespository;

@Service
public class DataInit {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserInfoRespository userInfoRespository;
	@Autowired
	private RoleRespository roleInfoRespository;
	@Autowired
	private PermissionRespository permissionRespository;

	@PostConstruct
	public void dataInit() {
		Role USER=new Role();
		USER.setName("ROLE_USER");
		USER.setDescription("");
		roleInfoRespository.save(USER);
		
		Role ADMIN=new Role();
		ADMIN.setName("ROLE_ADMIN");
		ADMIN.setDescription("");
		roleInfoRespository.save(ADMIN);
		
		List<Role> r1=new ArrayList<Role>();
		r1.add(USER);
		UserInfo user = new UserInfo();
		user.setUsername("xusong");
		user.setPassword(passwordEncoder.encode("123456"));
		user.setRoles(r1);
		userInfoRespository.save(user);
		
		r1=new ArrayList<Role>();
		r1.add(USER);
		r1.add(ADMIN);
		UserInfo admin = new UserInfo();
		admin.setUsername("xusonglin");
		admin.setPassword(passwordEncoder.encode("123456"));
		admin.setRoles(r1);
		userInfoRespository.save(admin);
		
		

        //permission.
        r1 = new ArrayList<>();
        r1.add(USER);
        Permission permission1 = new Permission();
        permission1.setUrl("/helloUser");
        permission1.setName("普通用户URL");
        permission1.setDescription("普通用户的访问路径");
        permission1.setRoles(r1);
        permissionRespository.save(permission1);

        Permission permission2 = new Permission();
        permission2.setUrl("/helloAdmin");
        permission2.setName("管理员URL");
        permission2.setDescription("管理员的访问路径");
        List<Role> roles2 = new ArrayList<>();
        roles2.add(ADMIN);
        permission2.setRoles(roles2);
        permissionRespository.save(permission2);
	}
}

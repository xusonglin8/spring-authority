package cn.com.taiji.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Service;

import cn.com.taiji.domain.Permission;
import cn.com.taiji.domain.Role;
import cn.com.taiji.respository.PermissionRespository;
import cn.com.taiji.service.PermissionService;
@Service
public class PermissionServiceImpl implements PermissionService{
    @Autowired
	private Map<String,Collection<ConfigAttribute>> permissionMap=null;
    @Autowired
    private PermissionRespository permissionRespository;
	@Override
	public Map<String, Collection<ConfigAttribute>> getPermissionMap() {
		if(permissionMap==null||permissionMap.size()==0) {
		initPermissions();
		}
		return permissionMap;
	}
   @PostConstruct
   public void initPermissions() {
	 //key=url,value=[role1,role2]
	 //程序启动后，立即自动 执行
       //初始化permissionMap
	 		permissionMap=new HashMap();
	 		List<Permission> permissions=permissionRespository.findAll();
	 				for(Permission p:permissions) {
	 					Collection<ConfigAttribute> collection=new ArrayList<ConfigAttribute>();
	                     for(Role role:p.getRoles()){
	                    	 ConfigAttribute cfg=new SecurityConfig(role.getName());
	                    	 collection.add(cfg);
	                    	 permissionMap.put(p.getUrl(),collection);
	                     }				
	 				}
   }
}

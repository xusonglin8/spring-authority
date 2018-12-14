package cn.com.taiji.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class UserInfo {
	@Id
	@GeneratedValue
	private long uid;
	private String username;
	private String password;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="UserInfo_Role",joinColumns={@JoinColumn(name="uid")},inverseJoinColumns={@JoinColumn(name="rid")})
	private List<Role> roles;
//	@Enumerated(EnumType.STRING)
//	private Role role;
//
//	public enum Role {
//		ROLE_ADMIN, ROLE_USER, ROLE_FOO
//	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}



}

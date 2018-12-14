package cn.com.taiji.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
@Entity
public class Permission {
	@Id
	@GeneratedValue
private long id;
private String name;
private String description;
private String url;
private long pid;
@ManyToMany
@JoinTable(name="Permission_Role",joinColumns={@JoinColumn(name="id")},inverseJoinColumns={@JoinColumn(name="rid")})
private List<Role> roles;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public long getPid() {
	return pid;
}
public void setPid(long pid) {
	this.pid = pid;
}
public List<Role> getRoles() {
	return roles;
}
public void setRoles(List<Role> roles) {
	this.roles = roles;
}

}

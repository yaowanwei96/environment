package com.springboot.environment.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by yww on 2018/9/2.
 */
@Data
@Entity
@Table(name = "euser")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})

public class User  implements Serializable{
    private static final long serialVersionUID = -509438491019594820L;

    @Id
    @GeneratedValue
    private Integer user_id;
    private String password;
    private String user_mail;

    @Column(name="user_name",unique = true)
    private String user_name;
    private String user_tel;


    //指定了多对多的关系，fetch=FetchType.LAZY属性表示在多的那一方通过延迟加载的方式加载对象（默认不是延迟加载）
    @ManyToMany(fetch= FetchType.LAZY)//立即从数据库中进行加载数据;
    @JoinTable(name = "e_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns ={@JoinColumn(name = "role_id") })
    private List<Role> roles;// 一个用户具有多个角色

    //角色 -- 权限关系：多对多关系;mappedBy同样指定由对方来进行维护关联关系
    @ManyToMany(mappedBy = "users",fetch= FetchType.LAZY)
//    @JoinTable(name="e_user_group",joinColumns={@JoinColumn(name="user_id")},inverseJoinColumns={@JoinColumn(name="group_id")})
    private List<Group> groups;

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_mail() {
        return user_mail;
    }

    public void setUser_mail(String user_mail) {
        this.user_mail = user_mail;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_tel() {
        return user_tel;
    }

    public void setUser_tel(String user_tel) {
        this.user_tel = user_tel;
    }

    public User(String password, String user_mail, String user_name, String user_tel) {
        this.password = password;
        this.user_mail = user_mail;
        this.user_name = user_name;
        this.user_tel = user_tel;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", password='" + password + '\'' +
                ", user_mail='" + user_mail + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_tel='" + user_tel + '\'' +
                ", roles=" + roles +
                ", groups=" + groups +
                '}';
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}

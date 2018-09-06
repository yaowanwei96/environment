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
@Table(name = "erole")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})

public class Role  implements Serializable {
    private static final long serialVersionUID = -509438491019594820L;

    @Id
    @GeneratedValue
    private int role_id;
    private String role_name;
    private String role_authority;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    // 用户 - 角色关系定义;多对多
    @ManyToMany(mappedBy = "roles" ,fetch = FetchType.LAZY)
//    @JoinTable(name="e_user_role",joinColumns={@JoinColumn(name="role_id")},inverseJoinColumns={@JoinColumn(name="user_id")})
    private List<User> users;// 一个角色对应多个用户

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_authority() {
        return role_authority;
    }

    public void setRole_authority(String role_authority) {
        this.role_authority = role_authority;
    }

    public Role() {
    }

    @Override
    public String toString() {
        return "Role{" +
                "role_id=" + role_id +
                ", role_name='" + role_name + '\'' +
                ", role_authority='" + role_authority + '\'' +
                '}';
    }
}

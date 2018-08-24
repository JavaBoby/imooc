package com.imooc.user.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 刘海洋
 * @version V1.0
 * @Title: UserInfo
 * @Description: 用户实体类
 * @date 2018/7/30 14:58
 */
@Data
@ToString
@Table(name = "user_info")
@Entity
public class UserInfo {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "openid")
    private String openid;

    @Column(name = "role")
    private Integer role;
}

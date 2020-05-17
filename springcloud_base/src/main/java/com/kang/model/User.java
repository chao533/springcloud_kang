package com.kang.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "tb_user")
public class User {
    /**
     * 主键
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 登录名
     */
    @Column(name = "login_name")
    private String loginName;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 真实姓名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 联系方式
     */
    private String tel;

    /**
     * 头像
     */
    private String icon;

    /**
     * 性别（0：男  1：女）
     */
    private Boolean gender;

    /**
     * 生日
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    /**
     * 邮件
     */
    private String email;

    /**
     * 地址
     */
    private String addr;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 删除标识（0：正常  1：删除）
     */
    @Column(name = "is_del")
    private Boolean isDel;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取登录名
     *
     * @return login_name - 登录名
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置登录名
     *
     * @param loginName 登录名
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * 获取密码
     *
     * @return pwd - 密码
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * 设置密码
     *
     * @param pwd 密码
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * 获取真实姓名
     *
     * @return user_name - 真实姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置真实姓名
     *
     * @param userName 真实姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取联系方式
     *
     * @return tel - 联系方式
     */
    public String getTel() {
        return tel;
    }

    /**
     * 设置联系方式
     *
     * @param tel 联系方式
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 获取头像
     *
     * @return icon - 头像
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置头像
     *
     * @param icon 头像
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取性别（0：男  1：女）
     *
     * @return gender - 性别（0：男  1：女）
     */
    public Boolean getGender() {
        return gender;
    }

    /**
     * 设置性别（0：男  1：女）
     *
     * @param gender 性别（0：男  1：女）
     */
    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    /**
     * 获取生日
     *
     * @return birthday - 生日
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置生日
     *
     * @param birthday 生日
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取邮件
     *
     * @return email - 邮件
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮件
     *
     * @param email 邮件
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取地址
     *
     * @return addr - 地址
     */
    public String getAddr() {
        return addr;
    }

    /**
     * 设置地址
     *
     * @param addr 地址
     */
    public void setAddr(String addr) {
        this.addr = addr;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取删除标识（0：正常  1：删除）
     *
     * @return is_del - 删除标识（0：正常  1：删除）
     */
    public Boolean getIsDel() {
        return isDel;
    }

    /**
     * 设置删除标识（0：正常  1：删除）
     *
     * @param isDel 删除标识（0：正常  1：删除）
     */
    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    /**
     * 获取角色id
     *
     * @return role_id - 角色id
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置角色id
     *
     * @param roleId 角色id
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
package com.gason.demo.springdatajpa.entity;

import javax.persistence.*;

/**
 * @program: springdatajpa
 * @description: 用户实体
 * @author: Gason Lee
 * @create: 2019-01-16 09:47
 **/
@Entity
@Table(name = "jx_user")
public class User {
    @Id
    private Long id;

    @Column(length = 32)
    private String name;

    @Column(length = 32)
    private String account;

    @Column(length = 64,name = "password")
    private String pwd;

    private Byte isDelete;

    @Transient
    private String info;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getInfo() {
        return this.name + "\t"+this.pwd+"\t"+this.id;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", account='").append(account).append('\'');
        sb.append(", pwd='").append(pwd).append('\'');
        sb.append(", isDelete=").append(isDelete);
        sb.append(", info='").append(getInfo()).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

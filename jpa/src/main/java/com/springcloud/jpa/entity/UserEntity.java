package com.springcloud.jpa.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @description:
 * @author: Think
 * @date: 2020-05-31 09:48
 */
@Entity
@Table(name = "b_user", schema = "blog", catalog = "")
public class UserEntity {
    private long id;
    private String account;
    private String username;
    private String password;
    private Integer age;
    private String address;
    private Timestamp lastLoginTime;
    private Boolean enabled;
    private Byte accountNotExpired;
    private Byte accountNotLocked;
    private Byte credentialsNotExpired;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "account")
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "last_login_time")
    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Basic
    @Column(name = "enabled")
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "account_not_expired")
    public Byte getAccountNotExpired() {
        return accountNotExpired;
    }

    public void setAccountNotExpired(Byte accountNotExpired) {
        this.accountNotExpired = accountNotExpired;
    }

    @Basic
    @Column(name = "account_not_locked")
    public Byte getAccountNotLocked() {
        return accountNotLocked;
    }

    public void setAccountNotLocked(Byte accountNotLocked) {
        this.accountNotLocked = accountNotLocked;
    }

    @Basic
    @Column(name = "credentials_not_expired")
    public Byte getCredentialsNotExpired() {
        return credentialsNotExpired;
    }

    public void setCredentialsNotExpired(Byte credentialsNotExpired) {
        this.credentialsNotExpired = credentialsNotExpired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id &&
                Objects.equals(account, that.account) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(age, that.age) &&
                Objects.equals(address, that.address) &&
                Objects.equals(lastLoginTime, that.lastLoginTime) &&
                Objects.equals(enabled, that.enabled) &&
                Objects.equals(accountNotExpired, that.accountNotExpired) &&
                Objects.equals(accountNotLocked, that.accountNotLocked) &&
                Objects.equals(credentialsNotExpired, that.credentialsNotExpired);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account, username, password, age, address, lastLoginTime, enabled, accountNotExpired, accountNotLocked, credentialsNotExpired);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", enabled=" + enabled +
                ", accountNotExpired=" + accountNotExpired +
                ", accountNotLocked=" + accountNotLocked +
                ", credentialsNotExpired=" + credentialsNotExpired +
                '}';
    }
}

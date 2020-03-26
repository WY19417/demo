package com.test2.demo.Po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    private  String vip;

    private  String fishball;

    private  String fishchi;

    private String mail;

    private String phone;

    private String tools;


    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getFishball() {
        return fishball;
    }

    public void setFishball(String fishball) {
        this.fishball = fishball;
    }

    public String getFishchi() {
        return fishchi;
    }

    public void setFishchi(String fishchi) {
        this.fishchi = fishchi;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


    public String getTools() {
        return tools;
    }

    public void setTools(String tools) {
        this.tools = tools;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", vip='" + vip + '\'' +
                ", fishball='" + fishball + '\'' +
                ", fishchi='" + fishchi + '\'' +
                ", mail='" + mail + '\'' +
                ", phone='" + phone + '\'' +
                ", tools='" + tools + '\'' +
                '}';
    }
}

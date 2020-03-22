package com.spring.starter.model;

import javax.persistence.*;

@Entity
@Table(name = "end_user")
public class EndUser {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String email;

    @Column
    private String username;

    @Column
    private String passDigest;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassDigest() {
        return passDigest;
    }

    public void setPassDigest(String passDigest) {
        this.passDigest = passDigest;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

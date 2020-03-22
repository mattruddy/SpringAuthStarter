package com.pwa.store.model;

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
    private String passDigest;

    @OneToOne
    private Page page;

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

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}

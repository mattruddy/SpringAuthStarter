package com.pwa.store.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "page")
public class Page {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private EndUser endUser;

    @OneToMany
    private List<Service> services;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EndUser getEndUser() {
        return endUser;
    }

    public void setEndUser(EndUser endUser) {
        this.endUser = endUser;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
}

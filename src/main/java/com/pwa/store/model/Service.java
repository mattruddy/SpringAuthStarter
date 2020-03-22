package com.pwa.store.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @Column
    private Category category;

    @Column
    private String description;

    @Column
    private String link;

    @ManyToOne
    private Page page;

    @OneToMany
    private List<Image> images;

    public enum Category {
        GAME,
        DATING,
        SOCAIL,
        EDUCATIONAL,
        FOOD,
        BUSINESS,
        NEWS,
        SHOPPING,
        TOOL
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}

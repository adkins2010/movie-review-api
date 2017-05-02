package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movie {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    private long year;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getYear() {
        return year;
    }

    public Movie setId(long id) {
        this.id = id;
        return this;
    }

    public Movie setYear(long year) {
        this.year = year;
        return this;
    }

}

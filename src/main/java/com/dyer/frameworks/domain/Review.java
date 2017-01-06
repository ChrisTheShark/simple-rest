package com.dyer.frameworks.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Simple domain <code>class</code> represents a Review.
 */
@Entity
public class Review extends BaseEntity {

    @ManyToOne
    private Course course;

    private int rating;

    private String description;

    protected Review() {
        super();
    }

    public Review(Course course, int rating, String description) {
        this();
        this.course = course;
        this.rating = rating;
        this.description = description;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package com.filmosaurus.javaLearning.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "movies")
public class Movie {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String director;

    private String release_date;

    private String plot;

    public Movie(String title, String director, String release_date, String plot) {
        this.id = null;
        this.title = title;
        this.director = director;
        this.release_date = release_date;
        this.plot = plot;
    }

    @Override
  public String toString() {
    return "Movie{" + "id=" + id + ", title='" + title + '\'' + ", director='" + director + '\'' + ", release_date='"
      + release_date + '\'' +", plot='" + plot + '\'' + '}';
  }
}

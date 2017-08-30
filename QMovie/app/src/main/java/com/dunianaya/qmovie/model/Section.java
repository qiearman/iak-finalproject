package com.dunianaya.qmovie.model;

import java.util.List;

/**
 * Created by Qi on 8/29/2017.
 */

public class Section {
    String title;
    List<Movie> movies;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}

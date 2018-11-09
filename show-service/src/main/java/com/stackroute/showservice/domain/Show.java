package com.stackroute.showservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


//@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Document
public class Show {

    @Id
    private String cityName;
    private List<Movie> movies;

    public Show( ){
    }

    public Show(String cityName, List<Movie> movies) {
        this.cityName = cityName;
        this.movies = movies;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "Show{" +
                "cityName='" + cityName + '\'' +
                ", movies=" + movies +
                '}';
    }
}

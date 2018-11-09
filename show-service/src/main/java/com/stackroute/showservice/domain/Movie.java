package com.stackroute.showservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class Movie {

    private int movieId;

    private String movieTitle;
    private String yearOfRelease;
    private String posterUrl;
    private String ratingArray;
    private double averageRating;
    private String language;
    private String certificate;
    private  String genre;
    private List<Theatre> theatres;

    public Movie(){
    }

    public Movie(int movieId, String movieTitle, String yearOfRelease, String posterUrl, String ratingArray,
                 double averageRating, String language, String certificate, String genre, List<Theatre> theatres) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.yearOfRelease = yearOfRelease;
        this.posterUrl = posterUrl;
        this.ratingArray = ratingArray;
        this.averageRating = averageRating;
        this.language = language;
        this.certificate = certificate;
        this.genre = genre;
        this.theatres = theatres;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int mvoieId) {
        this.movieId = mvoieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public List<Theatre> getTheatres() {
        return theatres;
    }

    public void setTheatres(List<Theatre> theatres) {
        this.theatres = theatres;
    }

    public String getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(String yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getRatingArray() {
        return ratingArray;
    }

    public void setRatingArray(String ratingArray) {
        this.ratingArray = ratingArray;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieTitle='" + movieTitle + '\'' +
                ", yearOfRelease='" + yearOfRelease + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                ", ratingArray='" + ratingArray + '\'' +
                ", averageRating=" + averageRating +
                ", language='" + language + '\'' +
                ", certificate='" + certificate + '\'' +
                ", genre='" + genre + '\'' +
                ", theatres=" + theatres +
                '}';
    }
}

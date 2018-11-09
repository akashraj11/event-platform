package com.stackroute.showservice.service;

import com.stackroute.showservice.domain.Movie;
import com.stackroute.showservice.domain.Show;
import com.stackroute.showservice.domain.Theatre;
import com.stackroute.showservice.exceptions.ShowAlreadyExistsException;
import com.stackroute.showservice.exceptions.ShowNotFoundException;

import java.util.List;

public interface ShowService {

    public Show addShow(Show show) throws ShowAlreadyExistsException;

    public List<Show> getAllShows();

    public List<Show> updateShow(String cityName, int movieId, Theatre theatre) throws ShowNotFoundException;

    public List<Show> delteShow(String cityName, int movieId,int theatreId) throws ShowNotFoundException;

    public List<Movie> getMoviesByCityName(String cityName) throws ShowNotFoundException;
}

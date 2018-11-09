package com.stackroute.distributor.service;

import com.stackroute.distributor.domain.City;
import com.stackroute.distributor.domain.Distributor;
import com.stackroute.distributor.domain.Movie;
import com.stackroute.distributor.exceptions.*;

import java.util.List;

public interface DistributorService {
    public Distributor getDistributorByEmail(String email) throws DistributorNotFoundException;
    public Distributor addDistributor(Distributor distributor) throws DistributorAlreadyExistException,DistributorNotFoundException;
    public List<Distributor> getAllDistributors();
    public boolean deleteDistributor(String email) throws DistributorNotFoundException;
    public Distributor addNewCity(String email, City city) throws DistributorNotFoundException, CityAlreadyExistException;
    public Distributor DeleteCity(String email, String cityName) throws DistributorNotFoundException, CityNotFoundException;
    public Distributor addNewMovie(String email, String cityName, Movie movie) throws DistributorNotFoundException, CityNotFoundException, MovieAlreadyExistException;
    public Distributor DeleteMovie(String email, String cityName,int movieId) throws DistributorNotFoundException, CityNotFoundException,MovieNotFoundException;
}

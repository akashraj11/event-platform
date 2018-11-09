package com.stackroute.distributor.service;

import com.stackroute.distributor.domain.City;
import com.stackroute.distributor.domain.Distributor;
import com.stackroute.distributor.domain.Movie;
import com.stackroute.distributor.exceptions.*;
import com.stackroute.distributor.repository.DistributorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistributorServiceImpl implements DistributorService {

    private DistributorRepository  distributorRepository;

    @Autowired
    public DistributorServiceImpl(DistributorRepository distributorRepository) {
        this.distributorRepository = distributorRepository;
    }

    public Distributor getDistributorByEmail(String email) throws DistributorNotFoundException {

        if(distributorRepository.findByemail(email)==null) {
            String msg="movie-service.controller.movieNotFoundException";
            throw new DistributorNotFoundException(msg);

        }
        Distributor distributor=distributorRepository.findByemail(email) ;
        return distributor;
    }

    public Distributor addDistributor(Distributor distributor) throws DistributorAlreadyExistException {
        Distributor checkDistributor=distributorRepository.findByemail(distributor.getEmail());
  if(checkDistributor!=null) {
           String msg="movie-service.controller.movieAlreadyExistException";
           throw new DistributorAlreadyExistException(msg);
      }
       Distributor savedDistributor = distributorRepository.save(distributor);
        if(savedDistributor == null)
        {
            String msg="movie-service.controller.movieAlreadyExistException";
            throw new DistributorAlreadyExistException(msg);
        }
        return savedDistributor;
    }
    public List<Distributor> getAllDistributors(){
        return (List<Distributor>)distributorRepository.findAll();
    }

    public boolean deleteDistributor(String email) throws DistributorNotFoundException  {
        if(getDistributorByEmail(email)==null) {
            String msg="movie-service.controller.movieNotFoundException";
            throw new DistributorNotFoundException(msg);
        }
        else {
            Distributor deletedDistributor = getDistributorByEmail(email);
            distributorRepository.delete(deletedDistributor);
            return true;
        }
    }

    public Distributor addNewCity(String email, City city) throws DistributorNotFoundException, CityAlreadyExistException
    {
        if(getDistributorByEmail(email)==null) {
            String msg="movie-service.controller.movieNotFoundException";
            throw new DistributorNotFoundException(msg);
        }
        else
        {
            Distributor distributor=getDistributorByEmail(email);
            ArrayList<City> cities=new ArrayList<City>();
            cities=distributor.getCities();
            for(int i=0;i<cities.size();i++)
            {
                if(city.getCityName().equals(cities.get(i).getCityName()))
                    throw new CityAlreadyExistException("Existing City");
            }
            cities.add(city);
            distributor.setCities(cities);
            distributorRepository.save(distributor);
            return distributor;
        }
    }
    public Distributor DeleteCity(String email, String cityName) throws DistributorNotFoundException, CityNotFoundException
    {
        if(getDistributorByEmail(email)==null) {
            String msg="movie-service.controller.movieNotFoundException";
            throw new DistributorNotFoundException(msg);
        }
        else
        {
            Distributor distributor=getDistributorByEmail(email);
            ArrayList<City> cities=new ArrayList<City>();
            cities=distributor.getCities();
            for(int i=0;i<cities.size();i++)
            {
                if(cityName.equals(cities.get(i).getCityName()))
                {
                    cities.remove(i);
                    distributor.setCities(cities);
                    distributorRepository.save(distributor);
                    return distributor;
                }
            }
            throw new CityNotFoundException(cityName+"not exists");

        }
    }


    public Distributor addNewMovie(String email, String cityName, Movie movie) throws DistributorNotFoundException, CityNotFoundException, MovieAlreadyExistException
    {
        if(getDistributorByEmail(email)==null) {
            String msg="movie-service.controller.movieNotFoundException";
            throw new DistributorNotFoundException(msg);
        }
        else
        {
            Distributor distributor=getDistributorByEmail(email);
            ArrayList<City> cities=new ArrayList<City>();
            cities=distributor.getCities();
            for(int i=0;i<cities.size();i++)
            {
                if(cityName.equals(cities.get(i).getCityName()))
                {
                   ArrayList<Movie> movies=cities.get(i).getMovies();
                   for(int j=0;j<movies.size();j++)
                   {
                       if((movie.getMovieId()==movies.get(j).getMovieId()))
                           throw new MovieAlreadyExistException(movie.getMovieTitle()+" Already Exists");
                   }
                   movies.add(movie);
                   cities.get(i).setMovies(movies);
                   distributor.setCities(cities);
                   distributorRepository.save(distributor);
                   return distributor;
                }
            }
            throw new CityNotFoundException(cityName+"not exists");
        }
    }

    public Distributor DeleteMovie(String email, String cityName,int movieId) throws DistributorNotFoundException, CityNotFoundException,MovieNotFoundException
    {
        if(getDistributorByEmail(email)==null) {
            String msg="movie-service.controller.movieNotFoundException";
            throw new DistributorNotFoundException(msg);
        }
        else
        {
            Distributor distributor=getDistributorByEmail(email);
            ArrayList<City> cities=new ArrayList<City>();
            cities=distributor.getCities();
            for(int i=0;i<cities.size();i++)
            {
                if(cityName.equals(cities.get(i).getCityName()))
                {
                    ArrayList<Movie> movies=cities.get(i).getMovies();
                    for (int j=0;j<movies.size();j++)
                    {
                        if(movieId==movies.get(j).getMovieId())
                        {
                            movies.remove(j);
                            cities.get(i).setMovies(movies);
                            distributor.setCities(cities);
                            distributorRepository.save(distributor);
                            return distributor;
                        }

                    }
                    throw new MovieNotFoundException("movie not found");
                }
            }
            throw new CityNotFoundException(cityName+"not exists");

        }
    }




}

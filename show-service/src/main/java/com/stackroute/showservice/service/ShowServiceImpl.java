package com.stackroute.showservice.service;

import com.stackroute.showservice.domain.Movie;
import com.stackroute.showservice.domain.Show;
import com.stackroute.showservice.domain.Theatre;
import com.stackroute.showservice.exceptions.ShowAlreadyExistsException;
import com.stackroute.showservice.exceptions.ShowNotFoundException;
import com.stackroute.showservice.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@PropertySource("file:/home/cgi/IdeaProjects/moviecruiser/src/main/resources/application.properties")
public class ShowServiceImpl implements ShowService {

    private ShowRepository showRepository;

    @Autowired
    public ShowServiceImpl(ShowRepository showRepository){

        this.showRepository=showRepository;
    }

    @Override
    public Show addShow(Show show) throws ShowAlreadyExistsException {

        int movieId=show.getMovies().get(0).getMovieId();
//        System.out.println(movieId);
        int flag=0;
        int flag2=0;
        if(showRepository.existsById(show.getCityName())) {

            Show show1 = showRepository.findById(show.getCityName()).get();

            for (int i = 0; i < show1.getMovies().size(); i++) {
                if (show1.getMovies().get(i).getMovieId() == movieId) {
                    flag2=1;

                    if (show1.getMovies().get(i).getTheatres().indexOf(show.getMovies().get(0).getTheatres().get(0)) == -1) {
                        show1.getMovies().get(i).getTheatres().add(show.getMovies().get(0).getTheatres().get(0));
                        flag = 1;
                    }
                    break;
                }
            }
            if(flag2==0){
                show1.getMovies().add(show.getMovies().get(0));
                flag=1;
            }
            showRepository.save(show1);
            show=show1;
        }
        else {
//            System.out.println("City not exists case");
            showRepository.save(show);
            flag=1;
        }

        if(flag ==0) {
            throw new ShowAlreadyExistsException("Show Already exists");
        }
        //System.out.println("Hello case");
        return show;
    }

    @Override
    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    @Override
    public List<Show> updateShow(String cityName, int movieId, Theatre theatre) throws ShowNotFoundException {
        if(!showRepository.existsById(cityName)){
            throw new ShowNotFoundException("Show not found");
        }
        Show show1=showRepository.findById(cityName).get();
        int flag=0;
        for(int i=0;i<show1.getMovies().size();i++){

            if(show1.getMovies().get(i).getMovieId() == movieId){
                    List<Theatre> theatreList = show1.getMovies().get(i).getTheatres();
                    for(int j=0;j<theatreList.size();j++){
                        if(theatreList.get(j).getTheatreId() == theatre.getTheatreId()){
                            flag=1;
                            show1.getMovies().get(i).getTheatres().get(j).setTimings(theatre.getTimings());
                            break;
                        }
                    }
                break;
            }
        }
        showRepository.save(show1);
        if(flag == 0){
            throw new ShowNotFoundException("Show not found to Update");
        }
        return showRepository.findAll();
    }

    @Override
    public List<Show> delteShow(String cityName, int movieId, int theatreId) throws ShowNotFoundException {

        if(!showRepository.existsById(cityName)){
            throw new ShowNotFoundException("Show not found");
        }
        Show show1=showRepository.findById(cityName).get();
        int flag=0;
        for(int i=0;i<show1.getMovies().size();i++){

            if(show1.getMovies().get(i).getMovieId() == movieId){

                    List<Theatre> theatreList = show1.getMovies().get(i).getTheatres();
                    for(int j=0;j<theatreList.size();j++){
                        if(theatreList.get(j).getTheatreId() == theatreId){
                            flag=1;
                            show1.getMovies().get(i).getTheatres().remove(j);
                            break;
                        }
                    }
                break;
            }
        }
        showRepository.save(show1);
        if(flag == 0){
            throw new ShowNotFoundException("Show not found to Delete");
        }
        return showRepository.findAll();
    }

    @Override
    public List<Movie> getMoviesByCityName(String cityName) throws ShowNotFoundException {
        if(showRepository.existsById(cityName)){}
        else throw new ShowNotFoundException("No shows to show in this city");
        return showRepository.getShowByCityName(cityName).getMovies();
    }
}

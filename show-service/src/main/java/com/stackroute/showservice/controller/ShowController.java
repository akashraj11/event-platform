package com.stackroute.showservice.controller;

import com.stackroute.showservice.domain.Movie;
import com.stackroute.showservice.domain.Show;
import com.stackroute.showservice.exceptions.ShowAlreadyExistsException;
import com.stackroute.showservice.exceptions.ShowNotFoundException;
import com.stackroute.showservice.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(value = "api/v1")
public class ShowController {
    private ShowService showService;

    @Autowired
    public ShowController(ShowService showService){
        this.showService=showService;
    }

    @PostMapping("show")
    public ResponseEntity<?> saveShow(@RequestBody Show show){
        System.out.println("IN add controller");
        ResponseEntity responseEntity;
        try{
            Show show1=showService.addShow(show);
            responseEntity=new ResponseEntity<Show>(show1, HttpStatus.CREATED);
        }catch (ShowAlreadyExistsException ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }catch (Exception ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("show")
    public ResponseEntity<?> getAllShows(){
        ResponseEntity responseEntity;
        try{
            List<Show> showList=showService.getAllShows();
            responseEntity=new ResponseEntity<List<Show>>(showList, HttpStatus.OK);
        }catch (Exception ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("show/{id}")
    public ResponseEntity<?> getMoviesByCityName(@PathVariable(value = "id") String id){

        ResponseEntity responseEntity;

        try{
            List<Movie> movieList=showService.getMoviesByCityName(id);
            responseEntity = new ResponseEntity<List<Movie>>(movieList, HttpStatus.OK);
        }catch (ShowNotFoundException ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch (Exception ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

    @PutMapping("show/{id}")
    public ResponseEntity<?> updateShow(@RequestBody Show show,@PathVariable(value = "id")  int id){

        ResponseEntity responseEntity;

        try{
            List<Show> showList=showService.updateShow(show.getCityName(),show.getMovies().get(0).getMovieId(),show.getMovies().get(0).getTheatres().get(0));
            responseEntity=new ResponseEntity<List<Show>>(showList,HttpStatus.OK);
        }catch (ShowNotFoundException ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch (Exception ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

    @DeleteMapping("show/{id}")
    public ResponseEntity<?> deleteShow(@RequestBody Show show,@PathVariable(value = "id")  int id){

        ResponseEntity responseEntity;

        try{
            List<Show> showList=showService.delteShow(show.getCityName(),show.getMovies().get(0).getMovieId(),id);
            responseEntity=new ResponseEntity<List<Show>>(showList,HttpStatus.OK);
        }catch (ShowNotFoundException ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch (Exception ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

}

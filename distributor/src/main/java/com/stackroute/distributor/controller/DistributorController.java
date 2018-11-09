package com.stackroute.distributor.controller;

import com.stackroute.distributor.domain.City;
import com.stackroute.distributor.domain.Distributor;
import com.stackroute.distributor.domain.Movie;
import com.stackroute.distributor.exceptions.DistributorAlreadyExistException;
import com.stackroute.distributor.exceptions.DistributorNotFoundException;
import com.stackroute.distributor.service.DistributorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/distributor")
@Api(value="distributorapplication", description="Operations pertaining to a distributor application")
public class DistributorController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private DistributorService distributorService;

    @Autowired
    public DistributorController(DistributorService distributorService) {
        this.distributorService = distributorService;
    }

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private static final String TOPIC = "kafkajsontest";

    @ApiOperation(value = "Save a Distributor in database", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Saved Distributor"),
            @ApiResponse(code = 409, message = "Saving an existing distributor")
    })
    @PostMapping()
    public ResponseEntity<?> saveDistributor(@Valid @RequestBody Distributor distributor)  {
        ResponseEntity responseEntity;
        try {
            Distributor savedDistributor = distributorService.addDistributor(distributor);
            responseEntity = new ResponseEntity<Distributor>(savedDistributor, HttpStatus.OK);
        }
        catch (DistributorAlreadyExistException e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        catch (Exception ex)
        {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
            logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        kafkaTemplate.send(TOPIC,distributor);
        return responseEntity;
    }

    @ApiOperation(value = "Get all Distributors in database", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all distributors"),
            @ApiResponse(code = 409, message = "Exception")
    })
    @GetMapping()
    public ResponseEntity<?> getAllDistributors(){
        List<Distributor> distributorsList;
        distributorsList = distributorService.getAllDistributors();
        ResponseEntity responseEntity = new ResponseEntity<List<Distributor>>(distributorsList,HttpStatus.OK);
        return  responseEntity;
    }

    @ApiOperation(value = "Get a Distributor", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Distributor"),
            @ApiResponse(code = 409, message = "Distributor does not exist")
    })
    @GetMapping(value="/{email}")
    public ResponseEntity<?> searchDistributor(@PathVariable String email){
        ResponseEntity responseEntity;
        try {
            Distributor distributor = distributorService.getDistributorByEmail(email);
            responseEntity = new ResponseEntity<Distributor>(distributor, HttpStatus.OK);
        }
        catch ( DistributorNotFoundException e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        catch (Exception ex)
        {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
            logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return responseEntity;
    }

    @ApiOperation(value = "Delete a distributor", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Deleted distributor"),
            @ApiResponse(code = 409, message = "Movie does not  exists")
    })
    @DeleteMapping(value ="/{email}")
    public ResponseEntity<?> deleteDistributor(@PathVariable String email){
        ResponseEntity responseEntity;
        try {
            Boolean bool = distributorService.deleteDistributor(email);
            responseEntity = new ResponseEntity<Boolean>(bool, HttpStatus.OK);
        }
        catch (DistributorNotFoundException e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        catch (Exception ex)
        {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
            logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return responseEntity;
    }

    @ApiOperation(value = "Add new city to a Distributor", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added city"),
            @ApiResponse(code = 409, message = "city already exists")
    })
    @PutMapping(value = "{email}")
    public ResponseEntity<?> addNewCity(@PathVariable String email, @RequestBody City city)
    {
        ResponseEntity responseEntity;
        try {
            Distributor distributor=distributorService.addNewCity(email,city);
            responseEntity = new ResponseEntity<Distributor>(distributor, HttpStatus.OK);
        }catch(DistributorNotFoundException e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        catch (Exception ex)
        {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
            logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return responseEntity;

    }

    @ApiOperation(value = "Delete a city from Distributor", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted a city"),
            @ApiResponse(code = 409, message = "city does not exists")
    })
    @PutMapping(value = "{email}/{cityName}/deleteCity")
    public ResponseEntity<?> DeleteCity(@PathVariable String email, @PathVariable String cityName)
    {
        ResponseEntity responseEntity;
        try {
            Distributor distributor=distributorService.DeleteCity(email,cityName);
            responseEntity = new ResponseEntity<Distributor>(distributor, HttpStatus.OK);
        }catch(DistributorNotFoundException e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            e.printStackTrace();
             logger.error(e.getMessage());
        }
        catch (Exception ex)
        {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
             logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return responseEntity;

    }

    @ApiOperation(value = "Add new movie city", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added Movie"),
            @ApiResponse(code = 409, message = "Movie  Already exists")
    })
    @PutMapping(value = "{email}/{cityName}/addNewMovie")
    public ResponseEntity<?> addNewMovie(@PathVariable String email, @PathVariable String cityName, @RequestBody Movie movie)
    {
        ResponseEntity responseEntity;
        try {
            Distributor distributor=distributorService.addNewMovie(email,cityName,movie);
            responseEntity = new ResponseEntity<Distributor>(distributor, HttpStatus.OK);
        }catch(DistributorNotFoundException e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        catch (Exception ex)
        {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
            logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return responseEntity;

    }

    @ApiOperation(value = "Delete a movie", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Deleted Movie"),
            @ApiResponse(code = 409, message = "Movie does not exists")
    })
    @PutMapping(value = "{email}/{cityName}/{movieId}//deleteMovie")
    public ResponseEntity<?> DeleteMovie(@PathVariable String email, @PathVariable String cityName,@PathVariable int movieId)
    {
        ResponseEntity responseEntity;
        try {
            Distributor distributor=distributorService.DeleteMovie(email,cityName,movieId);
            responseEntity = new ResponseEntity<Distributor>(distributor, HttpStatus.OK);
        }catch(DistributorNotFoundException e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        catch (Exception ex)
        {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
            logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return responseEntity;

    }

}

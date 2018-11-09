package com.stackroute.producerregistrationservice.controller;

import com.stackroute.producerregistrationservice.domain.Producer;
import com.stackroute.producerregistrationservice.domain.Theatre;
import com.stackroute.producerregistrationservice.exceptions.ProducerAlreadyExistsException;
import com.stackroute.producerregistrationservice.exceptions.ProducerNotFoundException;
import com.stackroute.producerregistrationservice.services.ProducerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
@Api(value="ProducerTheatreRegistration", description="Operations pertaining to a Theatre Operator")
public class ProducerRegistrationServiceController {

    private ProducerService producerService;
    private KafkaTemplate<String, Producer> kafkaTemplate;

    @Autowired
    public ProducerRegistrationServiceController (ProducerService producerService, KafkaTemplate<String, Producer> kafkaTemplate) {
        this.producerService = producerService;
        this.kafkaTemplate = kafkaTemplate;
    }


    private static final String TOPIC = "kafkathenga";


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ApiOperation(value = "Save in database", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Saved "),
            @ApiResponse(code = 401, message = "You are not authorized to save "),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @PostMapping("/producer")
    public ResponseEntity<?> saveProducer(@Valid @RequestBody Producer producer){
        ResponseEntity responseEntity;
        try {
            Producer savedProducer = producerService.addProducer(producer);
            responseEntity = new ResponseEntity<Producer>(savedProducer, HttpStatus.CREATED);
        }
        catch (ProducerAlreadyExistsException e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            logger.error(e.getMessage()) ;
            e.printStackTrace();
        }
        catch (Exception e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            logger.error(e.getMessage()) ;
            e.printStackTrace();
        }
        kafkaTemplate.send(TOPIC,producer);
        return responseEntity;
    }



    @ApiOperation(value = "Search Producer details", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Searched "),
            @ApiResponse(code = 401, message = "You are not authorized to search "),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping(value="/producer/{email}/")
    public ResponseEntity<?> getTheatreListOfProducer(@PathVariable String email){
        ResponseEntity responseEntity;
        try {
            List<Theatre> theatreDetails = producerService.getTheatreByProducerEmail(email);
            responseEntity = new ResponseEntity<List<Theatre>>(theatreDetails, HttpStatus.OK);
        }
        catch (Exception e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
            logger.error(e.getMessage()) ;
            e.printStackTrace();
        }
        return responseEntity;
    }

    @ApiOperation(value = "Get All Producer details", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Retrieved "),
            @ApiResponse(code = 401, message = "You are not authorized to do the Operation "),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping(value="/producer")
        public ResponseEntity<?> getAllProducers(){
        List<Producer> producerList;
        producerList = producerService.getAllProducers();
        ResponseEntity responseEntity = new ResponseEntity<List<Producer>>(producerList,HttpStatus.OK);
        return  responseEntity;
    }

    @ApiOperation(value = "Delete Producer details", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Deleted "),
            @ApiResponse(code = 401, message = "You are not authorized to delete details "),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @DeleteMapping(value ="/producer/{email}/")
    public ResponseEntity<?> deleteProducer(@PathVariable String email){
        ResponseEntity responseEntity;
        try {
            Boolean bool = producerService.deleteProducer(email);
            responseEntity = new ResponseEntity<Boolean>(bool, HttpStatus.OK);
        }
        catch (ProducerNotFoundException e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
            e.printStackTrace();
           logger.error(e.getMessage());
        }
        catch (Exception ex)
        {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
           logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return responseEntity;
    }


    @ApiOperation(value = "Add Theatre details", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Added "),
            @ApiResponse(code = 401, message = "You are not authorized to add details "),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @PutMapping(value = "/addTheatre/{email}/")
    public ResponseEntity<?> addNewTheatre(@PathVariable String email, @RequestBody Theatre theatre)
    {
        ResponseEntity responseEntity;
        try {
            Producer producer=producerService.addNewTheatre(email,theatre);
            responseEntity = new ResponseEntity<Producer>(producer, HttpStatus.OK);
            kafkaTemplate.send(TOPIC,producer);
        }catch(ProducerNotFoundException e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
            e.printStackTrace();
           logger.error(e.getMessage());
        }
        catch (Exception ex)
        {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
           logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return responseEntity;

    }


    @ApiOperation(value = "Delete Theatre details", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Deleted"),
            @ApiResponse(code = 401, message = "You are not authorized to Delete "),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @PutMapping(value = "/deleteTheatre/{email}/{theatreName}")
    public ResponseEntity<?> DeleteTheatre(@PathVariable String email, @PathVariable String theatreName)
    {
        ResponseEntity responseEntity;
        try {
            Producer producer=producerService.DeleteTheatre(email,theatreName);
            responseEntity = new ResponseEntity<Producer>(producer, HttpStatus.OK);
            kafkaTemplate.send(TOPIC,producer);
        }catch(ProducerNotFoundException e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
            e.printStackTrace();
           logger.error(e.getMessage());
        }
        catch (Exception ex)
        {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
           logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return responseEntity;

    }


}

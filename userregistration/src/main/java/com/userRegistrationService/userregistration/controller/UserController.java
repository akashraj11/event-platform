package com.userRegistrationService.userregistration.controller;

import com.userRegistrationService.userregistration.Exception.UserAlreadyExistsException;
import com.userRegistrationService.userregistration.domain.User;
import com.userRegistrationService.userregistration.domain.loginCredential;
import com.userRegistrationService.userregistration.service.UserImpl;
import com.userRegistrationService.userregistration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.kafka.core.KafkaTemplate;

import static org.apache.kafka.common.config.ConfigResource.Type.TOPIC;

@RestController
@RequestMapping(value = "/api/v1")
//@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
public class UserController {

   private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private static final String TOPIC = "kafkajsontest";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("saveuser")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        ResponseEntity responseEntity;
        loginCredential loginCredential= new loginCredential(user.getEmail(),user.getPassword());
        try{
            userService.saveUser(user);
            kafkaTemplate.send(TOPIC,loginCredential);
            responseEntity= new ResponseEntity<String>("Successfully Created", HttpStatus.CREATED);
        }catch (UserAlreadyExistsException e){
            responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
            logger.error(e.getMessage()) ;
            e.printStackTrace();
        }
        catch (Exception e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            logger.error(e.getMessage()) ;
            e.printStackTrace();
        }
        return responseEntity;
    }

    @GetMapping(path = "/getuser/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") String id){
        ResponseEntity responseEntity;
        try{
            User user= userService.getUserById(id);
            responseEntity= new ResponseEntity<User>(user,HttpStatus.FOUND);
        }catch (Exception e){
            logger.error(e.getMessage()) ;
            responseEntity= new ResponseEntity<String>("User Not Found",HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
    @PutMapping("update/{id}")
    public ResponseEntity<?> updateUserById(@RequestBody User user ,@PathVariable("id") String id){
        ResponseEntity responseEntity;
        try {
            userService.updateUserById(id,user);
            responseEntity = new ResponseEntity<String>("Successfully Updated",HttpStatus.CREATED);
        }catch (Exception e){
            responseEntity =new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            logger.error(e.getMessage()) ;
        }
        return responseEntity;
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") String id){
        ResponseEntity responseEntity;
        try {
            userService.deleteUserById(id);
            responseEntity = new ResponseEntity<String>("Successfully deleted",HttpStatus.CREATED);
        }catch (Exception e){
            responseEntity =new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            logger.error(e.getMessage()) ;
        }
        return responseEntity;
    }

}

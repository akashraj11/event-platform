package com.stackroute.producerregistrationservice.services;

import com.stackroute.producerregistrationservice.domain.Producer;
import com.stackroute.producerregistrationservice.domain.Theatre;
import com.stackroute.producerregistrationservice.exceptions.ProducerAlreadyExistsException;
import com.stackroute.producerregistrationservice.exceptions.ProducerNotFoundException;
import com.stackroute.producerregistrationservice.exceptions.TheatreAlreadyExistException;
import com.stackroute.producerregistrationservice.exceptions.TheatreNotFoundException;

import java.util.ArrayList;
import java.util.List;

public interface ProducerService {
    public List<Producer> getAllProducers();
    public Producer addProducer(Producer producer) throws ProducerAlreadyExistsException;
    public boolean deleteProducer(String email)throws ProducerNotFoundException;
    public Producer getProducerByEmail(String email)throws ProducerNotFoundException;;
    public List<Theatre> getTheatreByProducerEmail(String email)throws ProducerNotFoundException;
    public Producer addNewTheatre(String email, Theatre theatre)throws ProducerNotFoundException, TheatreAlreadyExistException;
    public Producer DeleteTheatre(String email, String theatreName) throws ProducerNotFoundException, TheatreNotFoundException;
/*  public Theatre getTheatreByTheatreId(Integer theatreId);
    public ArrayList<Theatre> getTheatreByName(String theatreTitle)throws TheatreNotFoundException  ;
    public ArrayList<Theatre> getTheatreByCity(String theatreTitle)throws TheatreNotFoundException  ;*/
    //public Producer UpdateTheatre(String email, Theatre theatre) throws ProducerNotFoundException, TheatreAlreadyExistException

}

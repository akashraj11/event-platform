package com.stackroute.producerregistrationservice.repository;

import com.stackroute.producerregistrationservice.domain.Producer;
import com.stackroute.producerregistrationservice.domain.Theatre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends CrudRepository<Producer, String> {
    public Producer findByemail(String email);
}

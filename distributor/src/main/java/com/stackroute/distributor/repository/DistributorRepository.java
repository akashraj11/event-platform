package com.stackroute.distributor.repository;

import com.stackroute.distributor.domain.Distributor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistributorRepository extends CrudRepository<Distributor,String> {
    public Distributor findByemail( String email);
}

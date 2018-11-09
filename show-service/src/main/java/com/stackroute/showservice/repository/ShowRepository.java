package com.stackroute.showservice.repository;
import com.stackroute.showservice.domain.Show;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends MongoRepository<Show,String> {

    public Show getShowByCityName(String cityName);
}

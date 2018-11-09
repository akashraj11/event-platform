package com.userRegistrationService.userregistration.repository;

import com.userRegistrationService.userregistration.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends MongoRepository<User,String>{
}

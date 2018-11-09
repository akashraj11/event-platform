package com.userRegistrationService.userregistration.service;

import com.userRegistrationService.userregistration.Exception.NoSuchUserException;
import com.userRegistrationService.userregistration.Exception.UserAlreadyExistsException;
import com.userRegistrationService.userregistration.domain.User;
import com.userRegistrationService.userregistration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        if(userRepository.existsById(user.getEmail())){
            throw new UserAlreadyExistsException("User already Exists");
        }
        else {
            User saveduser = userRepository.save(user);
            return saveduser;
        }
    }

    @Override
    public User updateUserById(String id, User user)throws NoSuchUserException {
        User newuser=null;
        if(userRepository.existsById(id)) {
            newuser = getUserById(id);
            newuser.setCity(user.getCity());
            newuser.setEmail(user.getEmail());
            newuser.setGender(user.getGender());
            newuser.setName(user.getName());
            newuser.setPhoneNumber(user.getPhoneNumber());
            newuser.setLanguage(user.getLanguage());
            newuser.setGenre(user.getGenre());
            newuser.setGenre(user.getWatchList());
            User savedmovie=userRepository.save(user);
            return savedmovie;
        }
        else{
            throw new NoSuchUserException("No user Found");
        }
    }

    @Override
    public User getUserById(String id) {
        User user= userRepository.findById(id).get();
        return user;
    }

    @Override
    public Boolean deleteUserById(String id) throws NoSuchUserException {
        if(userRepository.existsById(id)==false){
            throw new NoSuchUserException("No user Found");
        }
        else{
            User deletedUser=getUserById(id);
            userRepository.delete(deletedUser);
            return true;
        }
    }
}

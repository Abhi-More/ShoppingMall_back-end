package com.shoppingmall.demo.services;

import com.shoppingmall.demo.config.UserInfoDetails;
import com.shoppingmall.demo.models.UserInfo;
import com.shoppingmall.demo.repositories.UserInfoRepository;
import com.shoppingmall.demo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserInfo> userDetail = repository.findByemail(username);

        // Converting userDetail to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public ResponseEntity<String> addUser(UserInfo userInfo) {

        try{
            if (repository.findByemail(userInfo.getEmail()).isPresent()) {
                return new ResponseEntity<>("User already exists with same email", HttpStatus.CONFLICT);
            }

            userInfo.setPassword(encoder.encode(userInfo.getPassword()));
            repository.save(userInfo);
            return new ResponseEntity<>("User added successfully", HttpStatus.CREATED);
        }
        catch(IllegalArgumentException e){
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }


    }


}

package com.example.testsecurity.service;


import com.example.testsecurity.dto.JoinDTO;
import com.example.testsecurity.entity.UserEntity;
import com.example.testsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public void joinProc(JoinDTO joinDTO){


        if(userRepository.existsByUserName(joinDTO.getUsername())){
            return;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(joinDTO.getUsername());
        userEntity.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        userEntity.setRole("ROLE_ADMIN");


        userRepository.save(userEntity);

    }
}

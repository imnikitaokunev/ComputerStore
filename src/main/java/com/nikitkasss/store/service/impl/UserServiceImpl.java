package com.nikitkasss.store.service.impl;

import com.nikitkasss.store.dto.user.AllUserInfoDto;
import com.nikitkasss.store.dto.user.GeneralUserInfoDto;
import com.nikitkasss.store.dto.user.UserLoginPasswordDto;
import com.nikitkasss.store.exception.ConvertingException;
import com.nikitkasss.store.exception.NoSuchEntityException;
import com.nikitkasss.store.model.AbstractUser;
import com.nikitkasss.store.repository.UserRepository;
import com.nikitkasss.store.service.UserService;
import com.nikitkasss.store.service.converter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional(readOnly = false)
public class UserServiceImpl implements UserService {

    @Autowired
    AuthenticationManagerBuilder manager;

    private UserRepository userRepository;
    private UserConverter userConverter;

    public UserServiceImpl (UserRepository userRepository, UserConverter userConverter){
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public List<AllUserInfoDto> allUsers() {
        return StreamSupport.stream(userRepository
                .findAll().spliterator(), false)
                .map(user->userConverter.convertToAllUserInfoDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<GeneralUserInfoDto> allUserGeneralInfo() {
        return StreamSupport.stream(userRepository
                .findAll().spliterator(), false)
                .map((user)->userConverter.convertToGeneralUserInfoDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserLoginPasswordDto> allLoginAndPassword() {
        return StreamSupport.stream(userRepository
        .findAll().spliterator(), false)
        .map(user -> userConverter.convertToLoginAndPasswordDto(user))
        .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void add(AllUserInfoDto dto) throws Exception {
        if(userRepository.getByUserName(dto.getUserName()) != null)
            throw new IllegalArgumentException("User with this login already exists." + dto.getUserName());
        AbstractUser user = userConverter.convertToUser(dto);

        //register()
//        System.out.println("REGISSTER" + dto.getUserName());
//
//        manager.inMemoryAuthentication()
//                .withUser(user.getUserName())
//                .password(user.getUserPassword())
//                .roles(user.getUserRole().getValue());
        userRepository.save(user);
    }

    @Override
    public AllUserInfoDto getByUserName(String username) throws ConvertingException {
        return userRepository.getByUserName(username);
    }

//    @Transactional
//    @Override
//    public void delete(AllUserInfoDto dto) throws ConvertingException {
//        AbstractUser user = userConverter.convertToUser(dto);
//        userRepository.delete(user);
//    }

    @Transactional
    @Override
    public void edit(AllUserInfoDto dto) throws ConvertingException {
        AbstractUser user = userConverter.convertToUser(dto);
        userRepository.save(user);
    }

    @Override
    public AllUserInfoDto getById(Long id) throws NoSuchEntityException {
        AbstractUser user = userRepository.findById(id).orElseThrow(() -> new NoSuchEntityException(String.format("Can't find entity by id = %id", id)));
        return userConverter.convertToAllUserInfoDto(user);
    }

    private AllUserInfoDto getByLogin(String login) throws NoSuchEntityException{
        AllUserInfoDto dto = userRepository.getByUserName(login);
        return dto;
    }
}

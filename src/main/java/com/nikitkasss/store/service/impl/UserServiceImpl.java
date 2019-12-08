package com.nikitkasss.store.service.impl;

import com.nikitkasss.store.dto.GeneralUserDto;
import com.nikitkasss.store.dto.UserDto;
import com.nikitkasss.store.exception.ConvertingException;
import com.nikitkasss.store.exception.NoSuchEntityException;
import com.nikitkasss.store.model.AbstractUser;
import com.nikitkasss.store.repository.UserRepository;
import com.nikitkasss.store.service.UserService;
import com.nikitkasss.store.service.converter.UserConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional(readOnly = false)
public class UserServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(String.valueOf(ActServiceImpl.class));


    private UserRepository userRepository;
    private UserConverter userConverter;

    public UserServiceImpl (UserRepository userRepository, UserConverter userConverter){
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public List<UserDto> allUsers() {
        logger.info("Show users");
        return StreamSupport.stream(userRepository
                .findAll().spliterator(), false)
                .map(user->userConverter.convertToAllUserInfoDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<GeneralUserDto> allUserGeneralInfo() {
        logger.info("Show users general info");
        return StreamSupport.stream(userRepository
                .findAll().spliterator(), false)
                .map((user)->userConverter.convertToGeneralUserInfoDto(user))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void add(UserDto dto) throws Exception {
        logger.info("Add user id = " + dto.getId());
        AbstractUser user = userConverter.convertToUser(dto);
        userRepository.save(user);
    }

    @Override
    public UserDto getByUserName(String username) throws ConvertingException {
        logger.info("Get user by name: " + username);
        return userRepository.getByUserName(username);
    }

    @Transactional
    @Override
    public void edit(UserDto dto) throws ConvertingException {
        logger.info("Edit user id: " + dto.getId());
        AbstractUser user = userConverter.convertToUser(dto);
        userRepository.save(user);
    }

    @Override
    public UserDto getById(Long id) throws NoSuchEntityException {
        logger.info("Edit user id: " + id);
        AbstractUser user = userRepository.findById(id).orElseThrow(() -> new NoSuchEntityException(String.format("Can't find entity by id = %id", id)));
        return userConverter.convertToAllUserInfoDto(user);
    }

    private UserDto getByLogin(String login) throws NoSuchEntityException{
        logger.info("Get user by login: " + login);
        UserDto dto = userRepository.getByUserName(login);
        return dto;
    }
}

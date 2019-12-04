package com.nikitkasss.store.service;

import com.nikitkasss.store.dto.UserDto;
import com.nikitkasss.store.dto.GeneralUserDto;
import com.nikitkasss.store.exception.ConvertingException;
import com.nikitkasss.store.exception.NoSuchEntityException;

import java.util.List;

public interface UserService {

    List<UserDto> allUsers();

    List<GeneralUserDto> allUserGeneralInfo();

    void add(UserDto dto) throws Exception;

    UserDto getByUserName(String username) throws ConvertingException;

    void edit(UserDto dto) throws ConvertingException;

    UserDto getById(Long id) throws NoSuchEntityException;
}

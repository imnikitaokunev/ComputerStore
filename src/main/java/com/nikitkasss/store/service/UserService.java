package com.nikitkasss.store.service;

import com.nikitkasss.store.dto.user.AllUserInfoDto;
import com.nikitkasss.store.dto.user.GeneralUserInfoDto;
import com.nikitkasss.store.dto.user.UserLoginPasswordDto;
import com.nikitkasss.store.exception.ConvertingException;
import com.nikitkasss.store.exception.NoSuchEntityException;

import java.util.List;

public interface UserService {

    List<AllUserInfoDto> allUsers();

    List<UserLoginPasswordDto> allLoginAndPassword();

    List<GeneralUserInfoDto> allUserGeneralInfo();

    void add(AllUserInfoDto dto) throws ConvertingException;

    //void delete(AllUserInfoDto dto) throws ConvertingException;

    void edit(AllUserInfoDto dto) throws ConvertingException;

    AllUserInfoDto getById(Long id) throws NoSuchEntityException;
}

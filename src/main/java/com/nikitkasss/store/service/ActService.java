package com.nikitkasss.store.service;

import com.nikitkasss.store.dto.act.AllActInfoDto;
import com.nikitkasss.store.exception.ConvertingException;
import com.nikitkasss.store.exception.NoSuchEntityException;

import java.text.ParseException;
import java.util.List;

public interface ActService {

    List<AllActInfoDto> allActs();

    void add(AllActInfoDto dto) throws ConvertingException, ParseException;

    void delete(AllActInfoDto dto) throws ConvertingException, ParseException;

    void edit(AllActInfoDto dto) throws ConvertingException, ParseException;

    AllActInfoDto getById(Long id) throws NoSuchEntityException;
}

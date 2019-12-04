package com.nikitkasss.store.service;

import com.nikitkasss.store.dto.ActDto;
import com.nikitkasss.store.exception.ConvertingException;
import com.nikitkasss.store.exception.NoSuchEntityException;

import java.text.ParseException;
import java.util.List;

public interface ActService {

    List<ActDto> allActs();

    void add(ActDto dto) throws ConvertingException, ParseException;

    void delete(ActDto dto) throws ConvertingException, ParseException;

    void edit(ActDto dto) throws ConvertingException, ParseException;

    ActDto getById(Long id) throws NoSuchEntityException;

    List<ActDto> getActsByParam(String param);
}

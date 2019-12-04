package com.nikitkasss.store.service;

import com.nikitkasss.store.dto.PositionDto;
import com.nikitkasss.store.dto.PositionNameDto;
import com.nikitkasss.store.exception.ConvertingException;
import com.nikitkasss.store.exception.NoSuchEntityException;

import java.util.List;

public interface PositionService{

    List<PositionDto> allPositions();

    List<PositionNameDto> allPositionNames();

    void add(PositionDto dto) throws ConvertingException;

    void delete(PositionDto dto) throws ConvertingException;

    void edit(PositionDto dto) throws ConvertingException;

    PositionDto getById(Long id) throws NoSuchEntityException;

    List<PositionDto> getPositionsByName(String name);
}

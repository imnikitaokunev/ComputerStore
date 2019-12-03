package com.nikitkasss.store.service;

import com.nikitkasss.store.dto.position.PositionInfoDto;
import com.nikitkasss.store.dto.position.PositionNameDto;
import com.nikitkasss.store.exception.ConvertingException;
import com.nikitkasss.store.exception.NoSuchEntityException;

import java.util.List;

public interface PositionService{

    List<PositionInfoDto> allPositions();

    List<PositionNameDto> allPositionNames();

    void add(PositionInfoDto dto) throws ConvertingException;

    void delete(PositionInfoDto dto) throws ConvertingException;

    void edit(PositionInfoDto dto) throws ConvertingException;

    PositionInfoDto getById(Long id) throws NoSuchEntityException;

    List<PositionInfoDto> getPositionsByName(String name);
}

package com.nikitkasss.store.service.impl;


import com.nikitkasss.store.dto.position.PositionInfoDto;
import com.nikitkasss.store.dto.position.PositionNameDto;
import com.nikitkasss.store.exception.ConvertingException;
import com.nikitkasss.store.exception.NoSuchEntityException;
import com.nikitkasss.store.model.Position;
import com.nikitkasss.store.repository.PositionRepository;
import com.nikitkasss.store.service.PositionService;
import com.nikitkasss.store.service.converter.PositionConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional(readOnly = true)
public class PositionServiceImpl implements PositionService {

    private PositionRepository positionRepository;
    private PositionConverter positionConverter;

    @Autowired
    public PositionServiceImpl(PositionRepository positionRepository, PositionConverter positionConverter) {
        this.positionRepository = positionRepository;
        this.positionConverter = positionConverter;
    }

    @Override
    public List<PositionInfoDto> allPositions() {
        return StreamSupport.stream(positionRepository
        .findAll().spliterator(), false)
                .map(position -> positionConverter.convertToPositionInfoDto(position))
                .sorted(Comparator.comparing(PositionInfoDto::getId))
                .collect(Collectors.toList());
    }

    @Override
    public List<PositionNameDto> allPositionNames() {
        return StreamSupport.stream(positionRepository
                .findAll().spliterator(), false)
                .map(position -> positionConverter.convertToPositionNameDto(position))
                .sorted(Comparator.comparing(PositionNameDto::getId))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void add(PositionInfoDto dto) throws ConvertingException {
        Position position = positionConverter.convertToPosition(dto);
        positionRepository.save(position);
    }

    @Transactional
    @Override
    public void delete(PositionInfoDto dto) throws ConvertingException {
        Position position = positionConverter.convertToPosition(dto);
        positionRepository.delete(position);
    }

    @Transactional
    @Override
    public void edit(PositionInfoDto dto) throws ConvertingException {
        Position position = positionConverter.convertToPosition(dto);
        positionRepository.save(position);
    }

    @Override
    public PositionInfoDto getById(Long id) throws NoSuchEntityException {
        Position position = positionRepository.findById(id).orElseThrow(() -> new NoSuchEntityException(String.format("Can't find entity by id = %id", id)));
        return positionConverter.convertToPositionInfoDto(position);
    }

    @Override
    public List<PositionInfoDto> getPositionsByName(String name) {
            return StreamSupport.stream(positionRepository
                    .findAll().spliterator(), false)
                    .map(product -> positionConverter.convertToPositionInfoDto(product))
                    .filter(product -> product.getPositionName().contains(name))
                    .collect(Collectors.toList());
        }

}

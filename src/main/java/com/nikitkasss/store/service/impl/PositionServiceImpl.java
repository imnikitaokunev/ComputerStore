package com.nikitkasss.store.service.impl;


import com.nikitkasss.store.dto.PositionDto;
import com.nikitkasss.store.dto.PositionNameDto;
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
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional(readOnly = true)
public class PositionServiceImpl implements PositionService {
    private static final Logger logger = Logger.getLogger(String.valueOf(PositionServiceImpl.class));


    private PositionRepository positionRepository;
    private PositionConverter positionConverter;

    @Autowired
    public PositionServiceImpl(PositionRepository positionRepository, PositionConverter positionConverter) {
        this.positionRepository = positionRepository;
        this.positionConverter = positionConverter;
    }

    @Override
    public List<PositionDto> allPositions() {
        logger.info("Show positions");
        return StreamSupport.stream(positionRepository
        .findAll().spliterator(), false)
                .map(position -> positionConverter.convertToPositionInfoDto(position))
                .sorted(Comparator.comparing(PositionDto::getId))
                .collect(Collectors.toList());
    }

    @Override
    public List<PositionNameDto> allPositionNames() {
        logger.info("Show position names");
        return StreamSupport.stream(positionRepository
                .findAll().spliterator(), false)
                .map(position -> positionConverter.convertToPositionNameDto(position))
                .sorted(Comparator.comparing(PositionNameDto::getId))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void add(PositionDto dto) throws ConvertingException {
        logger.info("Add position id = " + dto.getId());
        Position position = positionConverter.convertToPosition(dto);
        positionRepository.save(position);
    }

    @Transactional
    @Override
    public void delete(PositionDto dto) throws ConvertingException {
        logger.info("Delete position id = " + dto.getId());
        Position position = positionConverter.convertToPosition(dto);
        positionRepository.delete(position);
    }

    @Transactional
    @Override
    public void edit(PositionDto dto) throws ConvertingException {
        logger.info("Edit position id = " + dto.getId());
        Position position = positionConverter.convertToPosition(dto);
        positionRepository.save(position);
    }

    @Override
    public PositionDto getById(Long id) throws NoSuchEntityException {
        logger.info("Get position by id = " + id);
        Position position = positionRepository.findById(id).orElseThrow(() -> new NoSuchEntityException(String.format("Can't find entity by id = %id", id)));
        return positionConverter.convertToPositionInfoDto(position);
    }

    @Override
    public List<PositionDto> getPositionsByName(String name) {
        logger.info("Get position by name: " + name);
        return StreamSupport.stream(positionRepository
                    .findAll().spliterator(), false)
                    .map(product -> positionConverter.convertToPositionInfoDto(product))
                    .filter(product -> product.getPositionName().contains(name))
                    .collect(Collectors.toList());
        }

    @Override
    public Long getCountBySalary(Long min, Long max){
        logger.info("Get count of position by salary: " + min + " - " + max);
        return StreamSupport.stream(positionRepository
                .findAll().spliterator(), false)
                .map(product -> positionConverter.convertToPositionInfoDto(product))
                .filter(product -> product.getPositionSalary() >= min && product.getPositionSalary() <= max)
                .count();
    }

}

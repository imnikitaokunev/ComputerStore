package com.nikitkasss.store.service.impl;

import com.nikitkasss.store.dto.act.AllActInfoDto;
import com.nikitkasss.store.exception.ConvertingException;
import com.nikitkasss.store.exception.NoSuchEntityException;
import com.nikitkasss.store.model.Act;
import com.nikitkasss.store.repository.ActRepository;
import com.nikitkasss.store.service.ActService;
import com.nikitkasss.store.service.converter.ActConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional(readOnly = true)
public class ActServiceImpl implements ActService {

    private ActRepository actRepository;
    private ActConverter actConverter;

    @Autowired
    public ActServiceImpl(ActRepository actRepository, ActConverter actConverter) {
        this.actRepository = actRepository;
        this.actConverter = actConverter;
    }

    @Override
    public List<AllActInfoDto> allActs() {
        return StreamSupport.stream(actRepository
        .findAll().spliterator(), false)
                .map(act -> actConverter.convertToAllActInfoDto(act))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void add(AllActInfoDto dto) throws ConvertingException, ParseException {
        Act act = actConverter.convertToAct(dto);
        actRepository.save(act);
    }

    @Transactional
    @Override
    public void delete(AllActInfoDto dto) throws ConvertingException, ParseException {
        Act act = actConverter.convertToAct(dto);
        actRepository.delete(act);
    }

    @Transactional
    @Override
    public void edit(AllActInfoDto dto) throws ConvertingException, ParseException {
        Act act = actConverter.convertToAct(dto);
        actRepository.save(act);
    }

    @Override
    public AllActInfoDto getById(Long id) throws NoSuchEntityException {
        Act act = actRepository.findById(id).orElseThrow(() -> new NoSuchEntityException(String.format("Can't find entity by id = %id", id)));
        return actConverter.convertToAllActInfoDto(act);
    }
}

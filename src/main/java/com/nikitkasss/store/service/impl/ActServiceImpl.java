package com.nikitkasss.store.service.impl;

import com.nikitkasss.store.dto.ActDto;
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
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional(readOnly = true)
public class ActServiceImpl implements ActService {
    private static final Logger logger = Logger.getLogger(String.valueOf(ActServiceImpl.class));

    private ActRepository actRepository;
    private ActConverter actConverter;

    @Autowired
    public ActServiceImpl(ActRepository actRepository, ActConverter actConverter) {
        this.actRepository = actRepository;
        this.actConverter = actConverter;
    }

    @Override
    public List<ActDto> allActs() {
        logger.info("Show acts");
        return StreamSupport.stream(actRepository
        .findAll().spliterator(), false)
                .map(act -> actConverter.convertToAllActInfoDto(act))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void add(ActDto dto) throws ConvertingException, ParseException {
        logger.info("Add act id = " + dto.getId());
        Act act = actConverter.convertToAct(dto);
        actRepository.save(act);
    }

    @Transactional
    @Override
    public void delete(ActDto dto) throws ConvertingException, ParseException {
        logger.info("Delete act id = " + dto.getId());
        Act act = actConverter.convertToAct(dto);
        actRepository.delete(act);
    }

    @Transactional
    @Override
    public void edit(ActDto dto) throws ConvertingException, ParseException {
        logger.info("Edit act id = " + dto.getId());
        Act act = actConverter.convertToAct(dto);
        actRepository.save(act);
    }

    @Override
    public ActDto getById(Long id) throws NoSuchEntityException {
        logger.info("Get act by id = " + id);
        Act act = actRepository.findById(id).orElseThrow(() -> new NoSuchEntityException(String.format("Can't find entity by id = %id", id)));
        return actConverter.convertToAllActInfoDto(act);
    }

    @Override
    public List<ActDto> getActsByParam(String param) {
        logger.info("Act by param " + param);
        return StreamSupport.stream(actRepository
                .findAll().spliterator(), false)
                .map(act -> actConverter.convertToAllActInfoDto(act))
                .filter(act -> act.getId().toString().contains(param)
                        || act.getProductId().toString().contains(param)
                        || act.getBuyerId().toString().contains(param)
                        || act.getSellerId().toString().contains(param)
                        || act.getCount().toString().contains(param)
                        || act.getDate().contains(param))
                .collect(Collectors.toList());
    }
}

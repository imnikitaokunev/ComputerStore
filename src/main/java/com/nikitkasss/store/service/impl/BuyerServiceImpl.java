package com.nikitkasss.store.service.impl;

import com.nikitkasss.store.dto.BuyerDto;
import com.nikitkasss.store.exception.ConvertingException;
import com.nikitkasss.store.exception.NoSuchEntityException;
import com.nikitkasss.store.model.Buyer;
import com.nikitkasss.store.repository.BuyerRepository;
import com.nikitkasss.store.service.BuyerService;
import com.nikitkasss.store.service.converter.UserConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional(readOnly = true)
public class BuyerServiceImpl implements BuyerService {

    private BuyerRepository buyerRepository;
    private UserConverter userConverter;

    public BuyerServiceImpl(BuyerRepository buyerRepository, UserConverter userConverter) {
        this.buyerRepository = buyerRepository;
        this.userConverter = userConverter;
    }

    @Override
    public List<BuyerDto> allBuyers() {
        return StreamSupport.stream(buyerRepository
        .findAll().spliterator(), false)
                .map(buyer -> userConverter.convertToBuyerInfoDto(buyer))
                .sorted(Comparator.comparing(BuyerDto::getId))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void add(BuyerDto dto) throws ConvertingException {
        Buyer buyer = userConverter.convertToBuyer(dto);
        buyerRepository.save(buyer);
    }

    @Transactional
    @Override
    public void delete(BuyerDto dto) throws ConvertingException {
        Buyer buyer = userConverter.convertToBuyer(dto);
        buyerRepository.delete(buyer);
    }

    @Transactional
    @Override
    public void edit(BuyerDto dto) throws ConvertingException {
        Buyer buyer = userConverter.convertToBuyer(dto);
        buyerRepository.save(buyer);
    }

    @Override
    public BuyerDto getById(Long id) throws NoSuchEntityException {
        Buyer buyer = buyerRepository.findById(id).orElseThrow(() -> new NoSuchEntityException(String.format("Can't find entity by id = %id", id)));
        return userConverter.convertToBuyerInfoDto(buyer);
    }

    @Override
    public List<BuyerDto> getBuyersByParam(String param) {
        return StreamSupport.stream(buyerRepository
                .findAll().spliterator(), false)
                .map(buyer -> userConverter.convertToBuyerInfoDto(buyer))
                .filter(buyer -> buyer.getFirstName().contains(param)
                || buyer.getLastName().contains(param)
                || buyer.getPatronymicName().contains(param)
                || buyer.getUserName().contains(param)
                || buyer.getUserPassword().contains(param)
                || buyer.getRoleName().contains(param)
                || buyer.getEmail().contains(param)
                || buyer.getPhone().contains(param))
                .collect(Collectors.toList());
    }


}

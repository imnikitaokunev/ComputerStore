package com.nikitkasss.store.service;

import com.nikitkasss.store.dto.BuyerDto;
import com.nikitkasss.store.exception.ConvertingException;
import com.nikitkasss.store.exception.NoSuchEntityException;

import java.util.List;

public interface BuyerService {

    List<BuyerDto> allBuyers();

    void add(BuyerDto dto) throws ConvertingException;

    void delete(BuyerDto dto) throws ConvertingException;

    void edit(BuyerDto dto) throws ConvertingException;

    BuyerDto getById(Long id) throws NoSuchEntityException;

    List<BuyerDto> getBuyersByParam(String param);
}

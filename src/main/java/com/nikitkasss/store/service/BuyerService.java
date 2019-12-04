package com.nikitkasss.store.service;

import com.nikitkasss.store.dto.user.BuyerInfoDto;
import com.nikitkasss.store.exception.ConvertingException;
import com.nikitkasss.store.exception.NoSuchEntityException;

import java.util.List;

public interface BuyerService {

    List<BuyerInfoDto> allBuyers();

    void add(BuyerInfoDto dto) throws ConvertingException;

    void delete(BuyerInfoDto dto) throws ConvertingException;

    void edit(BuyerInfoDto dto) throws ConvertingException;

    BuyerInfoDto getById(Long id) throws NoSuchEntityException;

    List<BuyerInfoDto> getBuyersByParam(String param);
}

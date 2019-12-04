package com.nikitkasss.store.service;

import com.nikitkasss.store.dto.GeneralUserDto;
import com.nikitkasss.store.dto.SellerDto;
import com.nikitkasss.store.exception.ConvertingException;
import com.nikitkasss.store.exception.NoSuchEntityException;

import java.util.List;

public interface SellerService {

    List<SellerDto> allSellers();

    void add(SellerDto dto) throws ConvertingException;

    void delete(SellerDto dto) throws ConvertingException;

    void edit(SellerDto dto) throws ConvertingException;

    SellerDto getById(Long id) throws NoSuchEntityException;

    List<SellerDto> getSellersByParam(String param);

    List<GeneralUserDto> getGeneralSellersInfo();

    List<GeneralUserDto> getSellersGeneralInfoByParam(String param);
}

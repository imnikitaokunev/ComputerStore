package com.nikitkasss.store.service;

import com.nikitkasss.store.dto.user.SellerInfoDto;
import com.nikitkasss.store.exception.ConvertingException;
import com.nikitkasss.store.exception.NoSuchEntityException;

import java.util.List;

public interface SellerService {

    List<SellerInfoDto> allSellers();

    void add(SellerInfoDto dto) throws ConvertingException;

    void delete(SellerInfoDto dto) throws ConvertingException;

    void edit(SellerInfoDto dto) throws ConvertingException;

    SellerInfoDto getById(Long id) throws NoSuchEntityException;
}

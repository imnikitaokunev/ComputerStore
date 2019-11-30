package com.nikitkasss.store.service;

import com.nikitkasss.store.dto.product.AllProductInfoDto;
import com.nikitkasss.store.dto.product.ProductNameDto;
import com.nikitkasss.store.exception.ConvertingException;
import com.nikitkasss.store.exception.NoSuchEntityException;

import java.util.List;

public interface ProductService {

    List<AllProductInfoDto> allProducts();

    List<ProductNameDto> allProductNames();

    void add(AllProductInfoDto dto) throws ConvertingException;

    void delete(AllProductInfoDto dto) throws ConvertingException;

    //void softDelete(AllProductInfoDto dto) throws ConvertingException;

    void edit(AllProductInfoDto dto) throws ConvertingException;

    AllProductInfoDto getById(Long id) throws NoSuchEntityException;
}

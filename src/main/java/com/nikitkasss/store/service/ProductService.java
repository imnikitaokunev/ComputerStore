package com.nikitkasss.store.service;

import com.nikitkasss.store.dto.ProductDto;
import com.nikitkasss.store.dto.ProductNameDto;
import com.nikitkasss.store.exception.ConvertingException;
import com.nikitkasss.store.exception.NoSuchEntityException;

import java.util.List;

public interface ProductService {

    List<ProductDto> allProducts();

    List<ProductNameDto> allProductNames();

    void add(ProductDto dto) throws ConvertingException;

    void delete(ProductDto dto) throws ConvertingException;

    void edit(ProductDto dto) throws ConvertingException;

    ProductDto getById(Long id) throws NoSuchEntityException;

    List<ProductDto> getProductsByName(String productName);
}

package com.nikitkasss.store.service.impl;

import com.nikitkasss.store.dto.product.AllProductInfoDto;
import com.nikitkasss.store.dto.product.ProductNameDto;
import com.nikitkasss.store.exception.ConvertingException;
import com.nikitkasss.store.exception.NoSuchEntityException;
import com.nikitkasss.store.model.Product;
import com.nikitkasss.store.repository.ProductRepository;
import com.nikitkasss.store.service.ProductService;
import com.nikitkasss.store.service.converter.ProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ProductConverter productConverter;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductConverter productConverter){
        this.productRepository = productRepository;
        this.productConverter = productConverter;
    }

    @Override
    public List<ProductNameDto> allProductNames(){
        return StreamSupport.stream(productRepository
                .findAll().spliterator(), false)
                .map(product -> productConverter.convertToProductNameDto(product))
                .sorted(Comparator.comparing(ProductNameDto::getId))
                .collect(Collectors.toList());

    }

    @Override
    public List<AllProductInfoDto> allProducts() {
        return StreamSupport.stream(productRepository
                .findAll().spliterator(), false)
                .map(product -> productConverter.convertToAllProductInfoDto(product))
                .sorted(Comparator.comparing(AllProductInfoDto::getId))
                .collect(Collectors.toList());
    }

    @Override
    public List<AllProductInfoDto> getProductsByName(String name) {
        return StreamSupport.stream(productRepository
                .findAll().spliterator(), false)
                .map(product -> productConverter.convertToAllProductInfoDto(product))
                .filter(product -> product.getProductName().contains(name))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void add(AllProductInfoDto dto) throws ConvertingException {
        Product product = productConverter.convertToProduct(dto);
        productRepository.save(product);
    }

    @Transactional
    @Override
    public void delete(AllProductInfoDto dto) throws ConvertingException {
        Product product = productConverter.convertToProduct(dto);
        productRepository.delete(product);
    }

    @Transactional
    @Override
    public void edit(AllProductInfoDto dto) throws ConvertingException {
        Product product = productConverter.convertToProduct(dto);
        productRepository.save(product);
    }

    @Override
    public AllProductInfoDto getById(Long id) throws NoSuchEntityException {
        Product product =  productRepository.findById(id).orElseThrow(() -> new NoSuchEntityException(String.format("Can't find entity by id = %id", id)));
        return productConverter.convertToAllProductInfoDto(product);
    }
}

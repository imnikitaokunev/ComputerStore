package com.nikitkasss.store.repository;

import com.nikitkasss.store.dto.ProductDto;
import com.nikitkasss.store.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<ProductDto> getByProductName(String productName);
}

package com.nikitkasss.store.repository;

import com.nikitkasss.store.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}

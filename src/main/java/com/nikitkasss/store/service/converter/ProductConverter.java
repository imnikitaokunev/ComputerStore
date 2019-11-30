package com.nikitkasss.store.service.converter;

import com.nikitkasss.store.dto.product.AllProductInfoDto;
import com.nikitkasss.store.dto.product.ProductNameDto;
import com.nikitkasss.store.exception.ConvertingException;
import com.nikitkasss.store.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductConverter {

    public ProductNameDto convertToProductNameDto(Product product){
        ProductNameDto dto = new ProductNameDto();
        dto.setId(product.getId());
        dto.setProductName(product.getProductName());
        return dto;
    }

    public AllProductInfoDto convertToAllProductInfoDto(Product product){
        AllProductInfoDto dto = new AllProductInfoDto();
        dto.setId(product.getId());
        dto.setProductName(product.getProductName());
        dto.setProductCost(product.getProductCost());
        return dto;
    }

    public Product convertToProduct(AllProductInfoDto dto){
        throwExceptionIfDtoIsNotValid(dto);

        Product product = new Product();
        product.setId(dto.getId());
        product.setProductName(dto.getProductName());
        product.setProductCost(dto.getProductCost());

        return product;
    }

    private void throwExceptionIfDtoIsNotValid(AllProductInfoDto dto) throws ConvertingException {
        if(dto == null){
            throw new ConvertingException("Product information must be not empty.");
        }
        if(dto.getProductName() == null){
            throw new ConvertingException("Name must be not empty.");
        }
    }
}

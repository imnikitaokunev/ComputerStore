package com.nikitkasss.store.service.converter;

import com.nikitkasss.store.dto.act.AllActInfoDto;
import com.nikitkasss.store.exception.ConvertingException;
import com.nikitkasss.store.model.Act;
import com.nikitkasss.store.model.Buyer;
import com.nikitkasss.store.model.Product;
import com.nikitkasss.store.model.Seller;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ActConverter {

    public AllActInfoDto convertToAllActInfoDto(Act act){
        AllActInfoDto dto = new AllActInfoDto();
        dto.setId(act.getId());
        dto.setBuyerId(act.getBuyer().getId());
        dto.setSellerId(act.getSeller().getId());
        dto.setProductId(act.getProduct().getId());
        dto.setCount(act.getCount());
        dto.setDate(act.getDate().toString());
        return dto;
    }

    public Act convertToAct(AllActInfoDto dto) throws ConvertingException, ParseException {
        throwExceptionIfDtoIsNotValid(dto);

        Act act = new Act();
        act.setId(dto.getId());
        Buyer buyer = new Buyer();
        buyer.setId(dto.getBuyerId());
        Seller seller = new Seller();
        seller.setId(dto.getSellerId());
        Product product = new Product();
        product.setId(dto.getProductId());
        act.setBuyer(buyer);
        act.setSeller(seller);
        act.setProduct(product);
        act.setCount(dto.getCount());
        String pattern = "MM-dd-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse(dto.getDate().toString());
        act.setDate(date);
        return act;
    }

    private void throwExceptionIfDtoIsNotValid(AllActInfoDto dto) {
        if(dto == null){
            throw new ConvertingException("Act must be not empty.");
        }
    }

}

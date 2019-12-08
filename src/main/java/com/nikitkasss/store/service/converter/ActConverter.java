package com.nikitkasss.store.service.converter;

import com.nikitkasss.store.dto.ActDto;
import com.nikitkasss.store.exception.ConvertingException;
import com.nikitkasss.store.model.Act;
import com.nikitkasss.store.model.Buyer;
import com.nikitkasss.store.model.Product;
import com.nikitkasss.store.model.Seller;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ActConverter {

    public ActDto convertToAllActInfoDto(Act act){
        ActDto dto = new ActDto();
        dto.setId(act.getId());
        dto.setBuyerId(act.getBuyer().getId());
        dto.setSellerId(act.getSeller().getId());
        dto.setProductId(act.getProduct().getId());
        dto.setCount(act.getCount());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        String date = dateFormat.format(act.getDate());
        dto.setDate(date);
        return dto;
    }

    public Act convertToAct(ActDto dto) throws ConvertingException, ParseException {
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date date = simpleDateFormat.parse(dto.getDate());
        act.setDate(date);
        return act;
    }

    private void throwExceptionIfDtoIsNotValid(ActDto dto) {
        if(dto == null){
            throw new ConvertingException("Act must be not empty.");
        }
    }

}

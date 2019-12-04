package com.nikitkasss.store.service.impl;

import com.nikitkasss.store.dto.user.GeneralUserInfoDto;
import com.nikitkasss.store.dto.user.SellerInfoDto;
import com.nikitkasss.store.exception.ConvertingException;
import com.nikitkasss.store.exception.NoSuchEntityException;
import com.nikitkasss.store.model.Seller;
import com.nikitkasss.store.repository.SellerRepository;
import com.nikitkasss.store.service.SellerService;
import com.nikitkasss.store.service.converter.UserConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional(readOnly = true)
public class SellerServiceImpl implements SellerService {

    private SellerRepository sellerRepository;
    private UserConverter userConverter;

    public SellerServiceImpl(SellerRepository sellerRepository, UserConverter userConverter) {
        this.sellerRepository = sellerRepository;
        this.userConverter = userConverter;
    }

    @Override
    public List<SellerInfoDto> allSellers() {
        return StreamSupport.stream(sellerRepository
                .findAll().spliterator(), false)
                .map(seller -> userConverter.convertToSellerInfoDto(seller))
                .sorted(Comparator.comparing(SellerInfoDto::getId))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void add(SellerInfoDto dto) throws ConvertingException {
//        if(dto.getRoleName() != RoleEnum.SELLER.getValue() && dto.getRoleName() != RoleEnum.ADMIN.getValue()){
//            throw new IllegalArgumentException(dto.getRoleName() + " not supported.");
//        }

        Seller seller = userConverter.convertToSeller(dto);
        sellerRepository.save(seller);
    }

    @Transactional
    @Override
    public void delete(SellerInfoDto dto) throws ConvertingException {
        Seller seller = userConverter.convertToSeller(dto);
        sellerRepository.delete(seller);
    }

    @Transactional
    @Override
    public void edit(SellerInfoDto dto) throws ConvertingException {
        Seller seller = userConverter.convertToSeller(dto);
        sellerRepository.save(seller);
    }

    @Override
    public SellerInfoDto getById(Long id) throws NoSuchEntityException {
        Seller seller = sellerRepository.findById(id).orElseThrow(() -> new NoSuchEntityException(String.format("Can't find entity by id = %id", id)));
        return userConverter.convertToSellerInfoDto(seller);
    }

    @Override
    public List<SellerInfoDto> getSellersByParam(String param) {
        return StreamSupport.stream(sellerRepository
                .findAll().spliterator(), false)
                .map(seller -> userConverter.convertToSellerInfoDto(seller))
                .filter(seller -> seller.getFirstName().contains(param)
                        || seller.getLastName().contains(param)
                        || seller.getPatronymicName().contains(param)
                        || seller.getUserName().contains(param)
                        || seller.getUserPassword().contains(param)
                        || seller.getRoleName().contains(param))
                .collect(Collectors.toList());
    }

    @Override
    public List<GeneralUserInfoDto> getGeneralSellersInfo(){
        return StreamSupport.stream(sellerRepository
                .findAll().spliterator(), false)
                .map(seller -> userConverter.convertToGeneralUserInfoDto(seller))
                .sorted(Comparator.comparing(GeneralUserInfoDto::getId))
                .collect(Collectors.toList());
    }

    @Override
    public List<GeneralUserInfoDto> getSellersGeneralInfoByParam(String param){
        return StreamSupport.stream(sellerRepository
                .findAll().spliterator(), false)
                .map(seller -> userConverter.convertToGeneralUserInfoDto(seller))
                .filter(seller -> seller.getFirstName().contains(param)
                        || seller.getLastName().contains(param)
                        || seller.getUserName().contains(param))
                .sorted(Comparator.comparing(GeneralUserInfoDto::getId))
                .collect(Collectors.toList());
    }

}

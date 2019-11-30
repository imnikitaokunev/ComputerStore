package com.nikitkasss.store.service.converter;

import com.nikitkasss.store.dto.user.*;
import com.nikitkasss.store.exception.ConvertingException;
import com.nikitkasss.store.model.*;
import org.springframework.stereotype.Service;

@Service
public class UserConverter {

    public GeneralUserInfoDto convertToGeneralUserInfoDto(AbstractUser user){
        GeneralUserInfoDto dto = new GeneralUserInfoDto();
        dto.setId(user.getId());
        dto.setRoleName(user.getUserRole().getValue());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setUserName(user.getUserName());
        return dto;
    }

    public AllUserInfoDto convertToAllUserInfoDto(AbstractUser user){
        AllUserInfoDto dto = new AllUserInfoDto();
        dto.setId(user.getId());
        dto.setRoleName(user.getUserRole().getValue());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPatronymicName(user.getPatronymicName());
        dto.setUserName(user.getUserName());
        dto.setUserPassword(user.getUserPassword());
        return dto;
    }

    public AbstractUser convertToUser(AllUserInfoDto dto){
        AbstractUser user = new AbstractUser();
        user = getBaseUser(user, dto);
        return user;
    }

    public UserLoginPasswordDto convertToLoginAndPasswordDto(AbstractUser user){
        UserLoginPasswordDto dto = new UserLoginPasswordDto();
        dto.setId(user.getId());
        dto.setUserName(user.getUserName());
        dto.setUserPassword(user.getUserPassword());
        return dto;
    }

    public Buyer convertToBuyer(BuyerInfoDto dto) throws ConvertingException{
        throwExceptionIfDtoIsNotValid(dto);

        Buyer buyer = new Buyer();
        buyer = (Buyer)getBaseUser(buyer, dto);
        buyer.setEmail(dto.getEmail());
        buyer.setPhone(dto.getPhone());
        return buyer;
    }

    public BuyerInfoDto convertToBuyerInfoDto(Buyer buyer){
        BuyerInfoDto dto = new BuyerInfoDto();
        dto.setId(buyer.getId());
        dto.setEmail(buyer.getEmail());
        dto.setRoleName(buyer.getUserRole().getValue());
        dto.setPhone(buyer.getPhone());
        dto.setFirstName(buyer.getFirstName());
        dto.setLastName(buyer.getLastName());
        dto.setPatronymicName(buyer.getPatronymicName());
        dto.setUserName(buyer.getUserName());
        dto.setUserPassword(buyer.getUserPassword());
        return dto;
    }

    public Seller convertToSeller(SellerInfoDto dto) throws ConvertingException{
        throwExceptionIfDtoIsNotValid(dto);

        Seller seller = new Seller();
        seller = (Seller)getBaseUser(seller, dto);
        Position position = new Position();
        position.setId(dto.getPositionId());
        seller.setPosition(position);
        return seller;
    }

    public SellerInfoDto convertToSellerInfoDto(Seller seller){
        SellerInfoDto dto = new SellerInfoDto();
        dto.setId(seller.getId());
        dto.setRoleName(seller.getUserRole().getValue());
        dto.setFirstName(seller.getFirstName());
        dto.setLastName(seller.getLastName());
        dto.setPatronymicName(seller.getPatronymicName());
        dto.setUserName(seller.getUserName());
        dto.setUserPassword(seller.getUserPassword());
        dto.setPositionId(seller.getPosition().getId());
        return dto;
    }

    private AbstractUser getBaseUser(AbstractUser user, AllUserInfoDto dto){
        user.setId(dto.getId());
        user.setUserRole(RoleEnum.valueOf(dto.getRoleName()));
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPatronymicName(dto.getPatronymicName());
        user.setUserName(dto.getUserName());
        user.setUserPassword(dto.getUserPassword());
        return user;
    }

    private void throwExceptionIfDtoIsNotValid(AllUserInfoDto dto) throws ConvertingException {
        if (dto == null)
            throw new ConvertingException("Must be not null.");
        if (dto.getRoleName() == null)
            throw new ConvertingException("Role must be not null.");
        if (dto.getFirstName() == null || dto.getLastName() == null || dto.getPatronymicName() == null)
            throw new ConvertingException("Name must be not null.");

        if (dto instanceof SellerInfoDto) {
            if (((SellerInfoDto) dto).getPositionId() == null)
                throw new ConvertingException(("Position must be not null"));
        }
    }

}

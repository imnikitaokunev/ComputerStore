package com.nikitkasss.store.service.converter;

import com.nikitkasss.store.dto.position.PositionInfoDto;
import com.nikitkasss.store.dto.position.PositionNameDto;
import com.nikitkasss.store.exception.ConvertingException;
import com.nikitkasss.store.model.Position;
import org.springframework.core.convert.ConversionException;
import org.springframework.stereotype.Service;

@Service
public class PositionConverter {

    public PositionNameDto convertToPositionNameDto(Position position){
        PositionNameDto dto = new PositionNameDto();
        dto.setId(position.getId());
        dto.setPositionName(position.getName());
        return dto;
    }

    public PositionInfoDto convertToPositionInfoDto(Position position){
        PositionInfoDto dto = new PositionInfoDto();
        dto.setId(position.getId());
        dto.setPositionName(position.getName());
        dto.setPositionSalary(position.getSalary());
        return dto;
    }

    public Position convertToPosition(PositionInfoDto dto){
        throwExceptionIfDtoIsNotValid(dto);

        Position position = new Position();
        position.setId(dto.getId());
        position.setName(dto.getPositionName());
        position.setSalary(dto.getPositionSalary());
        return position;
    }

    private void throwExceptionIfDtoIsNotValid(PositionInfoDto dto) throws ConvertingException {
        if(dto == null){
            throw new ConvertingException("Position must be not null.");
        }
        if(dto.getPositionName() == null){
            throw new ConvertingException("Position name must be not null.");
        }
    }

}

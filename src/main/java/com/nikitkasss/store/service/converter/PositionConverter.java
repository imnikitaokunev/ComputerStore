package com.nikitkasss.store.service.converter;

import com.nikitkasss.store.dto.PositionDto;
import com.nikitkasss.store.dto.PositionNameDto;
import com.nikitkasss.store.exception.ConvertingException;
import com.nikitkasss.store.model.Position;
import org.springframework.stereotype.Service;

@Service
public class PositionConverter {

    public PositionNameDto convertToPositionNameDto(Position position){
        PositionNameDto dto = new PositionNameDto();
        dto.setId(position.getId());
        dto.setPositionName(position.getName());
        return dto;
    }

    public PositionDto convertToPositionInfoDto(Position position){
        PositionDto dto = new PositionDto();
        dto.setId(position.getId());
        dto.setPositionName(position.getName());
        dto.setPositionSalary(position.getSalary());
        return dto;
    }

    public Position convertToPosition(PositionDto dto){
        throwExceptionIfDtoIsNotValid(dto);

        Position position = new Position();
        position.setId(dto.getId());
        position.setName(dto.getPositionName());
        position.setSalary(dto.getPositionSalary());
        return position;
    }

    private void throwExceptionIfDtoIsNotValid(PositionDto dto) throws ConvertingException {
        if(dto == null){
            throw new ConvertingException("Position must be not null.");
        }
        if(dto.getPositionName() == null){
            throw new ConvertingException("Position name must be not null.");
        }
    }

}

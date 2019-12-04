package com.nikitkasss.store.dto;

public class SellerDto extends UserDto {

    private Long positionId;

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }
}

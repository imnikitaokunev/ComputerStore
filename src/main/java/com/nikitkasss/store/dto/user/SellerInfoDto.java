package com.nikitkasss.store.dto.user;

public class SellerInfoDto extends AllUserInfoDto {

    private Long positionId;

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }
}

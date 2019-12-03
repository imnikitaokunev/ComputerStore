package com.nikitkasss.store.dto.position;

public class PositionInfoDto {

    private Long id;
    private String positionName;
    private Long positionSalary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Long getPositionSalary() {
        return positionSalary;
    }

    public void setPositionSalary(Long positionSalary) {
        this.positionSalary = positionSalary;
    }
}

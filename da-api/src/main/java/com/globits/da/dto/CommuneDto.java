package com.globits.da.dto;


import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.Commune;

public class CommuneDto extends BaseObjectDto {

    private String nameCommune;

    private DistrictDto districtDto;
    public String getNameCommune() {
        return nameCommune;
    }

    public void setNameCommune(String nameCommune) {
        this.nameCommune = nameCommune;
    }

    public CommuneDto(String nameCommune) {
        this.nameCommune = nameCommune;
    }
    public CommuneDto(Commune commune){
        this.nameCommune= commune.getNameCommune();
    }
    public CommuneDto(){

    }
    public DistrictDto getDistrictDto() {
        return districtDto;
    }

    public void setDistrictDto(DistrictDto districtDto) {
        this.districtDto = districtDto;
    }
}

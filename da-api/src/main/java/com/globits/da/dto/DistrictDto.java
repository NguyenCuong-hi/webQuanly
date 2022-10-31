package com.globits.da.dto;


import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.District;

import java.util.List;
import java.util.UUID;


public class DistrictDto extends BaseObjectDto {

    private String nameDistrict;
    private int geocodeDistrict;
    private int foundedDistrict;

    private ProvinceDto provinceDto;



    private List<CommuneDto> communeDtoList;
    public String getNameDistrict() {
        return nameDistrict;
    }

    public void setNameDistrict(String nameDistrict) {
        this.nameDistrict = nameDistrict;
    }

    public int getGeocodeDistrict() {
        return geocodeDistrict;
    }

    public void setGeocodeDistrict(int geocodeDistrict) {
        this.geocodeDistrict = geocodeDistrict;
    }

    public int getFoundedDistrict() {
        return foundedDistrict;
    }
    public ProvinceDto getProvinceDto() {
        return provinceDto;
    }
    public void setProvinceDto(ProvinceDto provinceDto) {
        this.provinceDto = provinceDto;
    }
    public void setFoundedDistrict(int foundedDistrict) {
        this.foundedDistrict = foundedDistrict;
    }

    public DistrictDto(District district){
        this.setId(district.getId());
        this.setNameDistrict(district.getNameDistrict());
        this.setGeocodeDistrict(district.getGeocodeDistrict());
        this.setFoundedDistrict(district.getFoundedDistrict());
    }
    public DistrictDto(UUID uuid){
        this.setId(uuid);
    }
    public DistrictDto(){

    }
    public List<CommuneDto> getCommuneDtoList() {
        return communeDtoList;
    }

    public void setCommuneDtoList(List<CommuneDto> communeDtoList) {
        this.communeDtoList = communeDtoList;
    }

}

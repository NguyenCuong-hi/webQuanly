package com.globits.da.dto;


import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.Province;

import java.util.List;


public class ProvinceDto extends BaseObjectDto {

    private String nameProvince;
    private String geocodeProvince;
    private String postalCodeProvince;
    private List<DistrictDto> districtDtoList;

    public String getNameProvince() {
        return nameProvince;
    }

    public void setNameProvince(String nameProvince) {
        this.nameProvince = nameProvince;
    }

    public String getGeocodeProvince() {
        return geocodeProvince;
    }

    public void setGeocodeProvince(String geocodeProvince) {
        this.geocodeProvince = geocodeProvince;
    }

    public String getPostalCodeProvince() {
        return postalCodeProvince;
    }

    public void setPostalCodeProvince(String postalCodeProvince) {
        this.postalCodeProvince = postalCodeProvince;
    }

    public List<DistrictDto> getDistrictDtoList() {
        return districtDtoList;
    }

    public ProvinceDto(){

    }
}

package com.globits.da.domain;

import com.globits.core.domain.BaseObject;
import com.globits.da.convert.ConvertAddress;
import com.globits.da.dto.ProvinceDto;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "tbl_province")
public class Province extends BaseObject {


    @Column(name = "name_province")
    private String nameProvince;

    @Column(name = "geocode_province")
    private String geocodeProvince;

    @Column(name = "postalCode_province")
    private String postalCodeProvince;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "province")
    private List<District> districtList;

    public Province() {
    }

    public String getNameProvince() {
        return nameProvince;
    }

    public void setNameProvince(String nameProvince) {
        this.nameProvince = nameProvince;
    }


    public String getGeocodeProvince() {
        return geocodeProvince;
    }

    public void setGeocodeProvince(String geocodes) {
        geocodeProvince = geocodes;
    }

    public String getPostalCodeProvince() {
        return postalCodeProvince;
    }

    public void setPostalCodeProvince(String postalCodeProvince) {
        this.postalCodeProvince = postalCodeProvince;
    }

    public void setDistrictList(List<District> districts) {
        this.districtList = districts;
    }

    public List<District> getDistrictList() {
        return districtList;
    }


}

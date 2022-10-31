package com.globits.da.domain;

import com.globits.core.domain.BaseObject;
import com.globits.da.convert.ConvertAddress;
import com.globits.da.dto.DistrictDto;



import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "tbl_district")
public class District extends BaseObject {
    @Column(name = "name_district", nullable = false)
    private String nameDistrict;

    @Column(name = "geocode_district")
    private int geocodeDistrict;

    @Column(name = "founded_year")
    private int foundedDistrict;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id", nullable = false, referencedColumnName = "id")
    private Province province;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "district",cascade = CascadeType.ALL)
    private List<Commune> communeList;
    public District()
    {
    }
    public District (DistrictDto districtDto){
        this.setNameDistrict(districtDto.getNameDistrict());
        this.setGeocodeDistrict(districtDto.getGeocodeDistrict());
        this.setFoundedDistrict(districtDto.getFoundedDistrict());
        Province province = new Province();
        province.setId(districtDto.getProvinceDto().getId());
        this.setProvince(province);
    }



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

    public void setFoundedDistrict(int foundedDistrict) {
        this.foundedDistrict = foundedDistrict;
    }
    public Province getProvince() {
        return province;
    }

    public List<Commune> getCommuneList() {
        return communeList;
    }

    public void setCommuneList(List<Commune> communeList) {
        this.communeList = communeList;
    }
    public void setProvince(Province province) {
        this.province = province;
    }


}

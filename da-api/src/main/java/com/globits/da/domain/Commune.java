package com.globits.da.domain;

import com.globits.core.domain.BaseObject;
import com.globits.da.dto.CommuneDto;


import javax.persistence.*;


@Entity
@Table(name = "tbl_commune")
public class Commune extends BaseObject {
    @Column(name = "name_commune")
    private String nameCommune;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id",nullable = false,referencedColumnName = "id")
    private District district;


    public Commune() {
    }

    public String getNameCommune() {
        return nameCommune;
    }

    public void setNameCommune(String nameCommune) {
        this.nameCommune = nameCommune;
    }
    public Commune (CommuneDto communeDto){
        this.setNameCommune(communeDto.getNameCommune());
        District district1 = new District();
        district1.setId(communeDto.getDistrictDto().getId());
        this.setDistrict(district1);
    }
    public Commune(CommuneDto communeDto, District district){
        this.setNameCommune(communeDto.getNameCommune());
        this.setDistrict(district);
    }
    public void updateCommuneById(CommuneDto communeDto){
        this.setNameCommune(communeDto.getNameCommune());
    }
    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public void setCommune(CommuneDto communeDto){
        this.setNameCommune(communeDto.getNameCommune());

    }
}

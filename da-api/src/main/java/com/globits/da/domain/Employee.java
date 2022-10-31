package com.globits.da.domain;

import com.globits.core.domain.BaseObject;
import com.globits.da.dto.EmployeeDto;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tbl_employee")
public class Employee  extends BaseObject{

    @Column(name ="code")
    private String code;
    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;
    @Column(name="phone")
    private String phone;
    @Column(name = "age")
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProvince", referencedColumnName = "id",nullable = false)
    private  Province province;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDistrict",referencedColumnName = "id",nullable = false)
    private District district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCommune",referencedColumnName = "id",nullable = false)
    private Commune commune;

    public Employee() {
    }

    public Employee(EmployeeDto employeeDto) {

        this.setCode(employeeDto.getCode());
        this.setName(employeeDto.getName());
        this.setEmail(employeeDto.getEmail());
        this.setPhone(employeeDto.getPhone());
        this.setAge(employeeDto.getAge());
        Province province = new Province();
        province.setId(employeeDto.getProvinceId());
        this.setProvince(province);
        District district = new District();
        district.setId(employeeDto.getDistrictId());
        this.setDistrict(district);
        Commune commune = new Commune();
        commune.setId(employeeDto.getCommuneId());
        this.setCommune(commune);
    }

    public void setData(EmployeeDto employeeDto){
        this.setCode(employeeDto.getCode());
        this.setName(employeeDto.getName());
        this.setEmail(employeeDto.getEmail());
        this.setPhone(employeeDto.getPhone());
        this.setAge(employeeDto.getAge());
        Province province = new Province();
        province.setId(employeeDto.getProvinceId());
        this.setProvince(province);
        District district = new District();
        district.setId(employeeDto.getDistrictId());
        this.setDistrict(district);
        Commune commune = new Commune();
        commune.setId(employeeDto.getCommuneId());
        this.setCommune(commune);

    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Commune getCommune() {
        return commune;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }

}

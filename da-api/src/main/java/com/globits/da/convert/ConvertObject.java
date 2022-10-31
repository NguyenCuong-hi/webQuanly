package com.globits.da.convert;

import com.globits.da.domain.*;
import com.globits.da.dto.*;
import com.globits.da.repository.CommuneRepository;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ConvertObject {
    private static ProvinceRepository provinceRepository;
    private static DistrictRepository districtRepository;
    private static CommuneRepository communeRepository;

    @Autowired
    public ConvertObject(ProvinceRepository provinceRepository, DistrictRepository districtRepository, CommuneRepository communeRepository) {
        ConvertObject.provinceRepository = provinceRepository;
        ConvertObject.districtRepository = districtRepository;
        ConvertObject.communeRepository = communeRepository;
    }

    public static CertificateEmployee convertDtoToEntityCertificateEmployee(CertificateEmployee certificateEmployee, CertificateEmployeeDto certificateEmployeeDto) {
        Employee employee = new Employee();
        employee.setId(certificateEmployeeDto.getEmployeeDtoId());
        certificateEmployee.setEmployee(employee);

        Province province = new Province();
        province.setId(certificateEmployeeDto.getProvinceDtoId());
        certificateEmployee.setProvince(province);

        Certificate certificate = new Certificate();
        certificate.setId(certificateEmployeeDto.getCertificateDtoId());
        certificateEmployee.setCertificate(certificate);

        certificateEmployee.setEffectiveDate(certificateEmployeeDto.getEffectiveDate());
        certificateEmployee.setExpirationDate(certificateEmployeeDto.getExpirationDate());

        return certificateEmployee;
    }

    public static List<CertificateEmployee> convertDtoToEntityListCertificateEmployee(UUID idProvince, List<CertificateEmployeeDto> certificateEmployeeDtoList) {
        List<CertificateEmployee> certificateEmployeeList = new ArrayList<>();

        for (CertificateEmployeeDto certificateEmployeeDto : certificateEmployeeDtoList) {

            CertificateEmployee certificateEmployee = new CertificateEmployee();
            Employee employee = new Employee();
            employee.setId(certificateEmployeeDto.getEmployeeDtoId());
            certificateEmployee.setEmployee(employee);

            Province province = new Province();
            province.setId(idProvince);
            certificateEmployee.setProvince(province);

            Certificate certificate = new Certificate();
            certificate.setId(certificateEmployeeDto.getCertificateDtoId());
            certificateEmployee.setCertificate(certificate);

            certificateEmployee.setEffectiveDate(certificateEmployeeDto.getEffectiveDate());
            certificateEmployee.setExpirationDate(certificateEmployeeDto.getExpirationDate());

            certificateEmployeeList.add(certificateEmployee);

        }
        return certificateEmployeeList;
    }

    public static Certificate convertDtoToEntityCertificate(Certificate certificate, CertificateDto certificateDto) {
        certificate.setNameCertificate(certificateDto.getNameCertificate());
        certificate.setEffectiveDate(certificateDto.getEffectiveDate());
        certificate.setExpirationDate(certificateDto.getExpirationDate());
        return certificate;
    }

    public static CertificateDto convertEntityToDtoCertificate(CertificateDto certificateDto, Certificate certificate) {
        certificateDto.setNameCertificate(certificate.getNameCertificate());
        certificateDto.setEffectiveDate(certificateDto.getEffectiveDate());
        certificateDto.setExpirationDate(certificate.getExpirationDate());
        certificateDto.setModifiedBy(certificate.getModifiedBy());
        return certificateDto;
    }

    public static Employee convertDtoToEntityEmployee(EmployeeDto employeeDto, Employee employee) {
        employee.setCode(employeeDto.getCode());
        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());
        employee.setPhone(employeeDto.getPhone());
        employee.setAge(employeeDto.getAge());
        Province province = new Province();
        province.setId(employeeDto.getProvinceId());
        employee.setProvince(province);
        District district = new District();
        district.setId(employeeDto.getDistrictId());
        employee.setDistrict(district);
        Commune commune = new Commune();
        commune.setId(employeeDto.getCommuneId());
        employee.setCommune(commune);
        return employee;
    }

    public static Province convertDtoToEntityProvince(ProvinceDto provinceDto, Province province) {

            province.setNameProvince(provinceDto.getNameProvince());
            province.setGeocodeProvince(provinceDto.getGeocodeProvince());
            province.setPostalCodeProvince(provinceDto.getPostalCodeProvince());
            List<DistrictDto> districtDtoList = provinceDto.getDistrictDtoList();
            List<District> districtList = new ArrayList<>();
            if (districtDtoList != null) {
                for (DistrictDto districtDto : districtDtoList) {
                    District district ;
                    UUID districtId = districtDto.getId();
                    if (districtId == null) {
                        district = new District();
                    } else {
                        district = districtRepository.getDistrictById(districtId);
                    }
                    convertDtoToEntityDistrict(district,districtDto,province.getId());
                    districtList.add(district);
                }
            }
            province.setDistrictList(districtList);

        return province;
    }

    public static District convertDtoToEntityDistrict(District district, DistrictDto districtDto,UUID provinceId) {
        district.setNameDistrict(districtDto.getNameDistrict());
        district.setGeocodeDistrict(districtDto.getGeocodeDistrict());
        district.setFoundedDistrict(districtDto.getFoundedDistrict());
        Province province = new Province();
        province.setId(provinceId);
        district.setProvince(province);
        List<CommuneDto> communeDtoList = districtDto.getCommuneDtoList();
        List<Commune> communeList = new ArrayList<>();

        if (communeDtoList != null) {
            for (CommuneDto communeDto : communeDtoList) {
              Commune  commune ;
              UUID communeId = communeDto.getId();
              if(communeId == null){
                  commune = new Commune();
              }
                else {
                    commune = communeRepository.getCommuneById(communeId);
              }
                convertDtoToEntityCommune(commune,communeDto,district.getId());
                communeList.add(commune);
            }
        }
        district.setCommuneList(communeList);
        return district;
    }

    public static Commune convertDtoToEntityCommune(Commune commune, CommuneDto communeDto,UUID districtId) {
        commune.setNameCommune(communeDto.getNameCommune());
        District district = new District();
        district.setId(districtId);
        commune.setDistrict(district);
        return commune;
    }


    // Convert domain to Dto

    public static DistrictDto convertEntityToDtoDistrict(District district, DistrictDto districtDto){
        districtDto.setNameDistrict(district.getNameDistrict());
        districtDto.setFoundedDistrict(district.getFoundedDistrict());
        ProvinceDto provinceDto = new ProvinceDto();
        provinceDto.setId(district.getProvince().getId());
        districtDto.setProvinceDto(provinceDto);
        return  districtDto;
    }
    public static ProvinceDto convertEntityToDtoProvince(ProvinceDto provinceDto, Province province){
        provinceDto.setNameProvince(province.getNameProvince());
        provinceDto.setGeocodeProvince(province.getGeocodeProvince());
        provinceDto.setPostalCodeProvince(province.getPostalCodeProvince());
        provinceDto.setCreateDate(province.getCreateDate());
        provinceDto.setCreatedBy(province.getCreatedBy());
        provinceDto.setModifiedBy(province.getModifiedBy());
        provinceDto.setModifiedBy(province.getModifiedBy());
        return  provinceDto;
    }

    public static CommuneDto convertEntityToDtoCommune(CommuneDto communeDto, Commune commune){
        communeDto.setNameCommune(commune.getNameCommune());
        DistrictDto districtDto = new DistrictDto();
        districtDto.setId(commune.getDistrict().getId());
        communeDto.setDistrictDto(districtDto);
        return communeDto;
    }

    public static EmployeeDto convertEntityToDtoEmployee(EmployeeDto employeeDto, Employee employee) {
        employeeDto.setCode(employee.getCode());
        employeeDto.setName(employee.getName());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setPhone(employee.getPhone());
        employeeDto.setAge(employee.getAge());
        employeeDto.setProvinceId(employee.getProvince().getId());
        employeeDto.setDistrictId(employee.getDistrict().getId());
        employeeDto.setCommuneId(employee.getCommune().getId());
        return employeeDto;
    }

}

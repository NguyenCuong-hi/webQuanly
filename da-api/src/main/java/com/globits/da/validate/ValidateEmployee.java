package com.globits.da.validate;

import com.globits.da.Constants;
import com.globits.da.domain.Commune;
import com.globits.da.domain.District;
import com.globits.da.domain.Employee;
import com.globits.da.domain.Province;
import com.globits.da.dto.EmployeeDto;
import com.globits.da.repository.CommuneRepository;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.repository.EmployeeRepository;
import com.globits.da.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;


import java.util.UUID;
import java.util.regex.Pattern;

@Component
public class ValidateEmployee {
    @Autowired
    private  EmployeeRepository employeeRepository;

    @Autowired
    private  ProvinceRepository provinceRepository;
    @Autowired
    private  DistrictRepository districtRepository;
    @Autowired
    private  CommuneRepository communeRepository;

    public ResponseStatus checkEmployee(UUID employeeId, EmployeeDto employeeDto) {
        if (!StringUtils.hasText(employeeDto.getCode())) {
            return ResponseStatus.EMPLOYEE_CODE_IS_NULL;
        }
        if (!Pattern.compile(Constants.PATTERN_EMPLOYEE_CODE).matcher(employeeDto.getCode()).matches()) {
            return ResponseStatus.EMPLOYEE_CODE_REGEX;
        }
        if (!StringUtils.hasText(employeeDto.getName())) {
            return ResponseStatus.EMPLOYEE_NAME_IS_NULL;
        }
        if (!StringUtils.hasText(employeeDto.getEmail())) {
            return ResponseStatus.EMPLOYEE_EMAIL_NOT_NULL;
        }

        if (!Pattern.compile(Constants.PATTERN_EMAIL).matcher(employeeDto.getEmail()).matches()) {
            return ResponseStatus.EMPLOYEE_EMAIL_REGEX;
        }
        if(!StringUtils.hasText(employeeDto.getPhone())){
            return ResponseStatus.PHONE_NUMBER_IS_NULL;
        }
        if (!Pattern.compile(Constants.PATTERN_PHONE).matcher(employeeDto.getPhone()).matches()) {
            return ResponseStatus.EMPLOYEE_PHONE_REGEX;
        }
        if (employeeDto.getAge() < 0) {
            return ResponseStatus.EMPLOYEE_CHECK_AGE;
        }
        if (ObjectUtils.isEmpty(employeeDto.getProvinceId())) {
            return ResponseStatus.PROVINCE_IS_NULL;
        }
        if (ObjectUtils.isEmpty(employeeDto.getDistrictId())) {
            return ResponseStatus.DISTRICT_IS_NULL;
        }
        if (ObjectUtils.isEmpty(employeeDto.getCommuneId())) {
            return ResponseStatus.COMMUNE_IS_NULL;
        }
        District district = districtRepository.getDistrictById(employeeDto.getDistrictId());
        Commune commune = communeRepository.getCommuneById(employeeDto.getCommuneId());
        if (!employeeDto.getProvinceId().equals(district.getProvince().getId())) {
            return ResponseStatus.DISTRICT_NO_DEPEND_PROVINCE;
        }
        if (!employeeDto.getDistrictId().equals(commune.getDistrict().getId())) {
            return ResponseStatus.COMMUNE_NO_DEPEND_DISTRICT;
        }

        if (!employeeDto.getCode().equals(employeeRepository.getEmployeeById(employeeId))) {
            return ResponseStatus.EMPLOYEE_CODE_NO_EXIST;
        }
        return ResponseStatus.DONE;
    }


}

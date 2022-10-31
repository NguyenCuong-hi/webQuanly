package com.globits.da.service.impl;

import com.globits.da.convert.ConvertObject;
import com.globits.da.domain.CertificateEmployee;
import com.globits.da.dto.CertificateEmployeeDto;
import com.globits.da.repository.CertificateEmployeeRepository;
import com.globits.da.service.CertificateEmployeeService;
import com.globits.da.validate.Response;
import com.globits.da.validate.ResponseStatus;
import com.globits.da.validate.ValidateCertificateEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CertificateEmployeeServiceImp implements CertificateEmployeeService {

    @Autowired
    private CertificateEmployeeRepository certificateEmployeeRepository;
    @Autowired
    private ValidateCertificateEmployee validateCertificateEmployee;


    @Override
    public Response<CertificateEmployeeDto> createCertificateEmployee(CertificateEmployeeDto certificateEmployeeDto) {
        ResponseStatus responseStatus = validateCertificateEmployee.checkEmployeeCertificate(certificateEmployeeDto);
        if (!responseStatus.equals(ResponseStatus.DONE)) {
            return new Response<>(null,responseStatus);
        }
        CertificateEmployee certificateEmployee = new CertificateEmployee();
        ConvertObject.convertDtoToEntityCertificateEmployee(certificateEmployee, certificateEmployeeDto);
        certificateEmployeeRepository.save(certificateEmployee);
        return new Response<>(certificateEmployeeDto,responseStatus);
    }

    @Override
    public Response<CertificateEmployeeDto> editCertificateEmployee(UUID idCertificateEmployee, CertificateEmployeeDto certificateEmployeeDto) {
        CertificateEmployee certificateEmployee = certificateEmployeeRepository.getOne(idCertificateEmployee);
        ResponseStatus response = validateCertificateEmployee.checkEmployeeCertificate(certificateEmployeeDto);
        if(!response.equals(ResponseStatus.DONE)){
            return new Response<>(null,response);
        }
        ConvertObject.convertDtoToEntityCertificateEmployee(certificateEmployee, certificateEmployeeDto);
        certificateEmployeeRepository.save(certificateEmployee);
        return new Response<>(certificateEmployeeDto, response);
    }

    @Override
    public Response<Boolean> deleteCertificateEmployee(UUID idCertificate) {
        if(idCertificate == null){
            return new Response<>(null,ResponseStatus.CERTIFICATE_IS_NULL);
        }
        certificateEmployeeRepository.deleteById(idCertificate);
        return new Response<>(null,ResponseStatus.DONE);
    }



    @Override
    public Response<List<CertificateEmployeeDto>> certificateEmployeeList(UUID idProvince, List<CertificateEmployeeDto> certificateEmployeeList) {
        if(idProvince == null){
            return new Response<>(null,ResponseStatus.CERTIFICATE_IS_NULL);
        }
        List<CertificateEmployee> certificateEmployees = ConvertObject.convertDtoToEntityListCertificateEmployee(idProvince,certificateEmployeeList);
        certificateEmployeeRepository.saveAll(certificateEmployees);

        return new Response<>(certificateEmployeeList,ResponseStatus.DONE);
    }
}

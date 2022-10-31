package com.globits.da.service;

import com.globits.da.dto.CertificateEmployeeDto;
import com.globits.da.validate.Response;


import java.util.List;
import java.util.UUID;


public interface CertificateEmployeeService {
    public Response<CertificateEmployeeDto> createCertificateEmployee(CertificateEmployeeDto certificateEmployeeDto);
    public Response<CertificateEmployeeDto> editCertificateEmployee(UUID idCertificateEmployee, CertificateEmployeeDto certificateEmployeeDto);
    public Response<Boolean> deleteCertificateEmployee(UUID idCertificate);
    public Response<List<CertificateEmployeeDto>> certificateEmployeeList (UUID idProvince, List<CertificateEmployeeDto> certificateEmployeeList);
}

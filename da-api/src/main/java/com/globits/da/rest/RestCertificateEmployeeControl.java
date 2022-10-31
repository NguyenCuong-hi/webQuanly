package com.globits.da.rest;

import com.globits.da.dto.CertificateEmployeeDto;
import com.globits.da.service.CertificateEmployeeService;
import com.globits.da.validate.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/CertificateEmployee")
public class RestCertificateEmployeeControl {
    @Autowired
    CertificateEmployeeService certificateEmployeeService;
    @PostMapping(value = "/createCertificateEmployee")
    public Response<CertificateEmployeeDto> create(@RequestBody CertificateEmployeeDto certificateEmployeeDto){
        return  certificateEmployeeService.createCertificateEmployee(certificateEmployeeDto);

    }
    @PutMapping("/{idCertificateEmployee}")
    public Response<CertificateEmployeeDto> editCertificate(@PathVariable("idCertificateEmployee") UUID IdCertificateEmployee, @RequestBody CertificateEmployeeDto certificateEmployeeDto){
        return  certificateEmployeeService.editCertificateEmployee(IdCertificateEmployee, certificateEmployeeDto);

    }
    @DeleteMapping("/{idCertificateEmployee}")
    public Response<Boolean> delete(@PathVariable("idCertificateEmployee") UUID idCertificateEmployee){
        return   certificateEmployeeService.deleteCertificateEmployee(idCertificateEmployee);
    }

    @PostMapping("/addListCertificate/{idProvince}")
    public Response<List<CertificateEmployeeDto>> createListCertificate (@PathVariable UUID idProvince, @RequestBody List<CertificateEmployeeDto> certificateEmployeeDtoList){
        return  certificateEmployeeService.certificateEmployeeList(idProvince, certificateEmployeeDtoList);

    }

}

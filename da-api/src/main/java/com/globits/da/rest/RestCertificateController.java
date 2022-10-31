package com.globits.da.rest;

import com.globits.da.dto.CertificateDto;
import com.globits.da.service.CertificateService;
import com.globits.da.validate.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/certificate")
public class RestCertificateController {
    @Autowired
    CertificateService certificateService;
    @PostMapping("/addNewCertificate")
    public Response<CertificateDto> addNewCertificate(@RequestBody CertificateDto certificateDto){
      return  certificateService.createNewCertificate(certificateDto);

    }
    @GetMapping("/getCertificate/{id}")
    public Response<CertificateDto> getCertificateById(@PathVariable(value = "id") UUID id){
        return  certificateService.getCertificate(id);

    }
    @PutMapping("/updateCertificate/{id}")
    public Response<CertificateDto> updateCertificateById(@PathVariable("id") UUID id, @RequestBody CertificateDto certificateDto){
        return  certificateService.updateCertificateById(id,certificateDto);

    }
    @DeleteMapping("/{id}")
    public Response<CertificateDto> deleteCertificateById(@PathVariable("id") UUID id){
        return certificateService.deleteCertificateById(id);

    }

}

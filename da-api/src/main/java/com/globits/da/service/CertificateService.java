package com.globits.da.service;

import com.globits.da.dto.CertificateDto;
import com.globits.da.validate.Response;

import java.util.UUID;

public interface CertificateService {
    public Response<CertificateDto> createNewCertificate(CertificateDto certificateDto);
    public Response<CertificateDto> getCertificate(UUID id);
    public Response<CertificateDto> updateCertificateById(UUID id, CertificateDto certificateDto);
    public Response<CertificateDto> deleteCertificateById(UUID id) ;
    }

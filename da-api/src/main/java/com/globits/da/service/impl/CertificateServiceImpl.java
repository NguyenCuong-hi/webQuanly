package com.globits.da.service.impl;

import com.globits.da.convert.ConvertObject;
import com.globits.da.domain.Certificate;

import com.globits.da.dto.CertificateDto;
import com.globits.da.repository.CertificateRepository;
import com.globits.da.service.CertificateService;
import com.globits.da.validate.Response;
import com.globits.da.validate.ResponseStatus;
import com.globits.da.validate.ValidateCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("CertificateService")
public class CertificateServiceImpl implements CertificateService {
    @Autowired
    CertificateRepository certificateRepository;
    @Autowired
    private ValidateCertificate validateCertificate;
    @Autowired
    private ConvertObject convertObject;

    @Override
    public Response<CertificateDto> createNewCertificate(CertificateDto certificateDto) {
        ResponseStatus response = validateCertificate.handleValidateCertificate(certificateDto);
        if (!response.equals(ResponseStatus.DONE)) {
            return new Response<>(null, response);
        }
        Certificate certificate = new Certificate();
        ConvertObject.convertDtoToEntityCertificate(certificate, certificateDto);
        certificateRepository.save(certificate);
        return new Response<>(certificateDto, response);
    }

    @Override
    public Response<CertificateDto> getCertificate(UUID certificateID) {
        if(certificateID == null){
            return new Response<>(null,ResponseStatus.CERTIFICATE_IS_NULL);
        }
        Certificate certificate = certificateRepository.getCertificateById(certificateID);
        CertificateDto certificateDto = new CertificateDto();
        convertObject.convertEntityToDtoCertificate(certificateDto,certificate);
        ResponseStatus responseStatus = validateCertificate.handleValidateCertificate(certificateDto);
        return new Response<>(certificateDto,responseStatus);
    }

    @Override
    public Response<CertificateDto> updateCertificateById(UUID certificateId, CertificateDto certificateDto) {
        if (certificateId == null){
            return new Response<>(null,ResponseStatus.CERTIFICATE_IS_NULL);
        }
        ResponseStatus responseStatus = validateCertificate.handleValidateCertificate(certificateDto);
        if(!responseStatus.equals(ResponseStatus.DONE)){
            return new Response<>(null,responseStatus);
        }
        Certificate certificate = certificateRepository.getOne(certificateId);
        ConvertObject.convertDtoToEntityCertificate(certificate, certificateDto);
        certificateRepository.save(certificate);
        return new Response<>(certificateDto,responseStatus);

    }

    @Override
    public Response<CertificateDto> deleteCertificateById(UUID certificateId) {
        if(certificateId == null){
            return new Response<>(null,ResponseStatus.CERTIFICATE_IS_NULL);
        }
        certificateRepository.deleteById(certificateId);
        return new Response<>(null,ResponseStatus.DONE);
    }


}

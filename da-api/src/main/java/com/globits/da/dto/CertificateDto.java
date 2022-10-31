package com.globits.da.dto;

import com.globits.core.dto.BaseObjectDto;


import java.sql.Date;

public class CertificateDto extends BaseObjectDto {
    private String nameCertificate;
    private Date effectiveDate;
    private Date expirationDate;



   public CertificateDto ()
    {
        super();
    }

    public String getNameCertificate() {
        return nameCertificate;
    }

    public void setNameCertificate(String nameCertificate) {
        this.nameCertificate = nameCertificate;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public CertificateDto(String nameCertificate, Date effectiveDate, Date expirationDate) {
        this.nameCertificate = nameCertificate;
        this.effectiveDate = effectiveDate;
        this.expirationDate = expirationDate;
    }
}

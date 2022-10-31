package com.globits.da.domain;

import com.globits.core.domain.BaseObject;
import com.globits.da.dto.CertificateDto;
import com.globits.da.dto.CertificateEmployeeDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "tbl_certificate")
public class Certificate extends BaseObject {
    @Column(name = "name_certificate")
    private String nameCertificate;

    @Column(name = "effective_date")
    private Date effectiveDate;

    @Column(name = "expiration_date")
    private Date expirationDate;

    public Certificate() {
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

}

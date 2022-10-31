package com.globits.da.dto;

import com.globits.core.dto.BaseObjectDto;


import java.sql.Date;
import java.util.UUID;

public class CertificateEmployeeDto extends BaseObjectDto {
    private UUID employeeDtoId;
    private UUID provinceDtoId;
    private UUID certificateDtoId;
    private Date effectiveDate;
    private Date expirationDate;

    public UUID getEmployeeDtoId() {
        return employeeDtoId;
    }

    public void setEmployeeDtoId(UUID employeeDtoId) {
        this.employeeDtoId = employeeDtoId;
    }

    public UUID getProvinceDtoId() {
        return provinceDtoId;
    }

    public void setProvinceDtoId(UUID provinceDtoId) {
        this.provinceDtoId = provinceDtoId;
    }

    public UUID getCertificateDtoId() {
        return certificateDtoId;
    }

    public void setCertificateDtoId(UUID certificateDtoId) {
        this.certificateDtoId = certificateDtoId;
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

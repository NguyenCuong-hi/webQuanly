package com.globits.da.domain;

import com.globits.core.domain.BaseObject;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "certificate_employee")
public class CertificateEmployee extends BaseObject {
    @ManyToOne
    @JoinColumn(name = "employeeId", referencedColumnName = "id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name="certificateId", referencedColumnName = "id",nullable = false)
    private Certificate certificate;

    @ManyToOne
    @JoinColumn(name = "provinceId", referencedColumnName = "id", nullable = false)
    private Province province;

    @Column(name = "effective_date")
    private  Date effectiveDate;

    @Column(name = "expiration_date")
    private Date expirationDate;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
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
    public CertificateEmployee(){

    }

}

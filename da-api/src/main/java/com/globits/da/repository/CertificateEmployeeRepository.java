package com.globits.da.repository;


import com.globits.da.domain.CertificateEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CertificateEmployeeRepository extends JpaRepository<CertificateEmployee,UUID> {
    CertificateEmployee getCertificateEmployeeById(UUID id);

    @Query(value = "select count(e) from CertificateEmployee e " +
            "where e.employee.id = ?1 " +
            " and e.certificate.id = ?2 and e.expirationDate >= current_date")
    Integer countCertificateEmployee(UUID employeeId, UUID CertificateId);

}

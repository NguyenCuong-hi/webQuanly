package com.globits.da.validate;



import com.globits.da.Constants;
import com.globits.da.dto.CertificateEmployeeDto;
import com.globits.da.repository.CertificateEmployeeRepository;
import com.globits.da.repository.CertificateRepository;
import com.globits.da.repository.EmployeeRepository;
import com.globits.da.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;


@Component
public class ValidateCertificateEmployee {
    @Autowired
    private static EmployeeRepository employeeRepository;
    @Autowired
    private  static ProvinceRepository provinceRepository;
    @Autowired
    private  static CertificateRepository certificateRepository;
    @Autowired
     CertificateEmployeeRepository certificateEmployeeRepository;

    public ResponseStatus checkEmployeeCertificate(CertificateEmployeeDto certificateEmployeeDto){
        if (certificateEmployeeRepository.countCertificateEmployee(certificateEmployeeDto.getEmployeeDtoId(),certificateEmployeeDto.getCertificateDtoId()) > 1)
        {
            return ResponseStatus.CERTIFICATE_DUPLICATE;
        }
        if(ObjectUtils.isEmpty(certificateEmployeeDto.getCertificateDtoId())){
            return ResponseStatus.CERTIFICATE_EMPLOYEE_ID_CERTIFICATE_IS_NULL;
        }
        if(ObjectUtils.isEmpty(certificateEmployeeDto.getEmployeeDtoId())){
            return ResponseStatus.CERTIFICATE_EMPLOYEE_ID_EMPLOYEE_IS_NULL;
        }
        if(ObjectUtils.isEmpty(certificateEmployeeDto.getProvinceDtoId())){
            return ResponseStatus.CERTIFICATE_EMPLOYEE_ID_PROVINCE_IS_NULL;
        }
        return ResponseStatus.DONE;
    }


}

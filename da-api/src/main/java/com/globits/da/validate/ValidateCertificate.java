package com.globits.da.validate;

import com.globits.da.domain.Certificate;
import com.globits.da.dto.CertificateDto;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.time.LocalDate;

@Component
public class ValidateCertificate {
    public static ResponseStatus handleValidateCertificate(CertificateDto certificateDto) {

        if (!StringUtils.hasText(certificateDto.getNameCertificate())) {
            return ResponseStatus.CERTIFICATE_NAME_IS_NULL;
        }
        if (ObjectUtils.isEmpty(certificateDto.getEffectiveDate())) {
            return ResponseStatus.CERTIFICATE_EFFECTIVE_IS_NULL;
        }
        Date nowDate = Date.valueOf(LocalDate.now());
        if (!certificateDto.getEffectiveDate().before(nowDate)) {
            return ResponseStatus.CERTIFICATE_NOT_EXPIRATION;
        }

        return ResponseStatus.DONE;
    }


}

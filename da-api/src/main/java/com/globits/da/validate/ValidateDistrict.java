package com.globits.da.validate;

import com.globits.da.dto.DistrictDto;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

@Component
public class ValidateDistrict {
    public static ResponseStatus handleValidateDistrict( DistrictDto districtDto) {

        // check null district
        if(districtDto.getNameDistrict().isEmpty()){
            return ResponseStatus.DISTRICT_NAME_IS_NULL;
        }
        if(ObjectUtils.isEmpty(districtDto.getGeocodeDistrict()))
        {
            return ResponseStatus.DISTRICT_GEOCODE_IS_NUL;
        }
        if(ObjectUtils.isEmpty(districtDto.getFoundedDistrict())){
            return ResponseStatus.DISTRICT_FOUNDED_YEAR_IS_NULL;
        }
        // check form district



      return ResponseStatus.DONE;
    }
}

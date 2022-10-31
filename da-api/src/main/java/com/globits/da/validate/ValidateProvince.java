package com.globits.da.validate;


import com.globits.da.dto.ProvinceDto;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Component
public class ValidateProvince {
    public ResponseStatus checkProvince(ProvinceDto provinceDto){
        if(!StringUtils.hasText(provinceDto.getNameProvince())){
            return ResponseStatus.PROVINCE_NAME_IS_NULL;
        }
        if(!StringUtils.hasText(provinceDto.getGeocodeProvince())){
            return ResponseStatus.PROVINCE_GEOCODE_IS_NULL;
        }
        if(ObjectUtils.isEmpty(provinceDto.getId())){
            return ResponseStatus.PROVINCE_IS_NULL;
        }
        return ResponseStatus.DONE;
    }

}

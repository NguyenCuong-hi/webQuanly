package com.globits.da.validate;

import com.globits.da.domain.Commune;
import com.globits.da.dto.CommuneDto;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.UUID;
@Component
public class ValidateCommune {

    public static ResponseStatus handleValidateCommune( CommuneDto communeDto){

        if(!StringUtils.hasText(communeDto.getNameCommune())){
            return ResponseStatus.COMMUNE_NAME_IS_NULL;
        }
        return ResponseStatus.DONE;
    }
    public static ResponseStatus handleValidateCommuneEntity(Commune commune){
        if(ObjectUtils.isEmpty(commune)){
            return ResponseStatus.COMMUNE_IS_NULL;
        }
        if(!StringUtils.hasText(commune.getNameCommune())){
            return ResponseStatus.COMMUNE_NAME_IS_NULL;
        }
        return ResponseStatus.DONE;
    }
}

package com.globits.da.service.impl;

import com.globits.da.convert.ConvertObject;
import com.globits.da.domain.Commune;
import com.globits.da.dto.CommuneDto;
import com.globits.da.repository.CommuneRepository;
import com.globits.da.service.CommuneService;
import com.globits.da.validate.Response;
import com.globits.da.validate.ResponseStatus;
import com.globits.da.validate.ValidateCommune;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("CommuneService")
public class CommuneServiceImpl implements CommuneService {
    @Autowired
    private CommuneRepository communeRepository;
    @Autowired
   private ConvertObject convertObject;
    @Autowired
    private ValidateCommune validateCommune;
    @Override
    public Response<CommuneDto> createCommuneWithIdDistrict(CommuneDto communeDto) {
       ResponseStatus responseStatus = validateCommune.handleValidateCommune(communeDto);
       if(responseStatus.equals(ResponseStatus.DONE)) {
           Commune commune = new Commune();
           convertObject.convertDtoToEntityCommune(commune,communeDto,communeDto.getDistrictDto().getId());
           communeRepository.save(commune);
       }

        return new Response<>(communeDto,responseStatus);
    }

    @Override
    public Response<CommuneDto> getCommuneById(UUID communeId) {
        ResponseStatus responseStatus;
        if(communeId == null){
            responseStatus = ResponseStatus.COMMUNE_IS_NULL;
        }
        Commune commune = communeRepository.getCommuneById(communeId);
        CommuneDto communeDto = new CommuneDto();
        convertObject.convertEntityToDtoCommune(communeDto,commune);
        responseStatus = validateCommune.handleValidateCommune(communeDto);
        return new Response<>(communeDto,responseStatus);
    }

    @Override
    public Response<CommuneDto> updateCommuneById(UUID districtId, CommuneDto communeDto) {
        if(districtId == null)
        {
            return new Response<>(null,ResponseStatus.COMMUNE_IS_NULL);
        }
        if(validateCommune.handleValidateCommune(communeDto).equals(ResponseStatus.DONE)) {
            Commune commune = communeRepository.getOne(districtId);
            commune.updateCommuneById(communeDto);
            communeRepository.save(commune);

        }
        return new Response<>(communeDto, ResponseStatus.DONE);
    }

    @Override
    public Response<Boolean> deleteCommuneById(UUID communeId) {
        ResponseStatus responseStatus;
        if(communeId == null){
            responseStatus = ResponseStatus.COMMUNE_IS_NULL;
        }
        communeRepository.deleteById(communeId);
        responseStatus = ResponseStatus.DONE;
        return new Response<>(null,responseStatus);
    }

}

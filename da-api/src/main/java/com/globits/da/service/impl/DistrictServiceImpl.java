package com.globits.da.service.impl;

import com.globits.da.convert.ConvertObject;
import com.globits.da.domain.Commune;
import com.globits.da.domain.District;
import com.globits.da.dto.CommuneDto;
import com.globits.da.dto.DistrictDto;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.service.DistrictService;
import com.globits.da.validate.Response;
import com.globits.da.validate.ResponseStatus;
import com.globits.da.validate.ValidateDistrict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("DistrictService")
public class DistrictServiceImpl implements DistrictService {
    @Autowired
   private DistrictRepository districtRepository;
    @Autowired
    private ConvertObject convertObject;
    @Autowired
    private ValidateDistrict validateDistrict;
    @Override
    public Response<DistrictDto> createNewDistrict(DistrictDto districtDto){
        if(!validateDistrict.handleValidateDistrict(districtDto).equals(ResponseStatus.DONE)){
            return new Response<>(null,ResponseStatus.COMMUNE_IS_NULL);
        }
        District district = new District();
        convertObject.convertDtoToEntityDistrict(district,districtDto,districtDto.getProvinceDto().getId());
        districtRepository.save(district);
        return new Response<>(districtDto,ResponseStatus.DONE);
    }

    @Override
    public Response<DistrictDto> getDistrictById(UUID districtId){
        if(districtId == null){
            return new Response<>(null,ResponseStatus.DISTRICT_IS_NULL);
        }
        DistrictDto districtDto = new DistrictDto();
        District district = districtRepository.getDistrictById(districtId);
        ConvertObject.convertEntityToDtoDistrict(district,districtDto);

        return new Response<>(districtDto,ResponseStatus.DONE);

    }
    @Override
    public Response<DistrictDto> updateDistrict(UUID districtId, DistrictDto districtDto){
        if(!validateDistrict.handleValidateDistrict(districtDto).equals(ResponseStatus.DONE)){
            return new Response<>(null,ResponseStatus.DISTRICT_IS_NULL);
        }
        District district = districtRepository.getOne(districtId);
        convertObject.convertDtoToEntityDistrict(district,districtDto,districtDto.getProvinceDto().getId());
        districtRepository.save(district);
        return new Response<>(districtDto,ResponseStatus.DONE);
    }
    @Override
    public Response<Boolean> deleteDistrictById(UUID id){
        districtRepository.deleteById(id);
        return new Response<>(null,ResponseStatus.DONE);
    }

    @Override
    public Response<List<CommuneDto>> getCommuneWithDistrictId(UUID districtId){
        List<CommuneDto> communeListDto = new ArrayList<>();
        District district = districtRepository.getDistrictById(districtId);
        for(Commune commune : district.getCommuneList()){
            communeListDto.add(new CommuneDto(commune));
        }
       return new Response<>(communeListDto,ResponseStatus.DONE);
    }


}

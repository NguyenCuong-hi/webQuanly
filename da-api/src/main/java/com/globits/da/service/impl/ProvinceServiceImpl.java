package com.globits.da.service.impl;


import com.globits.da.convert.ConvertObject;
import com.globits.da.domain.District;
import com.globits.da.domain.Province;
import com.globits.da.dto.DistrictDto;
import com.globits.da.dto.ProvinceDto;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.service.ProvinceService;
import com.globits.da.validate.Response;
import com.globits.da.validate.ResponseStatus;
import com.globits.da.validate.ValidateProvince;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service("provinceService")
public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private ValidateProvince validateProvince;
    @Autowired
    private ConvertObject convertObject;


    @Override
    public Response<ProvinceDto> createNewProvince(ProvinceDto provinceDto) {
        Province province = new Province();
        ResponseStatus responseStatus ;
        if (!validateProvince.checkProvince(provinceDto).equals(ResponseStatus.DONE)) {
            convertObject.convertDtoToEntityProvince(provinceDto, province);
            provinceRepository.save(province);
            responseStatus = ResponseStatus.DONE;
        }
        else {
            responseStatus = validateProvince.checkProvince(provinceDto);
        }

        return new Response<>(provinceDto,responseStatus);
    }

    @Override
    public Response<ProvinceDto> getProvince(UUID id) {
        if(id == null){
            return new Response<>(null,ResponseStatus.PROVINCE_IS_NULL);
        }
      Province province = provinceRepository.getProvinceById(id);
        ProvinceDto provinceDto = new ProvinceDto();
        ConvertObject.convertEntityToDtoProvince(provinceDto,province);
      return  new Response<>(provinceDto,ResponseStatus.DONE);
    }

    public Response<List<DistrictDto>> getDistrictByProvinceId(UUID id) {

        List<DistrictDto> districtDtoList = new ArrayList<>();
        Province province = provinceRepository.getProvinceById(id);
        for (District district : province.getDistrictList()) {
            DistrictDto districtDto = new DistrictDto();
            convertObject.convertEntityToDtoDistrict(district,districtDto);
            districtDtoList.add(districtDto);
        }
        return new Response<>(districtDtoList,ResponseStatus.DONE);
    }

    @Override
    public Response<ProvinceDto> update (UUID id, ProvinceDto provinceDto) {
        ResponseStatus responseStatus = validateProvince.checkProvince(provinceDto);
        if(!validateProvince.checkProvince(provinceDto).equals(ResponseStatus.DONE)){
            Province province = provinceRepository.getOne(id);
            convertObject.convertDtoToEntityProvince(provinceDto, province);
           Province province1 =  provinceRepository.save(province);
            responseStatus = ResponseStatus.DONE;
        }
        return new Response<>(provinceDto,responseStatus);
    }

    @Override
    public Response<Boolean> deleteProvinceById(UUID id) {
        ResponseStatus responseStatus;
        if(id != null){
            provinceRepository.deleteById(id);
            responseStatus = ResponseStatus.DONE;
        }
        else {
            responseStatus = ResponseStatus.PROVINCE_IS_NULL;
        }
        return  new Response<>(null,responseStatus);
    }


}

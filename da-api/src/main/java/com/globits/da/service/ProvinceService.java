package com.globits.da.service;

import com.globits.da.dto.ProvinceDto;
import com.globits.da.validate.Response;
import com.globits.da.validate.ResponseStatus;

import java.util.UUID;

public interface ProvinceService {
    public Response<ProvinceDto> createNewProvince(ProvinceDto provinceDto);

    public Response<ProvinceDto> getProvince(UUID id);

    public Response<ProvinceDto> update(UUID id, ProvinceDto provinceDto);

    public Response<Boolean> deleteProvinceById(UUID id);

//    public void deleteAllProvince();

}

package com.globits.da.service;

import com.globits.da.dto.CommuneDto;
import com.globits.da.dto.DistrictDto;
import com.globits.da.validate.Response;



import java.util.List;
import java.util.UUID;

public interface DistrictService {
    public Response<DistrictDto> createNewDistrict(DistrictDto districtDto);
    public Response<DistrictDto> getDistrictById(UUID id);
    public Response<DistrictDto> updateDistrict(UUID id, DistrictDto districtDto);
    public Response<Boolean> deleteDistrictById(UUID id);
    public Response<List<CommuneDto>> getCommuneWithDistrictId(UUID id);
}

package com.globits.da.service;

import com.globits.da.dto.CommuneDto;
import com.globits.da.validate.Response;

import java.util.UUID;

public interface CommuneService {
    public Response<CommuneDto> createCommuneWithIdDistrict(CommuneDto communeDto);
    public Response<CommuneDto> getCommuneById(UUID id);
    public Response<CommuneDto> updateCommuneById(UUID id, CommuneDto communeDto);
    public Response<Boolean> deleteCommuneById(UUID id);
}

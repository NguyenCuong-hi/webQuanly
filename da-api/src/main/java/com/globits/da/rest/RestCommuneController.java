package com.globits.da.rest;


import com.globits.da.dto.CommuneDto;
import com.globits.da.service.CommuneService;
import com.globits.da.validate.Response;
import com.globits.da.validate.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/commune")
public class RestCommuneController {
    @Autowired
    CommuneService communeService;
    @PostMapping("/addNewCommune")
    public Response<CommuneDto> createNewCommune(@RequestBody CommuneDto communeDto){
       return communeService.createCommuneWithIdDistrict(communeDto);
    }
    @GetMapping("/{id}")
    public Response<CommuneDto> getCommuneById(@PathVariable(value = "id") UUID id){
        return communeService.getCommuneById(id);

    }
    @PutMapping("/updateCommune/{id}")
    public Response<CommuneDto> updateCommuneById(@PathVariable("id") UUID id,@RequestBody CommuneDto communeDto){
       return communeService.updateCommuneById(id,communeDto);

    }
    @DeleteMapping("/{id}")
    public Response<Boolean> deleteCommuneById(@PathVariable("id") UUID id){
      return   communeService.deleteCommuneById(id);
    }
}

package com.globits.da.rest;


import com.globits.da.dto.CommuneDto;
import com.globits.da.dto.DistrictDto;
import com.globits.da.service.DistrictService;
import com.globits.da.validate.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/district")
public class RestDistrictController {
    @Autowired
    private  DistrictService districtService;
    @PostMapping(value = "/addNewDistrict")
    public Response<DistrictDto> createNewDistrict(@RequestBody DistrictDto districtDto){
        return districtService.createNewDistrict(districtDto);

    }
    @GetMapping("/{id}")
    public Response<DistrictDto> getDistrictById(@PathVariable(value = "id") UUID idDistrict){
      return districtService.getDistrictById(idDistrict);

    }

    @PutMapping("/{id}")
    public Response<DistrictDto> updateDistrictById(@PathVariable("id") UUID idDistrict,@RequestBody DistrictDto districtDto){
        return districtService.updateDistrict(idDistrict,districtDto);
    }
    @DeleteMapping("/{id}")
    public Response<Boolean> deleteDistrictById(@PathVariable("id") UUID idDistrict){
        return  districtService.deleteDistrictById(idDistrict);
    }


    @GetMapping("/getCommuneWithDistrictID/{id}")
    public Response<List<CommuneDto>> getCommuneWithDistrictID(@PathVariable("id") UUID idDistrict ){
       return districtService.getCommuneWithDistrictId(idDistrict);
    }

    //    @DeleteMapping("/deleteAllDistrict")
//    public String deleteAllDistrict(){
//        districtService.deleteAllDistrict();
//        return "DeleteSuccess";
//    }

//    @PutMapping("/editDistrictAndListCommune/{id}")
//    public Response<DistrictDto> editDistrictAndListCommune (@PathVariable("id") UUID idDistrict, @RequestBody DistrictDto districtDto){
//        return  districtService.editDistrictAndListCommune(idDistrict,districtDto);
//
//    }
}

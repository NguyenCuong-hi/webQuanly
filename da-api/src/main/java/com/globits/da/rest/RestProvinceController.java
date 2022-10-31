package com.globits.da.rest;


import com.globits.da.domain.Province;
import com.globits.da.dto.DistrictDto;
import com.globits.da.dto.ProvinceDto;
import com.globits.da.service.impl.ProvinceServiceImpl;
import com.globits.da.validate.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/province")
public class RestProvinceController {
    @Autowired
    ProvinceServiceImpl provinceService;

    @PostMapping("/addNewProvince")
    public Response<ProvinceDto> create(@RequestBody ProvinceDto provinceDto) {
       return provinceService.createNewProvince(provinceDto);
    }

    @GetMapping(value = "{id}")
    public Response<ProvinceDto> getProvinceById(@PathVariable("id") UUID id) {
        return provinceService.getProvince(id);
    }

    @PutMapping(value = "/updateProvince/{id}")
    public Response<ProvinceDto> updateProvinceById(@PathVariable("id") UUID id, @RequestBody ProvinceDto provinceDto) {
        return provinceService.update(id, provinceDto);

    }

    @DeleteMapping("/{id}")
    public Response<Boolean> deleteProvinceById(@PathVariable("id") UUID id) {
        return provinceService.deleteProvinceById(id);

    }

    @GetMapping("/district/{id}")
    public Response<List<DistrictDto>> getDistrictByProvinceId(@PathVariable(value = "id") UUID id) {
        return provinceService.getDistrictByProvinceId(id);
    }

//    @DeleteMapping("/deleteAllProvince")
//    public String deleteAllProvince(Object object) {
//        provinceService.deleteAllProvince();
//        return "Delete Success";
//    }


}

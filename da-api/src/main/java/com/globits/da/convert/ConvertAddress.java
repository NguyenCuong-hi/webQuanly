package com.globits.da.convert;


import com.globits.da.domain.Certificate;
import com.globits.da.domain.Commune;
import com.globits.da.domain.District;
import com.globits.da.domain.Province;
import com.globits.da.dto.CertificateDto;
import com.globits.da.dto.CommuneDto;
import com.globits.da.dto.DistrictDto;
import com.globits.da.dto.ProvinceDto;
import com.globits.da.repository.CommuneRepository;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ConvertAddress {
    private static ProvinceRepository provinceRepository;
    private static DistrictRepository districtRepository;
    private static CommuneRepository communeRepository;

    @Autowired
    public ConvertAddress(ProvinceRepository provinceRepository, DistrictRepository districtRepository, CommuneRepository communeRepository) {
        ConvertAddress.provinceRepository = provinceRepository;
        ConvertAddress.districtRepository = districtRepository;
        ConvertAddress.communeRepository = communeRepository;
    }

//    public static List<District> convertListDistrictDtoToEntity(ProvinceDto provinceDto, Province province) {
//        List<DistrictDto> districtDtoList = provinceDto.getDistrictDtoList();
//        List<District> districtList = new ArrayList<>();
//        if (districtDtoList != null) {
//            for (DistrictDto districtDto : districtDtoList) {
//                District district = new District(districtDto, province);
//                districtList.add(district);
//            }
//        }
//        return districtList;
//    }


//    public static List<District> convertListDistrictDtoToEntityUpdate(ProvinceDto provinceDto) {
//        List<DistrictDto> districtDtoList = provinceDto.getDistrictDtoList();
//        List<District> districtList = new ArrayList<>();
//        if (districtDtoList != null) {
//            for (DistrictDto districtDto : districtDtoList) {
//                if (districtDto.getId() != null) {
//                    UUID districtId = districtDto.getId();
//                    District district = districtRepository.getDistrictById(districtId);
//                    district.setDistrict(districtDto);
//                    districtList.add(district);
//                }
//            }
//
//        }
//        return districtList;
//    }

    public static List<Commune> convertListCommuneDtoToEntity(DistrictDto districtDto, District district) {
        List<CommuneDto> communeDtoList = districtDto.getCommuneDtoList();
        List<Commune> communeList = new ArrayList<>();
        for (CommuneDto communeDto : communeDtoList) {
            communeList.add(new Commune(communeDto, district));
        }
        return communeList;
    }

    public static List<Commune> convertListCommuneDtoToEntityForUpdate(DistrictDto districtDto, District district) {
        List<CommuneDto> communeDtoList = districtDto.getCommuneDtoList();
        List<Commune> communeList = new ArrayList<>();
        for (CommuneDto communeDto : communeDtoList) {
            Commune commune = communeRepository.getCommuneById(communeDto.getId());
            commune.setCommune(communeDto);
            communeList.add(commune);
        }
        return communeList;
    }

}
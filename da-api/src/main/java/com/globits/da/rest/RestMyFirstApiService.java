package com.globits.da.rest;


import com.globits.da.dto.UserDto;
import com.globits.da.service.MyFirstApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;


@RestController
@RequestMapping("/api/myFirstApi")
public class RestMyFirstApiService {
    //#4
    @GetMapping("/firstApi")
    public String getFirstApi() {
        return "MyFirstAPI";
    }

    //#5
    @Autowired
    MyFirstApiService myFirstApiService;

    @GetMapping("/getMyFirstApiService")
    public String getMyFirstApiService() {
        return myFirstApiService.getMyFirstApiService();
    }

    //#6
    @PostMapping("/postUserApiService")
    public UserDto setMyFirstApiService(@RequestBody UserDto userDto) {
        userDto.getCode();
        userDto.getName();
        userDto.getAge();
        System.out.println(userDto);
        return userDto;
    }

    //#9
    @PostMapping("/postTestParam")
    public int postTestParam(@RequestParam(value = "param") Integer param) {
        return param;
    }

    //#10
    @PostMapping("/postAgeUserService/{age}")
    public int postAgeUserService(@PathVariable("age") int age) {
        System.out.println("Test PathVariable : " + age);
        return age;
    }

    // #11
    public String postTestNoAnnotation() {
        return "Success Test No Annotation !";
    }
    //#12 Test Upload file

    @PostMapping("/upload/{fileName}")
    public ResponseEntity<?> handleFileUpload(@PathVariable(value = "fileName") String fileName ,@RequestParam("file") MultipartFile file ) throws IOException {

        myFirstApiService.uploadAndHandleFile(file,fileName);
        return ResponseEntity.ok("File uploaded successfully.");
    }


    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test(@RequestParam("f") MultipartFile file){
        return "OK";
    }
}

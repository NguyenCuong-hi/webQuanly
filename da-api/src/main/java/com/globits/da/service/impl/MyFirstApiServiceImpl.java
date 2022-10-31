package com.globits.da.service.impl;

import com.globits.da.service.MyFirstApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


@Service
public class MyFirstApiServiceImpl implements MyFirstApiService {
    @Autowired
    MyFirstApiService myFirstApiService;

    @Override
    public String getMyFirstApiService() {
        return "MyFirstApiService";
    }

    public File convertToFile(MultipartFile multipart, String fileName) throws IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
        multipart.transferTo(convFile);
        return convFile;
    }

    @Override
    public void uploadAndHandleFile(MultipartFile multipart, String fileName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(convertToFile(multipart, fileName));
        System.out.println(fileInputStream.read());

    }
}
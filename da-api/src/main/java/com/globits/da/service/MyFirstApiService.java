package com.globits.da.service;


import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


public interface MyFirstApiService {
    public String getMyFirstApiService();


    public void uploadAndHandleFile(MultipartFile multipart, String fileName) throws IOException;


}

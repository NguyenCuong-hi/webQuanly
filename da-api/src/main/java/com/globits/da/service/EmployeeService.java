package com.globits.da.service;

import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeDto;
import com.globits.da.validate.Response;
import com.globits.da.validate.ResponseStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    public Response<EmployeeDto> deleteEmployeeById(UUID employeeID);
    public Response<EmployeeDto> createEmployee(EmployeeDto employee);
    public Response<EmployeeDto> getEmployeeById(UUID id);
    public Response<EmployeeDto> updateEmployeeById(UUID id, EmployeeDto employeeDto);
    public Response<Object> exportFileExcel(HttpServletResponse httpServletResponse);
    public List<EmployeeDto> importFileExcel(MultipartFile multipartFile);
    public Response<Object> insertEmployeeFromExcelToDatabase(MultipartFile multipartFile);
}

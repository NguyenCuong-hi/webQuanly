package com.globits.da.rest;

import com.globits.da.Constants;
import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeDto;
import com.globits.da.service.EmployeeService;
import com.globits.da.validate.Response;
import com.globits.da.validate.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

@RestController
@RequestMapping("api/RestEmployeeController")
public class RestEmployeeController {
    @Autowired
    EmployeeService employeeService;

    //    @PostMapping("/addEmployee")
//    public ResponseEntity<List<Employee>> addEmployee(@RequestBody List<EmployeeDto> employeeDtoList) {
//        employeeDtoList.forEach(new Consumer<EmployeeDto>() {
//            @Override
//            public void accept(EmployeeDto employeeDto) {
//                employeeService.createEmployee(employeeDto);
//            }
//        });
//        return new ResponseEntity<List<Employee>>();
//
//    }
    @PostMapping("/createEmployee")
    public Response<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeService.createEmployee(employeeDto);
    }


    @DeleteMapping(value = "/{id}")
    public Response<EmployeeDto> deleteEmployee(@PathVariable("id") UUID employeeId) {
        return employeeService.deleteEmployeeById(employeeId);
    }


    @GetMapping(value = "{id}")
    public Response<EmployeeDto> getEmployee(@PathVariable("id") UUID employeeId) {

        return employeeService.getEmployeeById(employeeId);
    }

    @PutMapping(value = "updateEmployee/{id}")
    public Response<EmployeeDto> updateEmployee(@PathVariable("id") UUID employeeId, @RequestBody EmployeeDto employeeDto) {
        return employeeService.updateEmployeeById(employeeId, employeeDto);
    }

    @GetMapping("/exportExcel")
    public Response<Object> exportExcel(HttpServletResponse httpServletResponse) {
        httpServletResponse.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=employee" + ".xlsx";
        httpServletResponse.setHeader(headerKey, headerValue);
        return employeeService.exportFileExcel(httpServletResponse);

    }

    @PostMapping("/importDatabase")
    public Response<Object> importDatabase(@RequestParam("file") MultipartFile multipartFile) {
        return employeeService.insertEmployeeFromExcelToDatabase(multipartFile);

    }

}

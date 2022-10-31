package com.globits.da.service.impl;


import com.globits.da.convert.ConvertObject;
import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeDto;
import com.globits.da.repository.EmployeeRepository;
import com.globits.da.service.EmployeeService;
import com.globits.da.validate.Response;
import com.globits.da.validate.ResponseStatus;
import com.globits.da.validate.ValidateEmployee;
import com.globits.da.xcel.EmployeeExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ValidateEmployee validateEmployee;

    @Autowired
    private ConvertObject convertObject;


    @Override
    public Response<EmployeeDto> createEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        ResponseStatus statusCode = validateEmployee.checkEmployee(null,employeeDto);
        if(!statusCode.equals(ResponseStatus.DONE))
        {
            return new Response<>(null,statusCode);
        }
        convertObject.convertDtoToEntityEmployee(employeeDto,employee);
        employeeRepository.save(employee);
        statusCode = ResponseStatus.DONE;
       return  new Response<>(employeeDto, statusCode);
    }

    @Override
    public Response<EmployeeDto> deleteEmployeeById(UUID employeeID) {
        if(employeeID == null){
            return new Response<>(null,ResponseStatus.EMPLOYEE_IS_NULL);
        }
       employeeRepository.deleteById(employeeID);
        return new Response<>(null, ResponseStatus.DONE);
    }

    @Override
    public Response<EmployeeDto> updateEmployeeById(UUID id, EmployeeDto employeeDto) {
        ResponseStatus responseStatus = validateEmployee.checkEmployee(id,employeeDto);
        if(!responseStatus.equals(ResponseStatus.DONE))
        {
            return new Response<>(null,responseStatus);
        }
        Employee employeeUpdate = employeeRepository.getEmployeeById(id);
        employeeUpdate.setData(employeeDto);
        employeeRepository.save(employeeUpdate);
        return new Response<>(employeeDto, responseStatus);
    }

    @Override
    public Response<EmployeeDto> getEmployeeById(UUID employeeId) {
        ResponseStatus responseStatus = validateEmployee.checkEmployee(employeeId,null);
        if(employeeId == null){
            return new Response<>(null,responseStatus);
        }
        EmployeeDto employeeDto = new EmployeeDto();
        Employee employee = employeeRepository.getEmployeeById(employeeId);
       convertObject.convertEntityToDtoEmployee(employeeDto,employee);
       return new Response<>(employeeDto,responseStatus);
    }



    @Override
    public Response<Object> exportFileExcel(HttpServletResponse httpServletResponse) {
        final List<Employee> ListEmployee = employeeRepository.findAll();
        ListEmployee.sort(Comparator.comparing(Employee::getId));
        try
        {
            EmployeeExcel.writeFileExcel(ListEmployee,httpServletResponse);
        }catch (IOException ioException){
            return new Response<>(ioException.getMessage(),null);
        }
        return new Response<>(null,ResponseStatus.DONE);
    }

    @Override
    public List<EmployeeDto> importFileExcel(MultipartFile multipartFile){
        try {
            return EmployeeExcel.readFileExcel(multipartFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Response<Object> insertEmployeeFromExcelToDatabase(MultipartFile multipartFile){
        try {
            List<EmployeeDto> employeeList = EmployeeExcel.readFileExcel(multipartFile);
           for(EmployeeDto employeeDto : employeeList){
               validateEmployee.checkEmployee(null,employeeDto);
               Employee employee = new Employee();
               convertObject.convertDtoToEntityEmployee(employeeDto,employee);
               employeeRepository.save(employee);
           }
        } catch (IOException e) {
           e.getMessage();
        }
        return new Response<>(null,ResponseStatus.DONE);
    }
}

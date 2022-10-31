package com.globits.da.xcel;

import com.globits.da.Constants;
import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeDto;
import com.globits.da.validate.ResponseStatus;
import com.globits.da.validate.ValidateEmployee;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Component
public class EmployeeExcel {
    @Autowired
    static
    ValidateEmployee validateEmployee;
    private static void createCell(XSSFSheet sheet, Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(style);
    }


    public static void writeHeader(XSSFWorkbook workbook, XSSFSheet sheet) {

        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(sheet, row, Constants.COLUMN_INDEX_ID, "ID", style);
        createCell(sheet, row, Constants.COLUMN_INDEX_NAME, "Name", style);
        createCell(sheet, row, Constants.COLUMN_INDEX_AGE, "Age", style);
        createCell(sheet, row, Constants.COLUMN_INDEX_CODE, "Code", style);
        createCell(sheet, row, Constants.COLUMN_INDEX_EMAIL, "Email", style);
        createCell(sheet, row, Constants.COLUMN_INDEX_PHONE, "Phone", style);
        createCell(sheet, row, Constants.COLUMN_INDEX_PROVINCE, "ProvinceID", style);
        createCell(sheet, row, Constants.COLUMN_INDEX_DISTRICT, "DistrictID", style);
        createCell(sheet, row, Constants.COLUMN_INDEX_COMMUNE, "CommuneID", style);

    }

    public static void writeEmployee(Employee employee, Row row) {
        Cell cell = row.createCell(Constants.COLUMN_INDEX_ID);
        cell.setCellValue(String.valueOf(employee.getId()));

        cell = row.createCell(Constants.COLUMN_INDEX_AGE);
        cell.setCellValue(employee.getAge());

        cell = row.createCell(Constants.COLUMN_INDEX_CODE);
        cell.setCellValue(employee.getCode());

        cell = row.createCell(Constants.COLUMN_INDEX_EMAIL);
        cell.setCellValue(employee.getEmail());

        cell = row.createCell(Constants.COLUMN_INDEX_NAME);
        cell.setCellValue(employee.getName());

        cell = row.createCell(Constants.COLUMN_INDEX_PHONE);
        cell.setCellValue(employee.getPhone());

        cell = row.createCell(Constants.COLUMN_INDEX_PROVINCE);
        cell.setCellValue(String.valueOf((employee.getProvince().getId())));

        cell = row.createCell(Constants.COLUMN_INDEX_DISTRICT);
        cell.setCellValue(String.valueOf(employee.getDistrict().getId()));

        cell = row.createCell(Constants.COLUMN_INDEX_COMMUNE);
        cell.setCellValue(String.valueOf(employee.getCommune().getId()));
    }

    private static void autoSizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }

    public static void createOutFile(HttpServletResponse httpServletResponse, XSSFWorkbook workbook) {
        try {
            ServletOutputStream outputFile = httpServletResponse.getOutputStream();
            workbook.write(outputFile);
            workbook.close();
            outputFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeFileExcel(List<Employee> employeeList, HttpServletResponse httpServletResponse) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Employee");
        int rowIndex = 0;
        writeHeader(workbook, sheet);
        rowIndex++;
        for (Employee employee : employeeList) {
            Row row = sheet.createRow(rowIndex);
            writeEmployee(employee, row);
            rowIndex++;
        }
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autoSizeColumn(sheet, numberOfColumn);
        createOutFile(httpServletResponse, workbook);
    }

    // Read from excel


    // get value of cell
    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        Object cellValue = null;
        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = formulaEvaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            default:
                break;
        }
        return cellValue;
    }

    // read from excel
    public static List<EmployeeDto> readFileExcel(MultipartFile filePath) throws IOException {

        List<EmployeeDto> employeeList = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(filePath.getInputStream());
        Sheet sheet = workbook.getSheet("Employee");
        Iterator<Row> rowIterator = sheet.iterator();
        String error;
        int columIndex=0;
        ResponseStatus responseStatus = null;
        try {


            while (rowIterator.hasNext()) {
                EmployeeDto employeeDto = new EmployeeDto();
                Row nextRow = rowIterator.next();
                if (nextRow.getRowNum() == 0) {
                    continue;
                }
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    Object cellValue = getCellValue(cell);
                    if (cellValue == null || cellValue.toString().isEmpty()) {
                        continue;
                    }
                     columIndex = cell.getColumnIndex();
                    switch (columIndex) {
                        case Constants.COLUMN_INDEX_ID:
                            employeeDto.setId(UUID.fromString((String) cellValue));
                            responseStatus = validateEmployee.checkEmployee(null,employeeDto);
                            break;
                        case Constants.COLUMN_INDEX_AGE:
                            employeeDto.setAge((new BigDecimal((double) cellValue)).intValue());
                            responseStatus = validateEmployee.checkEmployee(null,employeeDto);
                            break;
                        case Constants.COLUMN_INDEX_CODE:
                            employeeDto.setCode((String) cellValue);
                            responseStatus = validateEmployee.checkEmployee(null,employeeDto);
                            break;
                        case Constants.COLUMN_INDEX_EMAIL:
                            employeeDto.setEmail((String) cellValue);
                            responseStatus = validateEmployee.checkEmployee(null,employeeDto);
                            break;
                        case Constants.COLUMN_INDEX_NAME:
                            employeeDto.setName((String) cellValue);
                            responseStatus = validateEmployee.checkEmployee(null,employeeDto);
                            break;
                        case Constants.COLUMN_INDEX_PHONE:
                            employeeDto.setPhone((String) cellValue);
                            responseStatus = validateEmployee.checkEmployee(null,employeeDto);
                            break;
                        case Constants.COLUMN_INDEX_PROVINCE:
                            employeeDto.setProvinceId(UUID.fromString((String) cellValue));
                            responseStatus = validateEmployee.checkEmployee(null,employeeDto);
                            break;
                        case Constants.COLUMN_INDEX_DISTRICT:
                            employeeDto.setDistrictId(UUID.fromString((String) cellValue));
                            responseStatus = validateEmployee.checkEmployee(null,employeeDto);
                            break;
                        case Constants.COLUMN_INDEX_COMMUNE:
                            employeeDto.setCommuneId(UUID.fromString((String) cellValue));
                            responseStatus = validateEmployee.checkEmployee(null,employeeDto);
                            break;
                        default:
                            break;
                    }
                }
                employeeList.add(employeeDto);

            }
        }
        catch (Exception exception){
            error = "Line"+ rowIterator + " Collumn" + columIndex + ": " +responseStatus;
        }
        workbook.close();
        return employeeList;
    }

}

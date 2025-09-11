package com.employeeMS.employeeMS.controller;

import com.employeeMS.employeeMS.service.employeeService;
import com.employeeMS.employeeMS.dto.ResponseDto;
import com.employeeMS.employeeMS.util.varList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.employeeMS.employeeMS.dto.employeeDto;

import javax.swing.text.html.parser.Entity;
import java.security.PublicKey;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/employee")
public class employeeController {
    @Autowired
    private employeeService empService;
    @PostMapping(value = "/saveEmployee")
    public ResponseEntity<ResponseDto> saveEmployee(@RequestBody employeeDto empDto){
        ResponseDto responseDto = new ResponseDto();
        try {
            String response = empService.saveEmployee(empDto);
            if(response.equals("00")){
                responseDto.setCode(varList.RSP_SUCCESS);
                responseDto.setMessage("Success!");
                responseDto.setContent(empDto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);

            }else if (response.equals("06")){
                responseDto.setCode(varList.RSP_DUPLICATED);
                responseDto.setMessage("User already exist!");
                responseDto.setContent(empDto);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }else {
                responseDto.setCode(varList.RSP_FAIL);
                responseDto.setMessage("Error in saving!");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);

            }
        }catch (Exception ex){
            responseDto.setCode(varList.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @PutMapping(value = "/updateEmployee")
    public ResponseEntity<ResponseDto> updateUser(@RequestBody employeeDto empDto){
        ResponseDto responseDto = new ResponseDto();
        try {
            String response = empService.updateEmployee(empDto);
            if (response.equals("00")){
                responseDto.setCode(varList.RSP_SUCCESS);
                responseDto.setMessage("Employee Updated Successfully!");
                responseDto.setContent(empDto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            } else if (response.equals("01")) {
                responseDto.setCode(varList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("The employee not exist!");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.NOT_FOUND);
            }else {
                responseDto.setCode(varList.RSP_FAIL);
                responseDto.setMessage("Error in updating!");
                responseDto.setContent(null);
                System.out.println(response);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            responseDto.setCode(varList.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getAllEmployees")
    public ResponseEntity getAllEmployees(){
        ResponseDto responseDto = new ResponseDto();
        try {
            List<employeeDto> empDtoList = empService.getAllEmployees();
            responseDto.setCode(varList.RSP_SUCCESS);
            responseDto.setMessage("List of all employees:");
            responseDto.setContent(empDtoList);
            return new ResponseEntity(responseDto,HttpStatus.ACCEPTED);
        }catch (Exception ex){
            responseDto.setCode(varList.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchEmployeeById/{empId}")
    public ResponseEntity searchByEmpId(@PathVariable int empId){
        ResponseDto responseDto = new ResponseDto();
        try {
            employeeDto empDto = empService.searchEmployeeById(empId);
            if(empDto!=null) {
                responseDto.setCode(varList.RSP_SUCCESS);
                responseDto.setMessage("Search Resullts: ");
                responseDto.setContent(empDto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            }else {
                responseDto.setCode(varList.RSP_FAIL);
                responseDto.setMessage("Not Found!");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex){
            responseDto.setCode(varList.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping(value = "/deleteEmployee/{empId}")
    public ResponseEntity deleteEmployee(@PathVariable int empId){
        ResponseDto responseDto =new ResponseDto();
        try {
            String response = empService.deleteEmployee(empId);
            if (response.equals("00")){
                responseDto.setCode(varList.RSP_SUCCESS);
                responseDto.setMessage("employee deleted successfully!");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto,HttpStatus.ACCEPTED);
            } else if (response.equals("01")) {
                responseDto.setCode(varList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("No employee exist with this ID!");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto,HttpStatus.NOT_FOUND);
            }else {
                responseDto.setCode(varList.RSP_ERROR);
                responseDto.setMessage("Error in deleting process!");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            responseDto.setCode(varList.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}

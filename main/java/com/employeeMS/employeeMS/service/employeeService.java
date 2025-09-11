package com.employeeMS.employeeMS.service;

import com.employeeMS.employeeMS.dto.employeeDto;
import com.employeeMS.employeeMS.entity.employee;
import com.employeeMS.employeeMS.repository.employeeRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import com.employeeMS.employeeMS.util.varList;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class employeeService {
    @Autowired
    private employeeRepo empRepo;
    @Autowired
    private ModelMapper modelMapper;

    public String saveEmployee(employeeDto dto){
        if(empRepo.existsById(dto.getEmpID())){
            return varList.RSP_DUPLICATED;
        }else {
            empRepo.save(modelMapper.map(dto, employee.class));
            return varList.RSP_SUCCESS;
        }
    }
    public String updateEmployee(employeeDto dto){
        if (empRepo.existsById(dto.getEmpID())){
            empRepo.save(modelMapper.map(dto, employee.class));
            return varList.RSP_SUCCESS;
        }else {
            return varList.RSP_NO_DATA_FOUND;
        }
    }
    public List<employeeDto> getAllEmployees(){
        List<employee> empList = empRepo.findAll();
        return modelMapper.map(empList, new TypeToken<ArrayList<employeeDto>>(){
        }.getType());
    }
    public employeeDto searchEmployeeById(int empId){
        if(empRepo.existsById(empId)){
            employee emp = empRepo.findById(empId).orElse(null);
            return modelMapper.map(emp, employeeDto.class);
        }else {
            return null;
        }
    }
    public String deleteEmployee(int empId){
        if (empRepo.existsById(empId)){
            empRepo.deleteById(empId);
            return varList.RSP_SUCCESS;
        }else {
            return varList.RSP_NO_DATA_FOUND;
        }
    }

}

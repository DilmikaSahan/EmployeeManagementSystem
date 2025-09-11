package com.employeeMS.employeeMS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDto {
    private String code;
    private String message;
    private Object content;
}

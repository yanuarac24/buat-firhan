package com.yanuar.exercise.dto;

import lombok.Data;

@Data
public class DetailEmployeeDto {
    private Long id;
    private String name;
    private String address;
    private int age;
    private String fax;
}

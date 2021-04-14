package com.yanuar.exercise.controller;

import com.yanuar.exercise.dto.CreateEmployeeDto;
import com.yanuar.exercise.dto.DetailEmployeeDto;
import com.yanuar.exercise.dto.ListEmployeeDto;
import com.yanuar.exercise.dto.UpdateEmployeeDto;
import com.yanuar.exercise.exception.ResourceNotFoundException;
import com.yanuar.exercise.model.Employee;
import com.yanuar.exercise.repository.EmployeeRepository;
import com.yanuar.exercise.util.ModelMapperUtil;
import org.hibernate.sql.Update;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapperUtil mapperUtil;

    @GetMapping("/employees")
    public List<ListEmployeeDto> getPageEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        List<ListEmployeeDto> employeeDtos = mapperEmployeeToDto(employees);
        return employeeDtos;
    }

    @PostMapping("/employees")
    public ResponseEntity<Object> createNewEmployee(@RequestBody CreateEmployeeDto createEmployeeDto) {
        Employee employee = mapperCreateEmployeeToEntity(createEmployeeDto);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Object> getSingleEmployee(@PathVariable Long id) {
        Optional<Employee> employee = Optional.ofNullable(employeeRepository.findById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Employee Null"));
        DetailEmployeeDto detailEmployeeDto = mapperEmployeeDetailDto(employee);
        return new ResponseEntity<>(detailEmployeeDto, HttpStatus.OK);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable Long id,
                                                 @RequestBody UpdateEmployeeDto updateEmployeeDto) {
        Employee employee = mapperUpdateEmployeeToEntity(id, updateEmployeeDto);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        employeeRepository.delete(employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    private List<ListEmployeeDto> mapperEmployeeToDto(List<Employee> employees) {
        List<ListEmployeeDto> employeeDtos = new ArrayList<>();
        for (Employee data : employees) {
            ListEmployeeDto listEmployeeDto = mapperUtil.modelMapperUtil().map(data, ListEmployeeDto.class);
            listEmployeeDto.setId(data.getId());
            listEmployeeDto.setName(data.getName());
            listEmployeeDto.setAge(data.getAge());
            employeeDtos.add(listEmployeeDto);
        }
        return employeeDtos;
    }

    private DetailEmployeeDto mapperEmployeeDetailDto(Optional<Employee> employee) {
        DetailEmployeeDto detailEmployeeDto = mapperUtil.modelMapperUtil().map(employee, DetailEmployeeDto.class);
        employee.ifPresent(value -> {
            detailEmployeeDto.setId(value.getId());
            detailEmployeeDto.setName(value.getName());
            detailEmployeeDto.setAge(value.getAge());
            detailEmployeeDto.setAddress(value.getAddress());
            detailEmployeeDto.setFax(value.getFax());
        });

        return detailEmployeeDto;
    }

    private Employee mapperCreateEmployeeToEntity(CreateEmployeeDto createEmployeeDto) {
        Employee employee = mapperUtil.modelMapperUtil().map(createEmployeeDto, Employee.class);
        employee.setName(createEmployeeDto.getName());
        employee.setAge(createEmployeeDto.getAge());
        employee.setAddress(createEmployeeDto.getAddress());
        employee.setFax(createEmployeeDto.getFax());

        return employeeRepository.save(employee);
    }

    private Employee mapperUpdateEmployeeToEntity(Long id, UpdateEmployeeDto updateEmployeeDto) {
        Employee employeeById = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        Employee employee = mapperUtil.modelMapperUtil().map(updateEmployeeDto, Employee.class);
        employee.setName(updateEmployeeDto.getName());
        employee.setAge(updateEmployeeDto.getAge());
        employee.setAddress(updateEmployeeDto.getAddress());
        employee.setFax(updateEmployeeDto.getFax());

        employeeById.setName(employee.getName());
        employeeById.setAge(employee.getAge());
        employeeById.setAddress(employee.getAddress());
        employeeById.setFax(employee.getFax());

        return employeeRepository.save(employeeById);
    }
}

package com.yanuar.exercise.repository;

import com.yanuar.exercise.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // native, nama tabel pakai nama tabel
    @Query(value = "SELECT * FROM employees", nativeQuery = true)
    List<Employee> findAllEmployees();

    // jpql, nama tabel pakai nama class
    @Query(value = "SELECT e FROM Employee e WHERE e.name = ?1")
    Employee findEmployeeName(String name);
}

package helderklemp.service;

import helderklemp.model.Employee;

import java.util.Collection;

/**
 * Created by helderklemp on 16/7/17.
 */
public interface EmployeeService {

    Collection<Employee> findEmployeeByManager(Long managerId);

    void addEmployee(Employee emp);

    String employeeReport();
}

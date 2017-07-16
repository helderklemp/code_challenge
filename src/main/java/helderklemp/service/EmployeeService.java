package helderklemp.service;

import helderklemp.service.model.Employee;

import java.util.Collection;
import java.util.List;

/**
 * Created by helderklemp on 16/7/17.
 */
public interface EmployeeService {

    Collection<Employee> findEmployeeByManager(Long managerId);

    void addEmployee(Employee emp);
}

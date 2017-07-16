package helderklemp.service;

import helderklemp.service.EmployeeService;
import helderklemp.service.model.Employee;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by helderklemp on 16/7/17.
 */
public class EmployeeServiceImpl implements EmployeeService {

    private Collection<Employee> employees;

    public EmployeeServiceImpl(){
        employees=new LinkedHashSet<>();
    }

    @Override
    public Collection<Employee> findEmployeeByManager(Long managerId) {
        return employees;
    }

    @Override
    public void addEmployee(Employee emp) {
        employees.add(emp);
    }
}

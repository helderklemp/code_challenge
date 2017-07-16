package helderklemp.service;

import helderklemp.service.model.Employee;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

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
        if(managerId ==null){
            return employees.stream().filter(emp -> emp.getManager() ==null).collect(Collectors.toSet());
        }else{
            return employees.stream().filter(emp ->
                    (emp.getManager()!=null && emp.getManager().getId().equals(managerId))).collect(Collectors.toSet());
        }
    }

    @Override
    public void addEmployee(Employee emp) {
        employees.add(emp);
    }
}

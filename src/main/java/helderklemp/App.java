package helderklemp;

import helderklemp.model.Employee;
import helderklemp.service.ConsoleReportServiceImpl;
import helderklemp.service.EmployeeService;
import helderklemp.service.EmployeeServiceImpl;
import helderklemp.service.ReportService;

/**
 * Created by helderklemp on 16/7/17.
 */
public class App {

    public static void main(String args[]){
        EmployeeService employeeService=new EmployeeServiceImpl();;
        ReportService reportService=new ConsoleReportServiceImpl(employeeService);;

        loadData(employeeService);

        reportService.processReport();


    }
    private static void loadData(EmployeeService employeeService) {
        Employee ceo = new Employee();
        ceo.setId(150l);
        ceo.setName("Jamie");
        employeeService.addEmployee(ceo);
        Employee alan = new Employee();
        alan.setId(100l);
        alan.setName("Alan");
        alan.setManager(ceo);
        employeeService.addEmployee(alan);

        Employee steve = new Employee();
        steve.setId(400l);
        steve.setName("Steve");
        steve.setManager(ceo);
        employeeService.addEmployee(steve);

        Employee matin = new Employee();
        matin.setId(220l);
        matin.setName("Matin");
        matin.setManager(alan);
        employeeService.addEmployee(matin);

        Employee alex = new Employee();
        alex.setId(275l);
        alex.setName("Alex");
        alex.setManager(alan);
        employeeService.addEmployee(alex);

        Employee david = new Employee();
        david.setId(190l);
        david.setName("David");
        david.setManager(steve);
        employeeService.addEmployee(david);
    }
}

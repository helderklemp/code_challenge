package helderklemp.service;

import helderklemp.model.Employee;

import java.util.Collection;

/**
 * Created by helderklemp on 16/7/17.
 */
public class ConsoleReportServiceImpl implements ReportService{
    private EmployeeService employService;
    private final String DEFAULT_IDENT="      ";

    public ConsoleReportServiceImpl(EmployeeService service){
        employService=service;
    }

    @Override
    public void processReport() {
        System.out.println(employeeReport());
    }
    public String employeeReport() {
        StringBuffer report=new StringBuffer("List of Company's Employees, the data below is represented in hierarchical format :\n");
        report.append(processEmployeeRow(employService.findEmployeeByManager(null),0));
        return report.toString();
    }

    private String processEmployeeRow(Collection<Employee> employees, int depth) {
        StringBuilder row=new StringBuilder("");
        for (Employee emp :employees) {
            row.append(printSpacesByDepth(depth));
            row.append(emp);
            row.append("\n");
            row.append(processEmployeeRow(employService.findEmployeeByManager(emp.getId()),depth+1));
        }
        return row.toString();
    }

    private String printSpacesByDepth(int depth) {
        StringBuilder result=new StringBuilder("");
        for (int i = 0; i < depth; i++) {
            result.append(DEFAULT_IDENT);
        }
        return result.toString();
    }
}

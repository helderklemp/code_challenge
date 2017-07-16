import helderklemp.service.ConsoleReportServiceImpl;
import helderklemp.service.EmployeeService;
import helderklemp.service.EmployeeServiceImpl;
import helderklemp.model.Employee;
import helderklemp.service.ReportService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

/**
 * Created by helderklemp on 16/7/17.
 */
public class ListEmployeeTest {

    EmployeeService employeeService;

    ReportService reportService;
    @Before
    public void setUp(){
        employeeService =new EmployeeServiceImpl();
        reportService=new ConsoleReportServiceImpl(employeeService);
    }

    @Test
    public void ListCeoTest(){
        Assert.assertNotNull("not Null result", employeeService.findEmployeeByManager(null));
        loadData();
        Collection<Employee> result = employeeService.findEmployeeByManager(null);
        Assert.assertEquals("Only one CEO",result.size(),1);
        Employee ceo =result .iterator().next();
        Assert.assertEquals("Retrieve the CEO",ceo.getName(),"Jamie");
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidCEOTest() throws Exception {
        loadData();
        Employee ceo2 = new Employee();
        ceo2.setId(750l);
        ceo2.setName("Another CEO");
        employeeService.addEmployee(ceo2);
        Assert.fail();
    }
    @Test(expected = IllegalArgumentException.class)
    public void invalidEmployeeTest() throws Exception {
        Employee incorrectEmp = new Employee();
        incorrectEmp.setId(null);
        incorrectEmp.setName(null);
        employeeService.addEmployee(incorrectEmp);
        Assert.fail();
    }
    @Test(expected = IllegalArgumentException.class)
    public void invalidManagerTest() throws Exception {
        loadData();
        Employee invalidManager = new Employee();
        invalidManager.setId(750l);
        invalidManager.setName("invalid Manager");
        Employee invalidEmployee = new Employee();
        invalidEmployee.setId(1l);
        invalidEmployee.setName("Invalid record");
        invalidEmployee.setManager(invalidManager);
        employeeService.addEmployee(invalidEmployee);
        Assert.fail();
    }
    @Test
    public void simpleHierarchyTest() throws Exception {
        loadData();
        Assert.assertEquals("Second Level Hierarchy, CEO direct employees", employeeService.findEmployeeByManager(150l).size(),2);
        Assert.assertEquals("Third Level Hierarchy ", employeeService.findEmployeeByManager(100l).size(),2);
        Assert.assertEquals("Third Level Hierarchy ", employeeService.findEmployeeByManager(400l).size(),1);
    }
    @Test
    public void testReport() throws Exception {
        loadData();
        reportService.processReport();
    }


    private void loadData() {
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
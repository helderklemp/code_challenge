import helderklemp.service.EmployeeService;
import helderklemp.service.EmployeeServiceImpl;
import helderklemp.model.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

/**
 * Created by helderklemp on 16/7/17.
 */
public class ListEmployeeTest {

    EmployeeService service;
    @Before
    public void setUp(){
        service=new EmployeeServiceImpl();
    }

    @Test
    public void ListCeoTest(){
        Assert.assertNotNull("not Null result",service.findEmployeeByManager(null));
        loadData();
        Collection<Employee> result =service.findEmployeeByManager(null);
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
        service.addEmployee(ceo2);
        Assert.fail();
    }
    @Test(expected = IllegalArgumentException.class)
    public void invalidEmployeeTest() throws Exception {
        Employee incorrectEmp = new Employee();
        incorrectEmp.setId(null);
        incorrectEmp.setName(null);
        service.addEmployee(incorrectEmp);
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
        service.addEmployee(invalidEmployee);
        Assert.fail();
    }
    @Test
    public void simpleHierarchyTest() throws Exception {
        loadData();
        Assert.assertEquals("Second Level Hierarchy, CEO direct employees",service.findEmployeeByManager(150l).size(),2);
        Assert.assertEquals("Third Level Hierarchy ",service.findEmployeeByManager(100l).size(),2);
        Assert.assertEquals("Third Level Hierarchy ",service.findEmployeeByManager(400l).size(),1);
    }
    @Test
    public void testReport() throws Exception {
        loadData();
        String report =service.employeeReport();
        System.out.println(report);
        Assert.assertNotNull(report);
    }


    private void loadData() {
        Employee ceo = new Employee();
        ceo.setId(150l);
        ceo.setName("Jamie");
        service.addEmployee(ceo);
        Employee alan = new Employee();
        alan.setId(100l);
        alan.setName("Alan");
        alan.setManager(ceo);
        service.addEmployee(alan);

        Employee steve = new Employee();
        steve.setId(400l);
        steve.setName("Steve");
        steve.setManager(ceo);
        service.addEmployee(steve);

        Employee matin = new Employee();
        matin.setId(220l);
        matin.setName("Matin");
        matin.setManager(alan);
        service.addEmployee(matin);

        Employee alex = new Employee();
        alex.setId(275l);
        alex.setName("Alex");
        alex.setManager(alan);
        service.addEmployee(alex);

        Employee david = new Employee();
        david.setId(190l);
        david.setName("David");
        david.setManager(steve);
        service.addEmployee(david);
    }


}
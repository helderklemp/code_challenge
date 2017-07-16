import helderklemp.service.EmployeeService;
import helderklemp.service.EmployeeServiceImpl;
import helderklemp.service.model.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
    public void testListEmployees(){
        Assert.assertNotNull("At least one CEO",service.findEmployeeByManager(null));
        loadData();
        Employee ceo =service.findEmployeeByManager(null).iterator().next();
        Assert.assertEquals("Retrieve the CEO",ceo.getName(),"Jamie");
    }


    private void loadData() {
        Employee ceo = new Employee();
        ceo.setId(150l);
        ceo.setName("Jamie");
        service.addEmployee(ceo);
    }


}
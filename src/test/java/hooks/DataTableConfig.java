package hooks;

import java.util.Map;

import io.cucumber.java.DataTableType;
import pojo.Employee;

public class DataTableConfig {
	
	@DataTableType
    public Employee employeeDetails(Map<String, String> entry) {

        Employee emp = new Employee();

        emp.setFirstName(entry.get("firstName"));
        emp.setMiddleName(entry.get("middleName"));
        emp.setLastName(entry.get("lastName"));
        emp.setEmpId(entry.get("empId"));
        emp.setUserName(entry.get("userName"));
        emp.setStatus(entry.get("status"));
        emp.setPassword(entry.get("password"));

        return emp;
    }
	
}

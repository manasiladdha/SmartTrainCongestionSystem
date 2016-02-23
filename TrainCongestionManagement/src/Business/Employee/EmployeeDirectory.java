/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Employee;

import java.util.ArrayList;

/**
 *
 * @author Manasi Laddha
 */
public class EmployeeDirectory {
    private ArrayList<Employee> employeeList;
    private static int count = 1;

    public EmployeeDirectory() {
        employeeList = new ArrayList<>();
    }

    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }
    
    public Employee createEmployee(String name, Employee employee){
        //Employee employee = new Employee();
        employee.setName(name);
        employee.setId(count ++);
        employeeList.add(employee);
        return employee;
    }
    
    public void removeEmployee(Employee e){
        employeeList.remove(e);
    }
      
}

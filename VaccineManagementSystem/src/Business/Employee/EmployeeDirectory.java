/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Employee;

import Business.UserAccount.UserAccount;
import java.util.ArrayList;

/**
 *
 * @author Krishnakanth Naik Jarapala, Venkata Bhargavi Sikhakolli.
 */
public class EmployeeDirectory {
    private ArrayList<Employee> employeeList;

    public EmployeeDirectory() {
        employeeList = new ArrayList<>();
    }

    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }
    
    public Employee createEmployee(String name){
        Employee employee = new Employee();
        employee.setName(name);
        employeeList.add(employee);
        return employee;
    }
    
    public boolean deleteEmployee(Employee emp){
        for (Employee employee : employeeList) 
        {
            if(employee.equals(emp)){
                employeeList.remove(emp);
                return true;
            }
        }
        return false;
    }
    
}

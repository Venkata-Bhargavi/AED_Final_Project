/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Employee;

import java.util.ArrayList;

/**
 *
 * @author Krishnakanth Naik Jarapala
 */
public class employeedirectory {
    private ArrayList<employee> empList;

    public employeedirectory() {
        empList = new ArrayList<>();
    }

    public ArrayList<employee> getEmpList() {
        return empList;
    }
    
    public employee createEmployee(String name){
        employee emp = new employee();
        emp.setName(name);
        empList.add(emp);
        return emp;
    }
}

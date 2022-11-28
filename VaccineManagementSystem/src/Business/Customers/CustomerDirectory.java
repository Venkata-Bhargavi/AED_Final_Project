/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Customers;

import java.util.ArrayList;

/**
 *
 * @author Krishnakanth Naik Jarapala
 */
public class CustomerDirectory {
    ArrayList<Customer> customerlist;
    
    public CustomerDirectory() {
        customerlist = new ArrayList();
    }

    public ArrayList<Customer> getCustomerlist() {
        return customerlist;
    }

    public void setCustomerlist(ArrayList<Customer> customerlist) {
        this.customerlist = customerlist;
    }
    
    public Customer addCustomer()
    {
        Customer customer = new Customer();
        customerlist.add(customer);
        return customer;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.DistributorBillManagerRole;
import Business.Role.HospitalBillManagerRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author Krishnakanth Naik Jarapala, Venkata Bhargavi Sikhakolli.
 */
public class DistributorBillingOrganization extends Organization{

    DistributorBillingOrganization() {
        super(Organization.Type.DistributorBillingOrganization.getValue());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
       ArrayList<Role> roles = new ArrayList<>();
         roles.add(new DistributorBillManagerRole());
         return roles;
    }
    
    
    
}

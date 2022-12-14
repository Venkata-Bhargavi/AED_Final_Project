/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *
 * @author Krishnakanth Naik Jarapala, Venkata Bhargavi Sikhakolli.
 */


public abstract class Role {
    
    public enum RoleType{
        Admin("Admin"),
        ClinicAdmin("ClinicAdmin"),
        HospitalAdmin("HospitalAdmin"),
        DistributorAdmin("DistributorAdmin"),
        SupplierAdmin ("SupplierAdmin"),
        EventAdmin("Event Admin"),
        OrderManagerRole("Order manager"),
        PharmacyAdminRole("Pharmacy Manager");
        
        private String value;
        private RoleType(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
    
    public abstract JFrame createWorkArea( 
            UserAccount account, 
            Organization organization, 
            Enterprise enterprise, 
            EcoSystem business,
            Network network);

    @Override
    public String toString() {
        return this.getClass().getName();
        //return RoleType.valueOf(name);
    }
    
    
}
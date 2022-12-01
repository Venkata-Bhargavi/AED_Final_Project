/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.SubOrganization;

import Business.Organization.Organization;
import Business.Role.EventAdminRole;
import Business.Role.EventVolunteerRole;
import Business.Role.Role;
import java.util.ArrayList;


/**
 *
 * @author Krishnakanth Naik Jarapala, Venkata Bhargavi Sikhakolli.
 */


public class EventClinicOrganization extends SubOrganization{   
    
    
    public EventClinicOrganization(){
         super(SubOrganization.TypeSubOrg.EventClinic.getValue());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new EventVolunteerRole());
        return roles;
    }
    
}

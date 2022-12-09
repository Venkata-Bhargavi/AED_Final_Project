/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Role.PHDAdminRole;
import Business.Role.PHDManagerRole;
import Business.Role.Role;
import Business.WorkQueue.PHDHospitalApproval;
import java.util.ArrayList;


/**
 *
 * @author Krishnakanth Naik Jarapala, Venkata Bhargavi Sikhakolli.
 */


public class PHDEnterprise extends Enterprise{
    private ArrayList<PHDHospitalApproval> hospitalApprovalList;

    public PHDEnterprise(String name)
    {
        super(name, Enterprise.EnterpriseType.PHD);
        hospitalApprovalList = new ArrayList<>();
    }

    public ArrayList<PHDHospitalApproval> getHospitalApprovalList() {
        return hospitalApprovalList;
    }

    public void setHospitalApprovalList(ArrayList<PHDHospitalApproval> hospitalApprovalList) {
        this.hospitalApprovalList = hospitalApprovalList;
    }
    
    

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new PHDAdminRole());
        roles.add(new PHDManagerRole());
        return roles;
    }
    
}

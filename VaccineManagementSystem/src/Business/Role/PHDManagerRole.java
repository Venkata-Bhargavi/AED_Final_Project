/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.Organization.PHDOrderOrganization;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;
import UI.PHD.PHDManagerWorkArea;
import javax.swing.JFrame;


/**
 *
 * @author Krishnakanth Naik Jarapala, Venkata Bhargavi Sikhakolli.
 */


public class PHDManagerRole extends Role {

    @Override
    public JFrame createWorkArea(UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business,Network network) {
        return new PHDManagerWorkArea( account, (PHDOrderOrganization)organization,enterprise, business, network);
    }
    
}

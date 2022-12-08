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
import UI.AdministrativeRole.AdministrativeWorkArea;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *
 * @author Krishnakanth Naik Jarapala, Venkata Bhargavi Sikhakolli.
 */


public class AdminRole extends Role{

    @Override
    public JFrame createWorkArea(UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business,Network network) {
        return new AdministrativeWorkArea( enterprise, business);
    }

    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.DistributionCenterOrganization;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import javax.swing.JFrame;
import javax.swing.JPanel;
import UI.Distributor.DistributionCenter.DistributorCenterAdminWorkArea;


/**
 *
 * @author Krishnakanth Naik Jarapala, Venkata Bhargavi Sikhakolli.
 */


public class DistributorCenterAdminRole extends Role{

    @Override
    public JFrame createWorkArea(UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business,Network network) {
        return new DistributorCenterAdminWorkArea(account, (DistributionCenterOrganization)organization, enterprise, business, network);
    }
    
}

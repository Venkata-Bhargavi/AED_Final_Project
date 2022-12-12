/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import java.util.ArrayList;


/**
 *
 * @author Krishnakanth Naik Jarapala, Venkata Bhargavi Sikhakolli.
 */


public class EnterpriseDirectory {
    
    private ArrayList<Enterprise> enterpriseList;

    public EnterpriseDirectory() {
        enterpriseList = new ArrayList<>();
    }

    public ArrayList<Enterprise> getEnterpriseList() {
        return enterpriseList;
    }
    
    public Enterprise createAndAddEnterprise(Enterprise.EnterpriseType entType, String entName){
        Enterprise enterprise = null;
        if (entType == Enterprise.EnterpriseType.Hospital){
            enterprise = new HospitalEnterprise(entName);
            enterpriseList.add(enterprise);
        }
        if (entType == Enterprise.EnterpriseType.CDC){
            enterprise = new CDCEnterprise(entName);
            enterpriseList.add(enterprise);
        }
        if (entType == Enterprise.EnterpriseType.PHD){
            enterprise = new PHDEnterprise(entName);
            enterpriseList.add(enterprise);
        }
        if (entType == Enterprise.EnterpriseType.Distributor){
            enterprise = new DistributorEnterprise(entName);
            enterpriseList.add(enterprise);
        }
        if (entType == Enterprise.EnterpriseType.Insuarance){
            enterprise = new InsuaranceEnterprise(entName);
            enterpriseList.add(enterprise);
        }
        
        return enterprise;
    }
    
}

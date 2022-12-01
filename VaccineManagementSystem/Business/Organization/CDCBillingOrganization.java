
package Business.Organization;

import Business.Role.CDCBillManagerRole;
import Business.Role.Role;
import java.util.ArrayList;


/**
 *
 * @author Krishnakanth Naik Jarapala, Venkata Bhargavi Sikhakolli.
 */


public class CDCBillingOrganization extends Organization{
    
     public CDCBillingOrganization(){
         super(Organization.Type.CDCBillingOrganization.getValue());
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
         ArrayList<Role> roles = new ArrayList<>();
         roles.add(new CDCBillManagerRole());
         return roles;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Organization.Organization.OrganizationType;
import java.util.ArrayList;

/**
 *
 * @author raunak
 */
public class OrganizationDirectory {
    
    private ArrayList<Organization> organizationList;
   

    public OrganizationDirectory() {
        organizationList = new ArrayList<>();
    }

    public ArrayList<Organization> getOrganizationList() {
        return organizationList;
    }
    
    public Organization createOrganization(OrganizationType type){
        Organization organization = null;
        if (type.getValue().equals(OrganizationType.TM_ADMINISTRATOR_ORGANIZATION.getValue())){
            organization = new TMAdministratorOrganization(type.getValue());
           // organization.setOrganizationID(counter++);  
            organizationList.add(organization);
        }
        else if (type.getValue().equals(OrganizationType.TM_ANALYST_ORGANIZATION.getValue())){
            organization = new TMAnalystsOrganization(type.getValue());
           // organization.setOrganizationID(counter++);  
            organizationList.add(organization);
        }
        else if (type.getValue().equals(OrganizationType.TM_SUPERVISOR_ORGANIZATION.getValue())){
            organization = new TMSupervisorOrganization(type.getValue());
          //  organization.setOrganizationID(counter++); 
            organizationList.add(organization);
        }
        else if(type.getValue().equals(OrganizationType.TRAVELLER_ORGANIZATION.getValue())){
            organization = new TravellerOrganization(type.getValue());
            organizationList.add(organization);
        }
         else if(type.getValue().equals(OrganizationType.SECURITY_ADMIN_ORGANIZATION.getValue())){
            organization = new SecurityAdministratorOrganization(type.getValue());
            organizationList.add(organization);
        }
         else if(type.getValue().equals(OrganizationType.SECURITY_OFFICIER_ORGANIZATION.getValue())){
            organization = new SecurityOfficierOrganization(type.getValue());
            organizationList.add(organization);
        }
         else if(type.getValue().equals(OrganizationType.SECURITY_SUPERVISOR_ORGANIZATION.getValue())){
            organization = new SecuritySupervisorOrganization(type.getValue());
            organizationList.add(organization);
        }        
             
        return organization;
    }
}
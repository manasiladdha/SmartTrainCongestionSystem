/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Enterprise.SecurityAuthorityEnterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import UserInterface.TravellerWorkArea.TravellerWorkAreaPanel;
import javax.swing.JPanel;

/**
 *
 * @author Manasi Laddha
 */
public class TravellerRole extends Role{

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise,Network network, EcoSystem business) {
        SecurityAuthorityEnterprise sae = null;
        for(Enterprise ee : network.getEnterpriseDirectory().getEnterpriseList()){
            if(ee instanceof SecurityAuthorityEnterprise){
              sae =(SecurityAuthorityEnterprise) ee;
            }
        }
        
        return new TravellerWorkAreaPanel(userProcessContainer,enterprise,sae,account);
    }
    
    @Override
    public String toString() {
        return RoleType.TRAVELLER.getValue();
    }    
}

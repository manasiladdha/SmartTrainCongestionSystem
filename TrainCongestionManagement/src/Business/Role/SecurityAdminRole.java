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
import Business.UserAccount.UserAccount;
import UserInterface.SecurityAdministratorRole.SecurityAdministratorWorkArea;
import javax.swing.JPanel;

/**
 *
 * @author Manasi Laddha
 */
public class SecurityAdminRole extends Role
{
    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise,Network network, EcoSystem business) {
       return new SecurityAdministratorWorkArea(userProcessContainer,enterprise);
    }
    
    @Override
    public String toString() {
        return RoleType.SECURITY_ADMIN.getValue();
    }
    
}

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
import UserInterface.SystemAdminRole.SystemAdminWorkAreaJPanel;
import javax.swing.JPanel;

/**
 *
 * @author Manasi Laddha
 */
public class SystemAdminRole extends Role{

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise,Network network, EcoSystem system) {
        //return new SystemAdminWorkAreaJPanel(userProcessContainer, system);
        return new SystemAdminWorkAreaJPanel(userProcessContainer,system);
    }
    
    @Override
    public String toString() {
        return RoleType.SYSTEM_ADMIN.getValue();
    }
    
}

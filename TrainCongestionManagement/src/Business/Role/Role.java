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
import javax.swing.JPanel;

/**
 *
 * @author Manasi Laddha
 */
public abstract class Role {
    
    public enum RoleType{
        SYSTEM_ADMIN("SYSTEM_ADMIN"),
        TM_ADMINISTRATOR("TM Admin"),
        TM_SUPERVISOR("TM Supervisor"),       
        TRAIN_DRIVER("Train Driver"), 
        TRAVELLER("Traveller"), 
        TM_ANALYST("TM Analyst"),        
        SECURITY_OFFICIER("Security Officier"),
        SECURITY_SUPERVISOR("Security Supervisor"),
        SECURITY_ADMIN("SecurityAdminRole");
        
        private String value;
        private RoleType(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
    
    public abstract JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, Network network, EcoSystem business);

    @Override
    public String toString() {
        return this.getClass().getName();
    }
    
}

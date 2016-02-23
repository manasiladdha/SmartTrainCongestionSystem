/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.Role;
import Business.Role.SecurityOfficierRole;
import java.util.ArrayList;

/**
 *
 * @author Manasi Laddha
 */
public class SecurityOfficierOrganization extends Organization {

    public SecurityOfficierOrganization(String name) {
        super(name);
        this.getSupportedRoles().add(new SecurityOfficierRole());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        
        return getSupportedRoles();
    }
    
    
    
}

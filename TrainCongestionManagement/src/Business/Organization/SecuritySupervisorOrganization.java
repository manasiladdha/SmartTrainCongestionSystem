/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.Role;
import Business.Role.SecuritySupervisorRole;
import java.util.ArrayList;

/**
 *
 * @author Manasi Laddha
 */
public class SecuritySupervisorOrganization extends Organization{

    public SecuritySupervisorOrganization(String name) {
        super(name);
        this.getSupportedRoles().add(new SecuritySupervisorRole());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {        
        return this.getSupportedRoles();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.Role;
import Business.Role.SecurityAdminRole;
import java.util.ArrayList;

/**
 *
 * @author Manasi Laddha
 */
public class SecurityAdministratorOrganization extends Organization{

    public SecurityAdministratorOrganization(String name) {
        super(name);
        this.getSupportedRoles().add(new SecurityAdminRole());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        return this.getSupportedRoles();
    }
    
}

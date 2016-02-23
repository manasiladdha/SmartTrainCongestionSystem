/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.Role;
import Business.Role.TMAnalystRole;
import java.util.ArrayList;

/**
 *
 * @author Manasi Laddha
 */
public class TMAnalystsOrganization extends Organization {

    public TMAnalystsOrganization(String name) {
        super(name);
        this.getSupportedRoles().add(new TMAnalystRole());
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        return this.getSupportedRoles();
    }
    
}

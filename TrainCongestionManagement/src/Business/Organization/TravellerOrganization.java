/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.Role;
import Business.Role.TravellerRole;
import java.util.ArrayList;

/**
 *
 * @author Manasi Laddha
 */
public class TravellerOrganization extends Organization{

    public TravellerOrganization(String name) {
        super(name);
        this.getSupportedRoles().add(new TravellerRole());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {       
        return this.getSupportedRoles();
    }
    
    
    
}

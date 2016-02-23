/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.Role;
import Business.Role.TMSupervisorRole;
import Business.TrainManagementSystem.LineCatalog;
import Business.TrainManagementSystem.StationCatalog;
import Business.TrainManagementSystem.TrainCatalog;
import java.util.ArrayList;

/**
 *
 * @author Manasi Laddha
 */
public class TMSupervisorOrganization extends Organization{
    

    
    public TMSupervisorOrganization(String name) {
        super(name); this.getSupportedRoles().add(new TMSupervisorRole());
       
    }

  

    @Override
    public ArrayList<Role> getSupportedRole() {
       
        return this.getSupportedRoles();
    }

    

    
}

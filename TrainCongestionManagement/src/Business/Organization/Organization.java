/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Employee.Employee;
import Business.Employee.EmployeeDirectory;
import Business.Role.Role;
import Business.UserAccount.UserAccount;
import Business.UserAccount.UserAccountDirectory;
import Business.WorkQueue.WorkQueue;
import java.util.ArrayList;

/**
 *
 * @author Manasi Laddha
 */
public abstract class Organization {
    
    private String name;
    private WorkQueue workQueue;
    private EmployeeDirectory employeeDirectory;
    private UserAccountDirectory userAccountDirectory;
    private int organizationID;
    private ArrayList<Role> supportedRoles ;
    private static int counter = 1;
    
    
    public UserAccount fetchUserAccountforEmployee(Employee ee){
        for(UserAccount ua: userAccountDirectory.getUserAccountList()){
            if(ua.getEmployee().getName().equals(ee.getName())){
                return ua;
            }
        }
        return null;
    }
    
    public ArrayList<Role> getSupportedRoles() {
        return supportedRoles;
    }

    public void setSupportedRoles(ArrayList<Role> supportedRoles) {
        this.supportedRoles = supportedRoles;
    }

    public void setEmployeeDirectory(EmployeeDirectory employeeDirectory) {
        this.employeeDirectory = employeeDirectory;
    }

    public void setUserAccountDirectory(UserAccountDirectory userAccountDirectory) {
        this.userAccountDirectory = userAccountDirectory;
    }

    public void setOrganizationID(int organizationID) {
        this.organizationID = organizationID;
    }
  
    
    public enum OrganizationType{
        
        TM_ADMINISTRATOR_ORGANIZATION("TM Administrator Organization"),
        TM_ANALYST_ORGANIZATION("TM Analysts Organization"),
        TM_DRIVER_ORGANIZATION("T Driver Organization"), 
        TRAVELLER_ORGANIZATION("Traveller Organization"),
        TM_SUPERVISOR_ORGANIZATION("TM Supervisor Organization"),
        SECURITY_ADMIN_ORGANIZATION("Security Admin Organization"),
        SECURITY_OFFICIER_ORGANIZATION("Security Officier Organization"),
        SECURITY_SUPERVISOR_ORGANIZATION("Security Supervisor Organization");
         
        private String value;
        private OrganizationType(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }

    public Organization(String name) {
        this.name = name;
        workQueue = new WorkQueue();
        employeeDirectory = new EmployeeDirectory();
        userAccountDirectory = new UserAccountDirectory();
        supportedRoles = new ArrayList<>();
        organizationID = counter++;
    }
      
    public abstract ArrayList<Role> getSupportedRole();
    
    public UserAccountDirectory getUserAccountDirectory() {
        return userAccountDirectory;
    }

    public int getOrganizationID() {
        return organizationID;
    }

    public EmployeeDirectory getEmployeeDirectory() {
        return employeeDirectory;
    }
    
    public String getName() {
        return name;
    }

    public WorkQueue getWorkQueue() {
        return workQueue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWorkQueue(WorkQueue workQueue) {
        this.workQueue = workQueue;
    }

    @Override
    public String toString() {
        return name;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.SecurityAdministratorRole;

import Business.Employee.Employee;
import Business.Enterprise.Enterprise;
import Business.Enterprise.SecurityAuthorityEnterprise;
import Business.Organization.Organization;
import Business.Organization.SecurityOfficierOrganization;
import Business.Organization.SecuritySupervisorOrganization;
import Business.Role.Role;
import Business.Role.SecurityOfficierRole;
import Business.Role.SecuritySupervisorRole;
import Business.UserAccount.UserAccount;
import java.awt.CardLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Manasi Laddha
 */
public class ManageSUserAccountJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ManageSUserAccountJPanel
     */
    JPanel container;
    SecurityAuthorityEnterprise enterprise;
    
    public ManageSUserAccountJPanel(JPanel container, Enterprise enterprise) {
        initComponents();
        
        this.enterprise = (SecurityAuthorityEnterprise) enterprise;
        this.container = container;

        userJTable.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,18));
        
        popOrganizationComboBox();
       // employeeJComboBox.removeAllItems();
        popData();
    }

     private void popOrganizationComboBox() {
        organizationJComboBox.removeAllItems();

        for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
           if ((organization instanceof SecurityOfficierOrganization) || (organization instanceof SecuritySupervisorOrganization)){
               organizationJComboBox.addItem(organization);
           }
        }
    }
    
    public void populateEmployeeComboBox(Organization organization){
        employeeJComboBox.removeAllItems();
        ArrayList<Employee> accountCreatedEmployees = new ArrayList<Employee>();
        
        for(UserAccount account : organization.getUserAccountDirectory().getUserAccountList()){
            accountCreatedEmployees.add(account.getEmployee());
        }
        
        for (Employee employee : organization.getEmployeeDirectory().getEmployeeList()){            
            if(!accountCreatedEmployees.contains(employee))
            employeeJComboBox.addItem(employee); 
        }  
        
        if(employeeJComboBox.getItemCount() <= 0) {
            createUserJButton.setEnabled(false);
            employeeJComboBox.setEnabled(false);
            roleJComboBox.setEnabled(false);
            txtPassword.setEnabled(false);
            txtUserName.setEnabled(false);
        }
        else{
             createUserJButton.setEnabled(true);
             employeeJComboBox.setEnabled(true);
             roleJComboBox.setEnabled(true);
             txtPassword.setEnabled(true);
             txtUserName.setEnabled(true);
        }
    }
    
    private void populateRoleComboBox(Organization organization){
        roleJComboBox.removeAllItems();
        
        for (Role role : organization.getSupportedRoles()){
            roleJComboBox.addItem(role);
        }
    }

    private void popData() {

        DefaultTableModel model = (DefaultTableModel) userJTable.getModel();

        model.setRowCount(0);

        for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
            for (UserAccount ua : organization.getUserAccountDirectory().getUserAccountList()) {
                Object row[] = new Object[2];
                row[0] = ua;
                row[1] = ua.getRole();
                ((DefaultTableModel) userJTable.getModel()).addRow(row);
            }
        }
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backjButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        organizationJComboBox = new javax.swing.JComboBox();
        createUserJButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        roleJComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        userJTable = new javax.swing.JTable();
        lblErrMsgUser = new javax.swing.JLabel();
        lblErrMsgPswrd = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        employeeJComboBox = new javax.swing.JComboBox();
        lblErrMsgPswrd1 = new javax.swing.JLabel();
        lblErrMsgPswrd2 = new javax.swing.JLabel();

        backjButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        backjButton1.setText("<< Back");
        backjButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backjButton1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Organization");

        organizationJComboBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        organizationJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        organizationJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                organizationJComboBoxActionPerformed(evt);
            }
        });

        createUserJButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        createUserJButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/submit.png"))); // NOI18N
        createUserJButton.setText("Create");
        createUserJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createUserJButtonActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Role");

        txtUserName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtUserName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUserNameFocusLost(evt);
            }
        });

        roleJComboBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        roleJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("User Name");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/account.png"))); // NOI18N
        jLabel6.setText("Create User Account");

        userJTable.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        userJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User Name", "Role"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(userJTable);

        lblErrMsgUser.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblErrMsgUser.setForeground(java.awt.Color.red);
        lblErrMsgUser.setText("ErrMsg");

        lblErrMsgPswrd.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblErrMsgPswrd.setForeground(java.awt.Color.red);
        lblErrMsgPswrd.setText("ErrMsg");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Password");

        txtPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPasswordFocusLost(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Employee");

        employeeJComboBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        employeeJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblErrMsgPswrd1.setForeground(java.awt.Color.red);
        lblErrMsgPswrd1.setText("*UserName should start with letters and ends with digit (No special characters)");

        lblErrMsgPswrd2.setForeground(java.awt.Color.red);
        lblErrMsgPswrd2.setText("*No Spaces, Minimum Length 6,Atleast one letter & number");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGap(139, 139, 139)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(lblErrMsgPswrd1))
                            .addComponent(lblErrMsgPswrd2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(organizationJComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(employeeJComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(roleJComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(1, 1, 1)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblErrMsgUser)
                                    .addComponent(lblErrMsgPswrd)))
                            .addComponent(createUserJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 782, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 107, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(345, 345, 345)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(backjButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel6)
                .addGap(41, 41, 41)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(organizationJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(employeeJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roleJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblErrMsgUser))
                .addGap(13, 13, 13)
                .addComponent(lblErrMsgPswrd1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblErrMsgPswrd)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblErrMsgPswrd2)
                .addGap(26, 26, 26)
                .addComponent(createUserJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                .addComponent(backjButton1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backjButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backjButton1ActionPerformed
        // TODO add your handling code here:
        container.remove(this);
        CardLayout layout = (CardLayout) container.getLayout();
        layout.previous(container);
    }//GEN-LAST:event_backjButton1ActionPerformed

    private void organizationJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_organizationJComboBoxActionPerformed
        Organization organization = (Organization) organizationJComboBox.getSelectedItem();
        if (organization != null){
            populateEmployeeComboBox(organization);
            populateRoleComboBox(organization);
        }
    }//GEN-LAST:event_organizationJComboBoxActionPerformed

    private boolean bUsernameCheck = false;
    private boolean bPasswordCheck = false;
    private void createUserJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createUserJButtonActionPerformed

        if(!bUsernameCheck && !bPasswordCheck){
            JOptionPane.showMessageDialog(this, "Username or Password is incorrect!", "User Account", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String userName = txtUserName.getText().trim();

        char[] passwordCharArray = txtPassword.getPassword();
        String password = String.valueOf(passwordCharArray).trim();

        Organization organization = (Organization) organizationJComboBox.getSelectedItem();
        Employee employee = (Employee) employeeJComboBox.getSelectedItem();

        if (organization instanceof SecuritySupervisorOrganization){
            SecuritySupervisorRole role = (SecuritySupervisorRole) roleJComboBox.getSelectedItem();

            if (organization.getUserAccountDirectory().checkIfUsernameIsUnique(userName)){
                organization.getUserAccountDirectory().createUserAccount(userName, password, employee, role);
                //clean all of things here and populate the table again
                popData();
                txtPassword.setText("");
                txtUserName.setText("");
                JOptionPane.showMessageDialog(this, "User Account created successfully.", "User Account", JOptionPane.INFORMATION_MESSAGE);

            }
            else {

                JOptionPane.showMessageDialog(this, "User Name already exists.", "User Account", JOptionPane.WARNING_MESSAGE);
            }

        }
        else if (organization instanceof SecurityOfficierOrganization) {
            SecurityOfficierRole role = (SecurityOfficierRole) roleJComboBox.getSelectedItem();

            if (organization.getUserAccountDirectory().checkIfUsernameIsUnique(userName)){
                organization.getUserAccountDirectory().createUserAccount(userName, password, employee, role);
                //clean all of things here and populate the table again
                popData();
                txtPassword.setText("");
                txtUserName.setText("");
                JOptionPane.showMessageDialog(this, "User Account created successfully.", "User Account", JOptionPane.INFORMATION_MESSAGE);

            }
            else {

                JOptionPane.showMessageDialog(this, "User Name already exists.", "User Account", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        popOrganizationComboBox();

    }//GEN-LAST:event_createUserJButtonActionPerformed

    private void txtUserNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUserNameFocusLost
        // TODO add your handling code here:
        if(txtUserName.getText().trim().length() == 0){
            lblErrMsgUser.setText("UserName cannot be blank.");
            bUsernameCheck = false;
        }
        else
        {  
            String name = txtUserName.getText();
             boolean matched = name.matches("^[a-zA-Z]+[0-9]+$");
             if(matched){
                bUsernameCheck = true;
                lblErrMsgUser.setText("");
             }
             else{
                bUsernameCheck = false;
                lblErrMsgUser.setText("User Name does not match the criteria.");
             }
        }

    }//GEN-LAST:event_txtUserNameFocusLost

    private void txtPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPasswordFocusLost
        // TODO add your handling code here:
        if(txtPassword.getPassword().length == 0){
            lblErrMsgPswrd.setText("Password cannot be blank.");
            bPasswordCheck = false;
        }
        else
        {
                String password = String.valueOf(txtPassword.getPassword());
                Pattern p = Pattern.compile("^(?=.*[0-9a-zA-Z])(?=\\S+$).{6,}$");
                Matcher m = p.matcher(password);
                
                if(m.matches()){                   
                    bPasswordCheck = true;
                    lblErrMsgPswrd.setText("");
                }
                else{
                    lblErrMsgPswrd.setText("Password does not match criteria.");  
                    bPasswordCheck = false;
                }
            
            
        }
    }//GEN-LAST:event_txtPasswordFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backjButton1;
    private javax.swing.JButton createUserJButton;
    private javax.swing.JComboBox employeeJComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblErrMsgPswrd;
    private javax.swing.JLabel lblErrMsgPswrd1;
    private javax.swing.JLabel lblErrMsgPswrd2;
    private javax.swing.JLabel lblErrMsgUser;
    private javax.swing.JComboBox organizationJComboBox;
    private javax.swing.JComboBox roleJComboBox;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUserName;
    private javax.swing.JTable userJTable;
    // End of variables declaration//GEN-END:variables
}
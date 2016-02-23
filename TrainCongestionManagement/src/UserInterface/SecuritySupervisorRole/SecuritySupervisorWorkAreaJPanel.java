/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.SecuritySupervisorRole;

import Business.Employee.Employee;
import Business.Employee.SecurityOfficierEmployee;
import Business.Enterprise.Enterprise;
import Business.Enterprise.SecurityAuthorityEnterprise;
import Business.Organization.Organization;
import Business.Organization.SecurityOfficierOrganization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.IncidentWorkRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.Font;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Manasi Laddha
 */
public class SecuritySupervisorWorkAreaJPanel extends javax.swing.JPanel {

    /**
     * Creates new form SecuritySupervisorWorkAreaJPanel
     */
    JPanel userProcessContainer;
    UserAccount account;
    Organization organization;
    SecurityAuthorityEnterprise enterprise;
    
    public SecuritySupervisorWorkAreaJPanel(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.organization = organization;
        this.enterprise = (SecurityAuthorityEnterprise) enterprise;
        
        tblNewIncidents.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 18));
        tblAssignedIncidents.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 18));
        tblResolvedIncidents.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        populateSecurityOfficiers();
        
        populateNewIncidents();        
        populateAssignedIncidents();
        populateResolvedIncidents();
        
    }
    
    private void populateResolvedIncidents(){
        
        DefaultTableModel dtm = (DefaultTableModel) tblResolvedIncidents.getModel();
        dtm.setRowCount(0);
        
        for(WorkRequest request : enterprise.getWorkQueue().getWorkRequestList()){
            if(request.getStatus().equals(IncidentWorkRequest.StatusType.ACTION_TAKEN.getValue())){
             
              Object row[] = new Object[11];
                row[0] = ((IncidentWorkRequest)request);
                row[1] = request.getSender().getEmployee();
                row[2] = ((IncidentWorkRequest)request).getIncidentLine();
                row[3] = ((IncidentWorkRequest)request).getIncidentRoute();
                row[4] = ((IncidentWorkRequest)request).getIncidentRouteSchedule().getTimeSlotsTrain().getValue(); 
                row[5] = ((IncidentWorkRequest)request).getIncidentTrain();
                row[6] = ((IncidentWorkRequest)request).getIncidentTrainCarNumber();
                row[7] = ((IncidentWorkRequest)request).getIncidentRoute().getEndStation();               
                row[8] = request.getSender().getEmployee();     
                row[9] =  request.getRequestDate();
                row[10] = request.getResolveDate();
                dtm.addRow(row);
            }
        }
        
    }
    
    private void populateNewIncidents(){
        
        DefaultTableModel dtm = (DefaultTableModel) tblNewIncidents.getModel();
        dtm.setRowCount(0);
        
        for(WorkRequest request : enterprise.getWorkQueue().getWorkRequestList()){
            if(request.getStatus().equals(IncidentWorkRequest.StatusType.NEW_INCIDENT.getValue())){
             
              Object row[] = new Object[9];
                row[0] = ((IncidentWorkRequest)request);
                row[1] = request.getSender().getEmployee();
                row[2] = ((IncidentWorkRequest)request).getIncidentLine();
                row[3] = ((IncidentWorkRequest)request).getIncidentRoute();
                row[4] = ((IncidentWorkRequest)request).getIncidentRouteSchedule().getTimeSlotsTrain().getValue(); 
                row[5] = ((IncidentWorkRequest)request).getIncidentTrain();
                row[6] = ((IncidentWorkRequest)request).getIncidentTrainCarNumber();
                row[7] = ((IncidentWorkRequest)request).getIncidentRoute().getEndStation();               
                row[8] = request.getReceiver();     
                
                dtm.addRow(row);
            }
        }
    }
    
    private void populateSecurityOfficiers(){
        cboSecurityOfficier.removeAllItems();        
        for(Organization org : enterprise.getOrganizationDirectory().getOrganizationList()){
            if(org instanceof SecurityOfficierOrganization){
                for(Employee ee : org.getEmployeeDirectory().getEmployeeList()){
                    SecurityOfficierEmployee so = (SecurityOfficierEmployee) ee;                    
                    if(!so.isIsBusy()){
                     cboSecurityOfficier.addItem(so);
                    }                    
                }
            }
        }    
        
        if(cboSecurityOfficier.getItemCount() < 0){
            btnAssign.setEnabled(false);
            cboSecurityOfficier.setEnabled(false);                   
        }
        else {  
            btnAssign.setEnabled(true);
            cboSecurityOfficier.setEnabled(true);                   
        }
    }

    private void populateAssignedIncidents(){
        
        DefaultTableModel dtm = (DefaultTableModel) tblAssignedIncidents.getModel();
        dtm.setRowCount(0);
        
        for(WorkRequest request : enterprise.getWorkQueue().getWorkRequestList()){
            if(request.getStatus().equals(IncidentWorkRequest.StatusType.ASSIGNED.getValue())){
             
              Object row[] = new Object[9];
                row[0] = ((IncidentWorkRequest)request);
                row[1] = request.getSender().getEmployee();
                row[2] = ((IncidentWorkRequest)request).getIncidentLine();
                row[3] = ((IncidentWorkRequest)request).getIncidentRoute();
                row[4] = ((IncidentWorkRequest)request).getIncidentRouteSchedule().getTimeSlotsTrain().getValue(); 
                row[5] = ((IncidentWorkRequest)request).getIncidentTrain();
                row[6] = ((IncidentWorkRequest)request).getIncidentTrainCarNumber();
                row[7] = ((IncidentWorkRequest)request).getIncidentRoute().getEndStation();               
                row[8] = request.getSender().getEmployee();     
                
                dtm.addRow(row);

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

        jLabel6 = new javax.swing.JLabel();
        mainPanel = new javax.swing.JTabbedPane();
        newIncidents = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNewIncidents = new javax.swing.JTable() {

            //Implement table cell tool tips.
            public String getToolTipText(MouseEvent e) {
                String tip = null;
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);

                if(rowIndex <0){
                    return null;
                }

                IncidentWorkRequest request = (IncidentWorkRequest) tblNewIncidents.getValueAt(rowIndex, 0);
                tip = request.getIncidentCauseMessage();

                return tip;

            }
        };
        jLabel1 = new javax.swing.JLabel();
        cboSecurityOfficier = new javax.swing.JComboBox();
        btnAssign = new javax.swing.JButton();
        assignedIncidents = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblAssignedIncidents = new javax.swing.JTable() {

            //Implement table cell tool tips.
            public String getToolTipText(MouseEvent e) {
                String tip = null;
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);

                if(rowIndex <0){
                    return null;
                }

                IncidentWorkRequest request = (IncidentWorkRequest) tblAssignedIncidents.getValueAt(rowIndex, 0);
                tip = request.getIncidentCauseMessage();

                return tip;

            }
        };
        resolveIncidents = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblResolvedIncidents = new javax.swing.JTable() {

            //Implement table cell tool tips.
            public String getToolTipText(MouseEvent e) {
                String tip = null;
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);

                if(rowIndex <0){
                    return null;
                }

                IncidentWorkRequest request = (IncidentWorkRequest) tblResolvedIncidents.getValueAt(rowIndex, 0);
                tip = request.getIncidentCauseMessage();

                return tip;

            }
        };

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/supervisor.GIF"))); // NOI18N
        jLabel6.setText("Security Supervisor Work Area");

        mainPanel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        tblNewIncidents.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblNewIncidents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Request ID", "Victim", "Line", "Route", "Schedule", "Train", "Car Number", "Action on Station", "Assigned To"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNewIncidents.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblNewIncidents);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Security Officier Available :");

        cboSecurityOfficier.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnAssign.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnAssign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/submit.png"))); // NOI18N
        btnAssign.setText("Assign");
        btnAssign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAssignActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout newIncidentsLayout = new javax.swing.GroupLayout(newIncidents);
        newIncidents.setLayout(newIncidentsLayout);
        newIncidentsLayout.setHorizontalGroup(
            newIncidentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newIncidentsLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(newIncidentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(newIncidentsLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(cboSecurityOfficier, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAssign)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1266, Short.MAX_VALUE))
                .addContainerGap())
        );
        newIncidentsLayout.setVerticalGroup(
            newIncidentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newIncidentsLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addGroup(newIncidentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cboSecurityOfficier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAssign))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel.addTab("New Incidents Request", newIncidents);

        tblAssignedIncidents.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblAssignedIncidents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Request ID", "Victim", "Line", "Route", "Schedule", "Train", "Car Number", "Action on Station", "Assigned To"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAssignedIncidents.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblAssignedIncidents);

        javax.swing.GroupLayout assignedIncidentsLayout = new javax.swing.GroupLayout(assignedIncidents);
        assignedIncidents.setLayout(assignedIncidentsLayout);
        assignedIncidentsLayout.setHorizontalGroup(
            assignedIncidentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assignedIncidentsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addGap(32, 32, 32))
        );
        assignedIncidentsLayout.setVerticalGroup(
            assignedIncidentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assignedIncidentsLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel.addTab("Assigned Incidents", assignedIncidents);

        tblResolvedIncidents.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblResolvedIncidents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Request ID", "Victim", "Line", "Route", "Schedule", "Train", "Car Number", "End Station", "Assigned To", "Request Date", "Resolved Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblResolvedIncidents.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tblResolvedIncidents);

        javax.swing.GroupLayout resolveIncidentsLayout = new javax.swing.GroupLayout(resolveIncidents);
        resolveIncidents.setLayout(resolveIncidentsLayout);
        resolveIncidentsLayout.setHorizontalGroup(
            resolveIncidentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resolveIncidentsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addGap(32, 32, 32))
        );
        resolveIncidentsLayout.setVerticalGroup(
            resolveIncidentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resolveIncidentsLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel.addTab("Resolved Incidents", resolveIncidents);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(322, 322, 322)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(mainPanel)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAssignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAssignActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblNewIncidents.getSelectedRow();
        
        if(selectedRow <0){
            JOptionPane.showMessageDialog(this, "Please select a row.", "Assign Incident", JOptionPane.WARNING_MESSAGE);
            return;
        }        
        
       IncidentWorkRequest request = (IncidentWorkRequest) tblNewIncidents.getValueAt(selectedRow, 0);
        
       if(request.getReceiver() == null){           
           request.setIntermediateReceiver(account);           
           SecurityOfficierEmployee so = (SecurityOfficierEmployee) cboSecurityOfficier.getSelectedItem();
           for(Organization org : enterprise.getOrganizationDirectory().getOrganizationList()){
            if(org instanceof SecurityOfficierOrganization){
              UserAccount officierAccount = org.fetchUserAccountforEmployee(so);
              request.setReceiver(officierAccount);
              request.setStatus(IncidentWorkRequest.StatusType.ASSIGNED.getValue());
              officierAccount.getWorkQueue().getWorkRequestList().add(request);
              so.setIsBusy(true);
              populateAssignedIncidents();
              populateNewIncidents();
              populateSecurityOfficiers();
            }
           }           
       }
       else {
          JOptionPane.showMessageDialog(this, "Request already Assigned.", "Assign Incident", JOptionPane.WARNING_MESSAGE);
          return; 
       }
    }//GEN-LAST:event_btnAssignActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel assignedIncidents;
    private javax.swing.JButton btnAssign;
    private javax.swing.JComboBox cboSecurityOfficier;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane mainPanel;
    private javax.swing.JPanel newIncidents;
    private javax.swing.JPanel resolveIncidents;
    private javax.swing.JTable tblAssignedIncidents;
    private javax.swing.JTable tblNewIncidents;
    private javax.swing.JTable tblResolvedIncidents;
    // End of variables declaration//GEN-END:variables
}

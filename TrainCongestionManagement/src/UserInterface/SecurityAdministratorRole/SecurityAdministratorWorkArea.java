/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.SecurityAdministratorRole;

import Business.Enterprise.Enterprise;
import Business.Enterprise.SecurityAuthorityEnterprise;
import Business.WorkQueue.IncidentWorkRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Manasi Laddha
 */
public class SecurityAdministratorWorkArea extends javax.swing.JPanel {

    /**
     * Creates new form SecurityAdministratorWorkArea
     */
    JPanel userProcessContainer;
    SecurityAuthorityEnterprise securityAuthorityEnterprise;
    
    public SecurityAdministratorWorkArea(JPanel userProcessContainer,Enterprise enterprise) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.securityAuthorityEnterprise = (SecurityAuthorityEnterprise) enterprise;
        
        valueLabel.setText(securityAuthorityEnterprise.getName());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        manageEmployeeJButton = new javax.swing.JButton();
        manageOrganizationJButton = new javax.swing.JButton();
        enterpriseLabel = new javax.swing.JLabel();
        valueLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        userJButton = new javax.swing.JButton();
        userJButton1 = new javax.swing.JButton();

        manageEmployeeJButton.setBackground(new java.awt.Color(0, 0, 0));
        manageEmployeeJButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        manageEmployeeJButton.setForeground(new java.awt.Color(255, 255, 255));
        manageEmployeeJButton.setText("Manage Employee >>");
        manageEmployeeJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageEmployeeJButtonActionPerformed(evt);
            }
        });

        manageOrganizationJButton.setBackground(new java.awt.Color(0, 0, 0));
        manageOrganizationJButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        manageOrganizationJButton.setForeground(new java.awt.Color(255, 255, 255));
        manageOrganizationJButton.setText("Manage Organization >>");
        manageOrganizationJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageOrganizationJButtonActionPerformed(evt);
            }
        });

        enterpriseLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        enterpriseLabel.setText("Enterprise :");

        valueLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        valueLabel.setText("<value>");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/secEnt.png"))); // NOI18N
        jLabel1.setText("Security Enterprise Administrator Work Area");

        userJButton.setBackground(new java.awt.Color(0, 0, 0));
        userJButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        userJButton.setForeground(new java.awt.Color(255, 255, 255));
        userJButton.setText("Manage User Accounts >>");
        userJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userJButtonActionPerformed(evt);
            }
        });

        userJButton1.setBackground(new java.awt.Color(0, 0, 0));
        userJButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        userJButton1.setForeground(new java.awt.Color(255, 255, 255));
        userJButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/alert.png"))); // NOI18N
        userJButton1.setText("Incidents Analysis >>");
        userJButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userJButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(enterpriseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(manageOrganizationJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(manageEmployeeJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(userJButton, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                            .addComponent(userJButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(valueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1))
                .addContainerGap(144, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enterpriseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valueLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(manageOrganizationJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(manageEmployeeJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(userJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(userJButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(146, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void manageEmployeeJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageEmployeeJButtonActionPerformed

        ManageSEmployeeJPanel manageSEmployeeJPanel = new ManageSEmployeeJPanel(userProcessContainer, securityAuthorityEnterprise.getOrganizationDirectory());
        userProcessContainer.add("ManageSEmployeeJPanel", manageSEmployeeJPanel);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_manageEmployeeJButtonActionPerformed

    private void manageOrganizationJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageOrganizationJButtonActionPerformed

        ManageSOrganizationJPanel manageSOrganizationJPanel = new ManageSOrganizationJPanel(userProcessContainer, securityAuthorityEnterprise.getOrganizationDirectory());
        userProcessContainer.add("ManageSOrganizationJPanel", manageSOrganizationJPanel);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_manageOrganizationJButtonActionPerformed

    private void userJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userJButtonActionPerformed
        // TODO add your handling code here:
        ManageSUserAccountJPanel muSajp = new ManageSUserAccountJPanel(userProcessContainer, securityAuthorityEnterprise);
        userProcessContainer.add("ManageSUserAccountJPanel", muSajp);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_userJButtonActionPerformed

    private void userJButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userJButton1ActionPerformed
        // TODO add your handling code here:
//        IncidentsAnalysisJPanel incidentsAnalysisJPanel = new IncidentsAnalysisJPanel(userProcessContainer, securityAuthorityEnterprise);
//        userProcessContainer.add("IncidentsAnalysisJPanel", incidentsAnalysisJPanel);
//        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
//        layout.next(userProcessContainer);
//        
//        
       // SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE, MMM d, yyyy");  
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
      
        Date today = new Date();
        Date weekBefore = new Date();
        weekBefore.setTime(today.getTime() + (long)(-7)*1000*60*60*24);
        
        long todayDate = (today.getTime())/(1000*60*60*24);
        long weekDate = (weekBefore.getTime())/(1000*60*60*24);
        //ArrayList<Date> incidentDatesList = new ArrayList<Date>();
        
        int cong1 =0;
        int cong2 =0;
        int cong3 =0;
        int cong4 =0;
       
        int counter1 =0;
        int counter2 =0;
        int counter3 =0;
        int counter4 =0;
        
        for(WorkRequest request: securityAuthorityEnterprise.getWorkQueue().getWorkRequestList()){
            IncidentWorkRequest incidentRequest = (IncidentWorkRequest) request;
            long requestDate = (request.getRequestDate().getTime())/(1000*60*60*24);
            if(requestDate >=weekDate && requestDate <= todayDate ){
               
                   if(incidentRequest.getTypeOfEmergency().equals("Medical")){
                       cong1 +=incidentRequest.getCrowdednessLevel();
                       counter1++;
                   }
                   else if (incidentRequest.getTypeOfEmergency().equals("Accidental")){
                       cong2 +=incidentRequest.getCrowdednessLevel();
                       counter2++;
                   }
                   else if (incidentRequest.getTypeOfEmergency().equals("Security")){
                       cong3 +=incidentRequest.getCrowdednessLevel();
                       counter3++;
                   }
                   else {
                       cong4 +=incidentRequest.getCrowdednessLevel();
                       counter4++;
                   }
               
            }
        }     
        
        if(counter4==0){
           counter4=1;
        }
        if(counter3==0){
           counter3=1;
        }
        if(counter2==0){
           counter2=1;
        }
        if(counter1==0){
           counter1=1;
        }
        
        
        
        dataset.setValue(cong1/counter1, "Incident Types" , "Medical");
        dataset.setValue(cong2/counter2, "Incident Types" , "Accidental");
        dataset.setValue(cong3/counter3, "Incident Types" , "Security");
        dataset.setValue(cong4/counter4, "Incident Types" , "Other");
        
        JFreeChart chart = ChartFactory.createBarChart("Incident Type v/s Average Congestion", "Incident Types","Average Congestion Levels", dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.BLUE);
        ChartFrame frame = new ChartFrame("Bar Chart for Congestion Analysis",chart);
        frame.setVisible(true);
        frame.setSize(750, 750);
        
    }//GEN-LAST:event_userJButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel enterpriseLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton manageEmployeeJButton;
    private javax.swing.JButton manageOrganizationJButton;
    private javax.swing.JButton userJButton;
    private javax.swing.JButton userJButton1;
    private javax.swing.JLabel valueLabel;
    // End of variables declaration//GEN-END:variables
}

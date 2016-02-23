/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.TMSupervisorRole;

import Business.Enterprise.TrainAuthorityEnterprise;
import Business.TrainManagementSystem.Station;
import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Manasi Laddha
 */
public class ManageStationJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ManageStationJPanel
     */
    
    TrainAuthorityEnterprise trainAuthorityEnterprise ;
    JPanel userProcessContainer;
    
    public ManageStationJPanel( JPanel userProcessContainer,TrainAuthorityEnterprise enterprise) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.trainAuthorityEnterprise = enterprise;
       
      //  populateList();
        populateTable();
        populatePopularity();
        tblStations.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,18));
        lblErrMsgStation.setText("");
       // lblErrMsgLine.setText("");
        rbtnNo.setSelected(true);        
        tblStations.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 18));
        
    }
    
    private void populatePopularity(){
        
        Station.Popularity[] values = Station.Popularity.values();
        cboStationPopularity.removeAllItems();
        cboPopularity.removeAllItems();
        for(Station.Popularity p : values){
            cboStationPopularity.addItem(p.getValue());
            cboPopularity.addItem(p.getValue());
        }
        
    }
    
    private void populateTable(){
        
        DefaultTableModel model = (DefaultTableModel) tblStations.getModel();
        
        model.setRowCount(0);
        
        for (Station s : trainAuthorityEnterprise.getStationCatalog().getStationsList()){
            Object[] row = new Object[5];
            row[0] = s.getStationID();
            row[1] = s;
            row[2] = s.isActiveFlag();
            row[3] = s.isMultiLineStation();
            if(s.getStationPopularity() == null){
                row[4] = "";
            }
            else  row[4] = s.getStationPopularity().getValue();
            model.addRow(row);
        }
        
    }
    
//    public void populateList(){
//        
//        DefaultListModel dtm = (DefaultListModel)lstLines.getModel();
//        dtm.clear();
//        if (trainAuthorityEnterprise.getLineCatalog().getLinesList().isEmpty())
//           trainAuthorityEnterprise.getLineCatalog().addLine("Green Line");
//                      
//        for(Line l : trainAuthorityEnterprise.getLineCatalog().getLinesList())
//            dtm.addElement(l);
//         
//         dtm.addElement(new Line("Green Line")); 
//    
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel6 = new javax.swing.JLabel();
        manageStationJTabbledPane = new javax.swing.JTabbedPane();
        CreateStationJPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtStationName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        chkActiveFlag = new javax.swing.JCheckBox();
        btnSubmitStation = new javax.swing.JButton();
        lblErrMsgStation = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        rbtnNo = new javax.swing.JRadioButton();
        rbtnYes = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        cboPopularity = new javax.swing.JComboBox();
        UpdateStationJPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblStations = new javax.swing.JTable();
        btnUpdateStation = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        cboStationPopularity = new javax.swing.JComboBox();
        btnBack = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/train.png"))); // NOI18N
        jLabel6.setText("Manage T-Stations");

        manageStationJTabbledPane.setBackground(new java.awt.Color(255, 255, 255));
        manageStationJTabbledPane.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setText("Create New Station");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Station Name :");

        txtStationName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtStationName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtStationNameFocusLost(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Station Active :");

        chkActiveFlag.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        chkActiveFlag.setText("Active");

        btnSubmitStation.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSubmitStation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/submit.png"))); // NOI18N
        btnSubmitStation.setText("Submit");
        btnSubmitStation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitStationActionPerformed(evt);
            }
        });

        lblErrMsgStation.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblErrMsgStation.setForeground(java.awt.Color.red);
        lblErrMsgStation.setText("ErrMsg");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Multi-Line Station :");

        buttonGroup1.add(rbtnNo);
        rbtnNo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rbtnNo.setText("No");

        buttonGroup1.add(rbtnYes);
        rbtnYes.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rbtnYes.setText("Yes");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Station Popularity :");

        cboPopularity.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout CreateStationJPanelLayout = new javax.swing.GroupLayout(CreateStationJPanel);
        CreateStationJPanel.setLayout(CreateStationJPanelLayout);
        CreateStationJPanelLayout.setHorizontalGroup(
            CreateStationJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CreateStationJPanelLayout.createSequentialGroup()
                .addGroup(CreateStationJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CreateStationJPanelLayout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addComponent(jLabel1))
                    .addGroup(CreateStationJPanelLayout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addGroup(CreateStationJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(43, 43, 43)
                        .addGroup(CreateStationJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkActiveFlag)
                            .addGroup(CreateStationJPanelLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(CreateStationJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSubmitStation, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(CreateStationJPanelLayout.createSequentialGroup()
                                        .addComponent(rbtnYes)
                                        .addGap(18, 18, 18)
                                        .addComponent(rbtnNo))
                                    .addGroup(CreateStationJPanelLayout.createSequentialGroup()
                                        .addGroup(CreateStationJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtStationName)
                                            .addComponent(cboPopularity, 0, 213, Short.MAX_VALUE))
                                        .addGap(37, 37, 37)
                                        .addComponent(lblErrMsgStation)))))))
                .addContainerGap(550, Short.MAX_VALUE))
        );
        CreateStationJPanelLayout.setVerticalGroup(
            CreateStationJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CreateStationJPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(CreateStationJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtStationName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblErrMsgStation))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(CreateStationJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(chkActiveFlag))
                .addGap(14, 14, 14)
                .addGroup(CreateStationJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(rbtnNo)
                    .addComponent(rbtnYes))
                .addGap(18, 18, 18)
                .addGroup(CreateStationJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cboPopularity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(btnSubmitStation, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(253, Short.MAX_VALUE))
        );

        manageStationJTabbledPane.addTab("Create Station", CreateStationJPanel);

        tblStations.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblStations.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Station ID", "Station Name", "Station Active", "Multi-Line", "Popularity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblStations.setRowHeight(18);
        tblStations.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblStations);

        btnUpdateStation.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnUpdateStation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/submit.png"))); // NOI18N
        btnUpdateStation.setText("Update Station");
        btnUpdateStation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateStationActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Popularity:");

        cboStationPopularity.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout UpdateStationJPanelLayout = new javax.swing.GroupLayout(UpdateStationJPanel);
        UpdateStationJPanel.setLayout(UpdateStationJPanelLayout);
        UpdateStationJPanelLayout.setHorizontalGroup(
            UpdateStationJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UpdateStationJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(UpdateStationJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1158, Short.MAX_VALUE)
                    .addGroup(UpdateStationJPanelLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(cboStationPopularity, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 607, Short.MAX_VALUE)
                        .addComponent(btnUpdateStation, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        UpdateStationJPanelLayout.setVerticalGroup(
            UpdateStationJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UpdateStationJPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(UpdateStationJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UpdateStationJPanelLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btnUpdateStation, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(UpdateStationJPanelLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(UpdateStationJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cboStationPopularity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(272, Short.MAX_VALUE))
        );

        manageStationJTabbledPane.addTab("Update Station", UpdateStationJPanel);

        btnBack.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnBack.setText("<< Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(manageStationJTabbledPane, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(423, 423, 423)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(manageStationJTabbledPane, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(btnBack)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private boolean bStationNameCheck = false;
    private void txtStationNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStationNameFocusLost
        // TODO add your handling code here:
        if(txtStationName.getText().trim().length() ==0){
            bStationNameCheck = false;
            lblErrMsgStation.setText("Station Name cannot be blank.");
        }
        else{
            boolean matched = txtStationName.getText().matches("^[\\w]+[\\w\\s]+$");
              
            if(matched){
                bStationNameCheck = true;
                lblErrMsgStation.setText("");
            }
            else{
                lblErrMsgStation.setText("Station Name can only have letters, numbers or space.");
                bStationNameCheck = false; 
            }
        
        }

    }//GEN-LAST:event_txtStationNameFocusLost

    private void btnSubmitStationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitStationActionPerformed
        // TODO add your handling code here:

        if (bStationNameCheck){
                     
            Station s =  new Station(txtStationName.getText());
                    
            if (chkActiveFlag.isSelected()){
                s.setActiveFlag(true);
            }
            
           
            if(rbtnYes.isSelected()){
               s.setMultiLineStation(true);
              
            }            
            else if (rbtnNo.isSelected()){                
                s.setMultiLineStation(false);
                              
            }
            
            String p = cboStationPopularity.getSelectedItem().toString(); 
            if(p.equals(Station.Popularity.HIGH_RIDERSHIP.getValue())){
                s.setStationPopularity(Station.Popularity.HIGH_RIDERSHIP);            
            }
            else {
                s.setStationPopularity(Station.Popularity.LOW_RIDERSHIP); 
            }
            
            
            trainAuthorityEnterprise.getStationCatalog().addStation(s);// .addStation(txtStationName.getText());
        
            resetFields();
            
            populateTable();
        }
        else {
            JOptionPane.showMessageDialog(this, "Please enter station name.", "Manage Station", JOptionPane.WARNING_MESSAGE);
          
        }
    }//GEN-LAST:event_btnSubmitStationActionPerformed

    private void btnUpdateStationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateStationActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblStations.getSelectedRow();
        
        if (selectedRow < 0){
            JOptionPane.showMessageDialog(this, "Please select a row!", "Manage Station", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Station station;    
        station = trainAuthorityEnterprise.getStationCatalog().getStation(((Station)tblStations.getValueAt(selectedRow, 1)).getStationName());
        
        station.setActiveFlag((Boolean)(tblStations.getValueAt(selectedRow, 2)));
        station.setMultiLineStation((Boolean)(tblStations.getValueAt(selectedRow, 3)));
        
        String p = cboStationPopularity.getSelectedItem().toString(); 
        if(p.equals(Station.Popularity.HIGH_RIDERSHIP.getValue())){
            station.setStationPopularity(Station.Popularity.HIGH_RIDERSHIP);            
        }
        else {
            station.setStationPopularity(Station.Popularity.LOW_RIDERSHIP); 
        }
            
        populateTable();
        
        JOptionPane.showMessageDialog(this, "Updated successfully!", "Manage Station", JOptionPane.INFORMATION_MESSAGE);
        
    }//GEN-LAST:event_btnUpdateStationActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);     
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_btnBackActionPerformed

    
    public void resetFields(){
        txtStationName.setText("");
        chkActiveFlag.setSelected(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CreateStationJPanel;
    private javax.swing.JPanel UpdateStationJPanel;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnSubmitStation;
    private javax.swing.JButton btnUpdateStation;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cboPopularity;
    private javax.swing.JComboBox cboStationPopularity;
    private javax.swing.JCheckBox chkActiveFlag;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblErrMsgStation;
    private javax.swing.JTabbedPane manageStationJTabbledPane;
    private javax.swing.JRadioButton rbtnNo;
    private javax.swing.JRadioButton rbtnYes;
    private javax.swing.JTable tblStations;
    private javax.swing.JTextField txtStationName;
    // End of variables declaration//GEN-END:variables
}
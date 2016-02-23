/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.TMSupervisorRole;

import Business.Enterprise.TrainAuthorityEnterprise;
import Business.TrainManagementSystem.TrainStatus;
import Business.TrainManagementSystem.Train;
import Business.TrainManagementSystem.TrainCar;
import Business.TrainManagementSystem.TrainOffered;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Manasi Laddha
 */
public class ViewRunningTrainsJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ViewRunningTrainsJPanel
     */
    JPanel userProcessContainer;
    TrainAuthorityEnterprise enterprise;
    
    public ViewRunningTrainsJPanel(JPanel userProcessContainer, TrainAuthorityEnterprise enterprise) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.enterprise = enterprise;
        
        populateTrains();
        
        tblTrainCapacityDetails.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN,18));
        tblTrainRunningDetails.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN,18));
    }
    
     private void populateTrains(){
        cboTrains.removeAllItems();

        for(Train t: enterprise.getTrainCatalog().getTrainsList()){
           if(t.getLineOnTrain().isIsActive() && t.getLineOnTrain().isHasStarted()){
                cboTrains.addItem(t);
            }
         }
        
        if(cboTrains.getItemCount() <=0){
            btnGetStatus.setEnabled(false);
            cboTrains.setEnabled(false);
        }
        else{
            btnGetStatus.setEnabled(true);
            cboTrains.setEnabled(true);
        }
    }
        
private void populateTable(){

     Train selectedTrain = (Train) cboTrains.getSelectedItem();
     TrainOffered selectedTrainOffered = enterprise.getTrainsOfferedHistory().fetchTrainOffered(selectedTrain, new Date());
     
     if(selectedTrainOffered == null){
         JOptionPane.showMessageDialog(this,"Train not offered for today.", "Running Schedule", JOptionPane.WARNING_MESSAGE);
         return;
     }
      
//    DefaultTableModel dtm = new DefaultTableModel();
//    tblTrainRunningDetails.setModel(dtm);    
//    dtm.setRowCount(0);
//    dtm.setColumnCount(0);
//    
//    TableColumnModel model = new DefaultTableColumnModel();           
//    TableColumn col1 = new TableColumn(dtm.getColumnCount());
//    col1.setHeaderValue("Route");
//    model.addColumn(col1);
//    
//    TableColumn col2 = new TableColumn(dtm.getColumnCount());
//    col2.setHeaderValue("Schedule");
//    model.addColumn(col2);
//     
//    tblTrainRunningDetails.setColumnModel(model);
//    dtm = (DefaultTableModel) tblTrainRunningDetails.getModel();
//    
//    
//    Vector columnNames = new Vector();
//    columnNames.add("Route");
//    columnNames.add("Schedule");
    
//    DefaultTableModel newModel = new DefaultTableModel(0,0);
////    String header[] = new String[] { "Route", "Schedule"};
////    newModel.setColumnIdentifiers(header);
//    
//    tblTrainRunningDetails.setModel(newModel);
//    
//     newModel.addColumn("Route", new Object[] { "v3" });
//     newModel.addColumn("Schedule",new Object[] { "v3" });
//    for(TrainStatus rt: selectedTrainOffered.getRunningTrains()){ 
//         newModel.addRow(new Object[]{rt.getTrainSchedule().getRoute(),rt.getTrainSchedule().getTimeSlot().getValue()});
//         newModel.fireTableDataChanged();
//     } 
//     dtm.addColumn("Route");
//     dtm.addColumn("Schedule");
//     for(TrainCar tc: selectedTrain.getTrainCars()){
//        dtm.addColumn("Actual Capacity " + tc.getCarNumber());
//     }
//     dtm.addColumn("Running Capacity");
//     dtm.addColumn("Extra Travellers");
//     dtm.addColumn("Status");
//     
     
     DefaultTableModel dtm = (DefaultTableModel) tblTrainRunningDetails.getModel();
     dtm.setRowCount(0);
     for(TrainStatus rt: selectedTrainOffered.getRunningTrains()){ 
         
          Object[] row = new Object[dtm.getColumnCount()];
          
                row[0] = rt.getTrainSchedule().getRoute();
                row[1] = rt.getTrainSchedule().getTimeSlot().getValue();
                row[2] = rt.getRunningCapacity();
                int diff= selectedTrain.getTrainCapacity() - rt.getRunningCapacity();
                if (diff  < 0){
                    row[3] = (-diff);             
                }
                else {
                    row[3] = 0;  
                }
                
                int count =  selectedTrain.getTrainCars().size();
                int j = 4;
                for(int i = 0 ; i< count ; i++){
                    row[j] = Integer.toString(rt.getTrainCars().get(i).getMaximumCapacity());     
                    j++;
                }
                
                int d = dtm.getColumnCount() - j -1;
                for(int i =0 ; i<d;i++){
                    row[j] = "NA";
                    j++;
                }
             
                row[j] = rt.getStatus();
                
          dtm.addRow(row);
        
        }
  

     
     }
     
    void populateTable(Train t){
        
        DefaultTableModel dtm = (DefaultTableModel) tblTrainCapacityDetails.getModel();
        dtm.setRowCount(0);

                   Object row[] = new Object[7];
                   row[0] = t.getTrainCapacity();
                   row[1] = t.getTrainCars().size();
                   row[2] = t.getTrainCars().get(0).getNormalSeatCapacity();
                   row[3] = t.getTrainCars().get(0).getReservedSeats();
                   row[4] = t.getTrainCars().get(0).getPackagedCapacity();
                   row[5] = findRestRoomAvailable(t);
                   row[6] =findBicycleAvailable(t);
                  dtm.addRow(row);
           
    }
    
      private int findRestRoomAvailable(Train t){
        int i =0;
        for(TrainCar tc: t.getTrainCars()){
            if(tc.isIsRestroomPresent()){
                i++;
            }
        }
        return i;
        
    }
    
    private int findBicycleAvailable(Train t){
        int i =0;
        for(TrainCar tc: t.getTrainCars()){
            if(tc.isIsBicyclesSkisAllowed()){
                i++;
            }
        }
        return i;
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTrainRunningDetails = new javax.swing.JTable(){

            public Component prepareRenderer(TableCellRenderer renderer, int row, int col)
            {
                Component c = super.prepareRenderer(renderer, row, col);
                try {

                    String status = tblTrainRunningDetails.getValueAt(row, (tblTrainRunningDetails.getColumnCount()-1)).toString();

                    if (status.startsWith("OVER")) {
                        c.setBackground(Color.RED);
                        c.setForeground(Color.WHITE);
                    } else {
                        c.setBackground(super.getBackground());
                        c.setForeground(super.getForeground());
                    }
                }
                catch(Exception e){

                }
                return c;
            }

        };
        jLabel16 = new javax.swing.JLabel();
        cboTrains = new javax.swing.JComboBox();
        btnGetStatus = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTrainCapacityDetails = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/runningT.png"))); // NOI18N
        jLabel1.setText("Running Train Status");

        tblTrainRunningDetails.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblTrainRunningDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Route", "Schedule", "Running Capacity", "Extra Travelers", "Car1 Volume", "Car2 Volume", "Car3 Volume", "Car4 Volume", "Car5 Volume", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTrainRunningDetails.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblTrainRunningDetails);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Train Details >>");

        cboTrains.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnGetStatus.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnGetStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/train.png"))); // NOI18N
        btnGetStatus.setText("Get Train Running Status >>");
        btnGetStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetStatusActionPerformed(evt);
            }
        });

        tblTrainCapacityDetails.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblTrainCapacityDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Maximum Capacity", "Train Cars", "Normal Seats Capacity", "Reserved Seats Capacity", "Standing Space Capacity", "Restroom Available", "Bicycle Space Available"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTrainCapacityDetails.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblTrainCapacityDetails);

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("Train :");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setText("Running Details >>");

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel17)
                                .addGap(29, 29, 29)
                                .addComponent(cboTrains, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(btnGetStatus))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel16)))
                        .addGap(0, 855, Short.MAX_VALUE)))
                .addContainerGap(50, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(btnBack)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(450, 450, 450)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGetStatus)
                    .addComponent(cboTrains, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(153, 153, 153)
                .addComponent(btnBack)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnGetStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetStatusActionPerformed
        // TODO add your handling code here:
        populateTable();
        populateTable((Train) cboTrains.getSelectedItem());

    }//GEN-LAST:event_btnGetStatusActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_btnBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnGetStatus;
    private javax.swing.JComboBox cboTrains;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblTrainCapacityDetails;
    private javax.swing.JTable tblTrainRunningDetails;
    // End of variables declaration//GEN-END:variables
}

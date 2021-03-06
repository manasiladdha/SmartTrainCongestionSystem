/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.TMSupervisorRole;

import Business.Enterprise.TrainAuthorityEnterprise;
import Business.TrainManagementSystem.Line;
import Business.TrainManagementSystem.Segment;
import Business.TrainManagementSystem.Station;
import java.awt.CardLayout;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Manasi Laddha
 */


public class ManageLinesJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ManageLinesJPanel
     */
    JPanel userProcessContainer;
    TrainAuthorityEnterprise enterprise;
    
    ArrayList<Station> temporaryStationList;
    
    public ManageLinesJPanel(JPanel userProcessContainer,TrainAuthorityEnterprise enterprise) {
        initComponents();
        this.userProcessContainer  = userProcessContainer;
        this.enterprise = enterprise;
        temporaryStationList = new ArrayList<>();              
        
        populateNewLinesComboBox();
        populateExtendingLinesComboBox();
        populateStartStationComboBox();
        populateActiveLines();
        lblErrMsgLine.setText("");
        
    }
    
    private void populateNewLinesComboBox(){
        cboNewLines.removeAllItems();      
       // cboNewLinesSchedules.removeAllItems();
          for(Line l : enterprise.getLineCatalog().getLinesList()){
            if(l.getStartStation() == null || l.getEndStation() == null){
                cboNewLines.addItem(l);  
              //  cboNewLinesSchedules.addItem(l);  
            }
           
        }    
         
        if(cboNewLines.getItemCount() <= 0){        
            cboNewLines.setEnabled(false);
            cboStartStations.setEnabled(false);
            btnAddStartStation.setEnabled(false);            
        }  
        else {
            cboNewLines.setEnabled(true);
            cboStartStations.setEnabled(true);
            btnAddStartStation.setEnabled(true);    
        }
        
    }
    
    
    private void populateExtendingLinesComboBox(){
        cboExtendingLines.removeAllItems();
        
        for(Line l : enterprise.getLineCatalog().getLinesList()){
            if(l.getStartStation() == null || l.getEndStation() == null){
                
            }
            else {
               if(!l.isIsActive())
                cboExtendingLines.addItem(l);
            }
        }   
        
        if(cboExtendingLines.getItemCount() <=0){
            cboExtendingLines.setEnabled(false);            
            btnGetSegments.setEnabled(false);          
        }
        else{
            cboExtendingLines.setEnabled(true);          
            btnGetSegments.setEnabled(true);          
        }
        
    }
    
    private void  populateActiveLines(){
         cboActiveLines.removeAllItems();
        
        for(Line l : enterprise.getLineCatalog().getLinesList()){
            if(l.getStartStation() == null || l.getEndStation() == null){
                
            }
            else {
               if(l.isIsActive())
                cboActiveLines.addItem(l);
            }
        }   
        
        if(cboActiveLines.getItemCount() <=0){
            cboActiveLines.setEnabled(false);
            btnGetSegmentsActiveLine.setEnabled(false);
        }
        else{
            cboActiveLines.setEnabled(true);
            btnGetSegmentsActiveLine.setEnabled(true);
            
            
        }
        
    }
    
    private void populateStartStationComboBox(){
        
        cboStartStations.removeAllItems();
        
        for(Station s : enterprise.getStationCatalog().getStationsList()){
            
           if (s.getLinesOnStation().isEmpty()){
                cboStartStations.addItem(s);
           }
           
           else {
                if(s.isMultiLineStation()){
                      cboStartStations.addItem(s);
                }
           }
                    
        }
       
    }
    
    public void populateEndStationComboBox(){        
        cboEndStations.removeAllItems();
        
        for(Station s : enterprise.getStationCatalog().getStationsList()){
          if (!s.equals((Station) cboStartStations.getSelectedItem()))
           if (s.getLinesOnStation().isEmpty() ){
                cboEndStations.addItem(s);
           }           
           else {
                if(s.isMultiLineStation()){                
                    cboEndStations.addItem(s);
                }
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        lblHeading = new javax.swing.JLabel();
        manageLinesJTabbledPane = new javax.swing.JTabbedPane();
        CreateLineJPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtLineName = new javax.swing.JTextField();
        btnCreateLine = new javax.swing.JButton();
        lblErrMsgLine = new javax.swing.JLabel();
        UpdateLineJPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cboStartStations = new javax.swing.JComboBox();
        btnAddStartStation = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cboEndStations = new javax.swing.JComboBox();
        btnAddEndStation = new javax.swing.JButton();
        cboNewLines = new javax.swing.JComboBox();
        ExtendLineJPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        cboExtendingLines = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblStationsOnLine = new javax.swing.JTable();
        btnGetSegments = new javax.swing.JButton();
        cboExtendedStations = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        btnExtendSegment = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        cboActiveLines = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        btnGetSegmentsActiveLine = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblStationsOnActiveLine = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        lblHeading.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblHeading.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/T.png"))); // NOI18N
        lblHeading.setText("Manage T-Lines");

        manageLinesJTabbledPane.setBackground(new java.awt.Color(255, 255, 255));
        manageLinesJTabbledPane.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Create New Line");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Line Name :");

        txtLineName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtLineName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLineNameFocusLost(evt);
            }
        });

        btnCreateLine.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCreateLine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/line.png"))); // NOI18N
        btnCreateLine.setText("Create Line");
        btnCreateLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateLineActionPerformed(evt);
            }
        });

        lblErrMsgLine.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblErrMsgLine.setForeground(java.awt.Color.red);
        lblErrMsgLine.setText("ErrMsg");

        javax.swing.GroupLayout CreateLineJPanelLayout = new javax.swing.GroupLayout(CreateLineJPanel);
        CreateLineJPanel.setLayout(CreateLineJPanelLayout);
        CreateLineJPanelLayout.setHorizontalGroup(
            CreateLineJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CreateLineJPanelLayout.createSequentialGroup()
                .addGroup(CreateLineJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CreateLineJPanelLayout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jLabel2)
                        .addGap(28, 28, 28)
                        .addGroup(CreateLineJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCreateLine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtLineName, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
                        .addGap(41, 41, 41)
                        .addComponent(lblErrMsgLine))
                    .addGroup(CreateLineJPanelLayout.createSequentialGroup()
                        .addGap(293, 293, 293)
                        .addComponent(jLabel1)))
                .addGap(624, 624, 624))
        );
        CreateLineJPanelLayout.setVerticalGroup(
            CreateLineJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CreateLineJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(37, 37, 37)
                .addGroup(CreateLineJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtLineName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblErrMsgLine))
                .addGap(29, 29, 29)
                .addComponent(btnCreateLine, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(459, Short.MAX_VALUE))
        );

        manageLinesJTabbledPane.addTab("Create Line", CreateLineJPanel);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Line Name :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Line Start Station :");

        cboStartStations.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnAddStartStation.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnAddStartStation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/train.png"))); // NOI18N
        btnAddStartStation.setText("Set Start Station >>");
        btnAddStartStation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddStartStationActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Line End Station :");

        cboEndStations.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cboEndStations.setEnabled(false);

        btnAddEndStation.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnAddEndStation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/train.png"))); // NOI18N
        btnAddEndStation.setText("Set End Station >>");
        btnAddEndStation.setEnabled(false);
        btnAddEndStation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddEndStationActionPerformed(evt);
            }
        });

        cboNewLines.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout UpdateLineJPanelLayout = new javax.swing.GroupLayout(UpdateLineJPanel);
        UpdateLineJPanel.setLayout(UpdateLineJPanelLayout);
        UpdateLineJPanelLayout.setHorizontalGroup(
            UpdateLineJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UpdateLineJPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(UpdateLineJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UpdateLineJPanelLayout.createSequentialGroup()
                        .addGroup(UpdateLineJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(UpdateLineJPanelLayout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jLabel4)))
                        .addGap(38, 38, 38)
                        .addGroup(UpdateLineJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboStartStations, 0, 217, Short.MAX_VALUE)
                            .addComponent(cboNewLines, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(UpdateLineJPanelLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel8)
                        .addGap(38, 38, 38)
                        .addGroup(UpdateLineJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAddEndStation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAddStartStation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboEndStations, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        UpdateLineJPanelLayout.setVerticalGroup(
            UpdateLineJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UpdateLineJPanelLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(UpdateLineJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cboNewLines, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(UpdateLineJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UpdateLineJPanelLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel5))
                    .addComponent(cboStartStations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(btnAddStartStation, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(UpdateLineJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UpdateLineJPanelLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel8))
                    .addComponent(cboEndStations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(btnAddEndStation, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(331, Short.MAX_VALUE))
        );

        manageLinesJTabbledPane.addTab("Add Start & End Stations on Line", UpdateLineJPanel);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Line Name :");

        cboExtendingLines.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cboExtendingLines.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboExtendingLinesActionPerformed(evt);
            }
        });

        tblStationsOnLine.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblStationsOnLine.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Start Station", "End Station"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblStationsOnLine.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblStationsOnLine);

        btnGetSegments.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnGetSegments.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/segment.png"))); // NOI18N
        btnGetSegments.setText("Get Segments");
        btnGetSegments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetSegmentsActionPerformed(evt);
            }
        });

        cboExtendedStations.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cboExtendedStations.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Next Station:");

        btnExtendSegment.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnExtendSegment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/segment.png"))); // NOI18N
        btnExtendSegment.setText("Extend Segment");
        btnExtendSegment.setEnabled(false);
        btnExtendSegment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExtendSegmentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ExtendLineJPanelLayout = new javax.swing.GroupLayout(ExtendLineJPanel);
        ExtendLineJPanel.setLayout(ExtendLineJPanelLayout);
        ExtendLineJPanelLayout.setHorizontalGroup(
            ExtendLineJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ExtendLineJPanelLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(ExtendLineJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ExtendLineJPanelLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboExtendedStations, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExtendSegment))
                    .addComponent(jScrollPane3)
                    .addGroup(ExtendLineJPanelLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(cboExtendingLines, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 667, Short.MAX_VALUE)
                        .addComponent(btnGetSegments, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        ExtendLineJPanelLayout.setVerticalGroup(
            ExtendLineJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ExtendLineJPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(ExtendLineJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cboExtendingLines, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGetSegments, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(ExtendLineJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cboExtendedStations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExtendSegment, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(313, Short.MAX_VALUE))
        );

        manageLinesJTabbledPane.addTab("Extend Line", ExtendLineJPanel);

        cboActiveLines.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Line Name :");

        btnGetSegmentsActiveLine.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnGetSegmentsActiveLine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/segment.png"))); // NOI18N
        btnGetSegmentsActiveLine.setText("Get Segments");
        btnGetSegmentsActiveLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetSegmentsActiveLineActionPerformed(evt);
            }
        });

        tblStationsOnActiveLine.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblStationsOnActiveLine.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Start Station", "End Station"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblStationsOnActiveLine.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tblStationsOnActiveLine);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(cboActiveLines, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 667, Short.MAX_VALUE)
                        .addComponent(btnGetSegmentsActiveLine, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(67, 67, 67))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cboActiveLines, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGetSegmentsActiveLine, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(389, Short.MAX_VALUE))
        );

        manageLinesJTabbledPane.addTab("View Existing Active Lines", jPanel1);

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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(manageLinesJTabbledPane))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHeading, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(495, 495, 495))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHeading, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(manageLinesJTabbledPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBack)
                .addGap(9, 9, 9))
        );
    }// </editor-fold>//GEN-END:initComponents

    boolean bLineNameCheck = false;
    
    private void btnAddStartStationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddStartStationActionPerformed
        // TODO add your handling code here:        
        Line line = enterprise.getLineCatalog().getExistingLine(((Line) cboNewLines.getSelectedItem()).getLineName());
        line.setStartStation((Station) cboStartStations.getSelectedItem());  
         
        //((Station) cboStartStations.getSelectedItem()).addLineOnStation(line);
        
        cboEndStations.setEnabled(true);
        btnAddEndStation.setEnabled(true); 
        
        cboStartStations.setEnabled(false);
        btnAddStartStation.setEnabled(false);
        
        populateEndStationComboBox();
        
    }//GEN-LAST:event_btnAddStartStationActionPerformed

    private void btnAddEndStationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddEndStationActionPerformed
        // TODO add your handling code here:
        
        Line line = enterprise.getLineCatalog().getExistingLine(((Line) cboNewLines.getSelectedItem()).getLineName());
        Station startStation = (Station) cboStartStations.getSelectedItem();
        Station endStation  = (Station) cboEndStations.getSelectedItem();
        
        for(Line l: enterprise.getLineCatalog().getLinesList()){
            for(Segment s : l.getSegments()){
                if (s.getEndStation1() == startStation &&  s.getEndStation2() == endStation){
                    JOptionPane.showMessageDialog(this, "The Line :" + l.getLineName() + " already exists from Station :" + startStation + " to Station :" + endStation, "Create Line", JOptionPane.WARNING_MESSAGE);
                    cboEndStations.setEnabled(false);
                    btnAddEndStation.setEnabled(false); 
                    cboStartStations.setEnabled(true);
                    btnAddStartStation.setEnabled(true);                    
                    return;     
                    
                }                
            }            
        }
        
        line.setEndStation((Station) cboEndStations.getSelectedItem());                  
        Segment seg = new Segment(line.getStartStation(), line.getEndStation());        
        line.getSegments().add(seg);
        
        cboEndStations.setEnabled(false);
        btnAddEndStation.setEnabled(false); 
        
         populateNewLinesComboBox();
         
         if (cboNewLines.getItemCount() > 0 ){
            cboStartStations.setEnabled(true);
            btnAddStartStation.setEnabled(true);
         }
        
        
        populateExtendingLinesComboBox();
        
    }//GEN-LAST:event_btnAddEndStationActionPerformed

    
    
    private void btnCreateLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateLineActionPerformed
        // TODO add your handling code here:

        if (bLineNameCheck){
            lblErrMsgLine.setText("");
            //Line newLine = new Line(txtLineName.getText());
            //add this new Line
           Line newLine = enterprise.getLineCatalog().addLine(txtLineName.getText());
           newLine.setIsActive(false);
            txtLineName.setText("");
            
            populateNewLinesComboBox();
            populateExtendingLinesComboBox();
            
            JOptionPane.showMessageDialog(this, "Line created successfully.", "Create Line", JOptionPane.INFORMATION_MESSAGE);
            
        }
        else{
            lblErrMsgLine.setText("Line Name cannot be blank.");
        }
        
       
    }//GEN-LAST:event_btnCreateLineActionPerformed

    private void txtLineNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLineNameFocusLost
        // TODO add your handling code here:
        if(txtLineName.getText().trim().length() == 0){
            bLineNameCheck = false;
            lblErrMsgLine.setText("Line Name cannot be blank.");
            //btnCreateLine.setEnabled(bLineNameCheck);
        }
        else{
       
            boolean matched = txtLineName.getText().matches("^[\\w]+[\\w\\s]+$");
            
            if(matched){            
                lblErrMsgLine.setText("");
                bLineNameCheck = true;
            }
            else {
                lblErrMsgLine.setText("Line Name can only have letters, numbers or space.");
                bLineNameCheck = false;   
            }
        }
    }//GEN-LAST:event_txtLineNameFocusLost

    private void btnGetSegmentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetSegmentsActionPerformed
        // TODO add your handling code here:
        
     Line selectedline = enterprise.getLineCatalog().getExistingLine(((Line) cboExtendingLines.getSelectedItem()).getLineName());
   
     populateTable(selectedline);
     populateExtendedComboBox(selectedline);
     
    }//GEN-LAST:event_btnGetSegmentsActionPerformed

    private void populateTable(Line line){
   
     DefaultTableModel dtm = (DefaultTableModel) tblStationsOnLine.getModel();
        dtm.setRowCount(0);
        for (Segment seg : line.getSegments()){
             Object[] row = new Object[2];
             row[0] = seg.getEndStation1();
             row[1] = seg.getEndStation2();
             dtm.addRow(row);
        }
    }
    
    private void btnExtendSegmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExtendSegmentActionPerformed
        // TODO add your handling code here:
     Line line = enterprise.getLineCatalog().getExistingLine(((Line) cboExtendingLines.getSelectedItem()).getLineName());
 
     Station startingPoint = line.getEndStation();
     Station nextStation = (Station) cboExtendedStations.getSelectedItem();
     
     if(line.getEndStation() == nextStation){
           JOptionPane.showMessageDialog(this, "Next Station cannot be same as last segment :" + startingPoint + " to " + nextStation, "Create Line", JOptionPane.WARNING_MESSAGE);
            return; 
     }
     
     
     for(Line l: enterprise.getLineCatalog().getLinesList()){
            for(Segment s : l.getSegments()){
                if (s.getEndStation1() == startingPoint &&  s.getEndStation2() == nextStation){
                    JOptionPane.showMessageDialog(this, "The Line :" + l.getLineName() + " already exists from Station :" + startingPoint + " to Station :" + nextStation, "Create Line", JOptionPane.WARNING_MESSAGE);
                    return;                    
                }                  
            }            
        }
     
     for(Segment seg : line.getSegments()){
         if((seg.getEndStation1() == startingPoint && seg.getEndStation2() == nextStation) || (seg.getEndStation2()==startingPoint && seg.getEndStation1() == nextStation)){
            JOptionPane.showMessageDialog(this, "Segment already exists :" + nextStation + " to " + startingPoint, "Create Line", JOptionPane.WARNING_MESSAGE);
            return; 
         }
     }
     
     line.extendSegment(nextStation);     
     populateTable(line);
     populateExtendedComboBox(line);
     
     JOptionPane.showMessageDialog(this, "Segment Extended!", "Create Line", JOptionPane.INFORMATION_MESSAGE);
     
    }//GEN-LAST:event_btnExtendSegmentActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnGetSegmentsActiveLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetSegmentsActiveLineActionPerformed
        // TODO add your handling code here:
        Line selected =(Line) cboActiveLines.getSelectedItem();
        if(selected == null)
            return;
        populateActiveTable(selected);
        
    }//GEN-LAST:event_btnGetSegmentsActiveLineActionPerformed

    private void cboExtendingLinesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboExtendingLinesActionPerformed
        // TODO add your handling code here:
        btnExtendSegment.setEnabled(false);
        cboExtendedStations.setEnabled(false);
    }//GEN-LAST:event_cboExtendingLinesActionPerformed

    
    private void populateActiveTable(Line line){
   
     DefaultTableModel dtm = (DefaultTableModel) tblStationsOnActiveLine.getModel();
        dtm.setRowCount(0);
        for (Segment seg : line.getSegments()){
             Object[] row = new Object[2];
             row[0] = seg.getEndStation1();
             row[1] = seg.getEndStation2();
             dtm.addRow(row);
        }
    }
    
    
    
    
    private void populateExtendedComboBox(Line selectedline){
        
        cboExtendedStations.removeAllItems();
        
        for(Station s : enterprise.getStationCatalog().getStationsList()){          
            if (!s.checkIfStationContainsLine(selectedline)){
                if (s.isMultiLineStation()){
                   cboExtendedStations.addItem(s); 
                }
            }

        }   
        
        if(cboExtendedStations.getItemCount() <=0){
            cboExtendedStations.setEnabled(false);
            btnExtendSegment.setEnabled(false);
        }
        else{
            cboExtendedStations.setEnabled(true);
            btnExtendSegment.setEnabled(true);
        }
        
         
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CreateLineJPanel;
    private javax.swing.JPanel ExtendLineJPanel;
    private javax.swing.JPanel UpdateLineJPanel;
    private javax.swing.JButton btnAddEndStation;
    private javax.swing.JButton btnAddStartStation;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCreateLine;
    private javax.swing.JButton btnExtendSegment;
    private javax.swing.JButton btnGetSegments;
    private javax.swing.JButton btnGetSegmentsActiveLine;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cboActiveLines;
    private javax.swing.JComboBox cboEndStations;
    private javax.swing.JComboBox cboExtendedStations;
    private javax.swing.JComboBox cboExtendingLines;
    private javax.swing.JComboBox cboNewLines;
    private javax.swing.JComboBox cboStartStations;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblErrMsgLine;
    private javax.swing.JLabel lblHeading;
    private javax.swing.JTabbedPane manageLinesJTabbledPane;
    private javax.swing.JTable tblStationsOnActiveLine;
    private javax.swing.JTable tblStationsOnLine;
    private javax.swing.JTextField txtLineName;
    // End of variables declaration//GEN-END:variables
}

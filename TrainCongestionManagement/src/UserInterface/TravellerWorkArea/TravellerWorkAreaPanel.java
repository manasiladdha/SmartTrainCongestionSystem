/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.TravellerWorkArea;

import Business.Enterprise.Enterprise;
import Business.Enterprise.SecurityAuthorityEnterprise;
import Business.Enterprise.TrainAuthorityEnterprise;
import Business.Organization.Organization;
import Business.Organization.TravellerOrganization;
import Business.TrainManagementSystem.Line;
import Business.TrainManagementSystem.Route;
import Business.TrainManagementSystem.RouteSchedule;
import Business.TrainManagementSystem.TrainStatus;
import Business.TrainManagementSystem.Segment;
import Business.TrainManagementSystem.SimulationClass;
import Business.TrainManagementSystem.Station;
import Business.TrainManagementSystem.Train;
import Business.TrainManagementSystem.TrainCar;
import Business.TrainManagementSystem.TrainOffered;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.AlertWorkRequest;
import Business.WorkQueue.IncidentWorkRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Manasi Laddha
 */
class TextAndIcon {
TextAndIcon(String text, Icon icon) {
    this.text = text;
    this.icon = icon;
  }

  String text;
  Icon icon;
}

class IconHeaderRenderer extends DefaultTableCellRenderer {
  @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
    boolean hasFocus, int row, int column) {
    if (table != null) {
    JTableHeader header = table.getTableHeader();
      if (header != null) {
        setForeground(header.getForeground());
        setBackground(header.getBackground());
        setFont(header.getFont());
      }
    }
    if (value instanceof TextAndIcon) {
      setIcon(((TextAndIcon) value).icon);
      setText(((TextAndIcon) value).text);
    } else {
      setText((value == null) ? "" : value.toString());
      setIcon(null);
    }
    //setBorder(UIManager.getBorder("TableHeader.cellBorder"));
    setHorizontalAlignment(JLabel.CENTER);
    return this;
  }
}


public class TravellerWorkAreaPanel extends javax.swing.JPanel {

    /**
     * Creates new form TravellerWorkAreaPanel
     */
    JPanel userProcessContainer;
    TrainAuthorityEnterprise enterprise;
    UserAccount account;
    SecurityAuthorityEnterprise sae;       
    TrainStatus rt;
    ArrayList<Segment> selectedSegments ;
    Route selectedRoute;
    SimulationClass simulateData;
    
    public TravellerWorkAreaPanel(JPanel userProcessContainer, Enterprise enterprise,SecurityAuthorityEnterprise sae, UserAccount account) {
        initComponents();
        this.enterprise = (TrainAuthorityEnterprise) enterprise;
        this.account = account;
        simulateData = new SimulationClass();
        this.sae = sae;
       
        populateStations();      
        populateAllLines();
        
        rbtnMedical.setSelected(true);
        
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE, MMM d, yyyy");  
        SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm a");  
        txtTodayDate.setText(dateFormatter.format(new Date()));
        txtCurrentTime.setText(timeFormatter.format(new Date()));
               
        //****** STYLING *******// 
        tblAlertsTrains.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 18));
        tblTrainDetails.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 18));  
    
               
    }
    
    private void populateRoutes(){
        
        Line slectedLine = (Line) cboIncidentLines.getSelectedItem();
        cboIncidentRoutes.removeAllItems();
        for(Segment seg : slectedLine.getSegments()){
            for(Route r : seg.getRoutes()){
                cboIncidentRoutes.addItem(r);                
            }            
        }       
        
    }
    
    private void populateTimeSlots(){
        cboTimeSlots.removeAllItems();
        
       for(RouteSchedule rs : selectedRoute.getRouteSchedules()){
          if(rs.isSlotTaken()){
              cboTimeSlots.addItem(rs); 
          }           
       }
        
//        for(TimeSlot.TimeSlotRange tr : TimeSlot.TimeSlotRange.values()){
//            cboTimeSlots.addItem(tr);
//        }
        
    }
    
    private void populateStations(){        
        cboStations.removeAllItems();
        for(Station station : enterprise.getStationCatalog().getStationsList()){ 
          if(station.getLinesOnStation().size()> 0) {   //check the lines          
            if(checkLines(station))
              cboStations.addItem(station);         
            }
        }
        
        if(cboStations.getItemCount() <=0){
            cboEndStations.setEnabled(false);
            cboLines.setEnabled(false);
            btnFindRoute.setEnabled(false);
            btnCalculateCrowdedness.setEnabled(false);
            cboTimeSlots.setEnabled(false);
        }
        else {
            cboEndStations.setEnabled(true);
            cboLines.setEnabled(true);
            btnFindRoute.setEnabled(true);
            btnCalculateCrowdedness.setEnabled(true);
            cboTimeSlots.setEnabled(true);
        }
    }
    
    private boolean checkLines(Station s){
        for(Line l : s.getLinesOnStation()){
            if(l.isHasStarted() && l.isIsActive()){
                return true;
            }
        }
        return  false;
    }
    
     private String calculatePercentage(int upperLimit, int lowerLimt){
        
            String resultedString ="";

            int diff = upperLimit - lowerLimt ;

            int result = (diff * 100)/ lowerLimt ;

            if(result <= 10){
                resultedString ="Managable";
            }
            else {
                resultedString ="Un-Managable";
            }
            return resultedString;
        
    }
    
    private void populateAlerts(){
        
        DefaultTableModel dtm = (DefaultTableModel) tblAlertsTrains.getModel();
        dtm.setRowCount(0);
        
        Line selectedLine = (Line) cboAlertLines.getSelectedItem();
        
        for(Organization org :enterprise.getOrganizationDirectory().getOrganizationList()){
            if(org instanceof TravellerOrganization){
                
                for(WorkRequest request : org.getWorkQueue().getWorkRequestList()){
                   if(((AlertWorkRequest)request).getAlertDate()==null){
                       continue;
                   }
                   
                   long alertDay =    (((AlertWorkRequest)request).getAlertDate().getTime())/(1000*60*60*24);
                   long today = (new Date().getTime())/(1000*60*60*24);
                   if(selectedLine.equals(((AlertWorkRequest)request).getAlertedline()) && alertDay == today){// && ((AlertWorkRequest)request).getAlertDate().equals(new Date())){
                        Object[] row = new Object[4];
                        row[0] = ((AlertWorkRequest)request).getRouteName();
                        row[1] = ((AlertWorkRequest)request).getTimeSlot();
                        row[2] = ((AlertWorkRequest)request).getTrainName();
                        row[3] = ((AlertWorkRequest)request).getResult();   
                        dtm.addRow(row);
                   }
                }
            }
        }
    }
  
//    private void runTrain(RouteSchedule selectedSchedule){
//        
//       // TimeSlot.TimeSlotRange selectedSchedule = (TimeSlot.TimeSlotRange) cboTimeSlots.getSelectedItem();
//       // RouteSchedule selectedSchedule = (RouteSchedule) cboTimeSlots.getSelectedItem();
//        
//        String value = selectedSchedule.getTimeSlotsTrain().name().substring(8);
//        int coveredJourney = (Integer.parseInt(value)==15)? 14:Integer.parseInt(value);        
//        slotNumber = (coveredJourney * 100)/15;
//       
////        int currentIndex =  cboTimeSlots.getSelectedIndex();
////        int previousIndex;
////        
////        if (currentIndex ==0){
////         previousIndex = 0;
////        }
////        else {
////            previousIndex = currentIndex - 1;
////        }
//        
//        int currentIndex = Integer.parseInt(value);
//        currentIndex--;
//        int previousIndex;
//        
//        if (currentIndex ==0){
//         previousIndex = 0;
//        }
//        else {
//            previousIndex = currentIndex - 1;
//        }
//        
//        //Train selectedTrain = selectedRoute.getRouteSchedules().get(currentIndex).getTrainOffered();
//        Train selectedTrain = selectedSchedule.getTrainOffered();
//        
//        TrainCar actualTrainCar = selectedTrain.getTrainCars().get(0);                      
//       
//        TrainOffered trainOffered = enterprise.getTrainsOfferedHistory().fetchOrAddTrain(selectedTrain, new Date());
//
//        
//        
//        txtTrainName.setText(trainOffered.getTrain().getTrainName());
//        
//        int previousSeed = 0;
//            
//            for(TrainSchedule ts : trainOffered.getTrain().getTrainSchedule()){
//                          
//                 if(ts.getTimeSlot() == selectedSchedule.getTimeSlotsTrain()){
//                       
////                     if(ts.getTimeSlot() == TimeSlot.TimeSlotRange.TimeSlot1){
////                          rt = trainOffered.fetchRunningTrain(trainOffered.getTrain().getTrainSchedule().get(0));
////                          if(rt == null){
////                             generateCrowdedness(previousSeed,ts,trainOffered,selectedTrain,actualTrainCar);
////                          }
////                          generateNextSeed(rt, ts);
////                         break;
////                     }
//                     rt = trainOffered.fetchRunningTrain(trainOffered.getTrain().getTrainSchedule().get(previousIndex));                    
//                     if(rt == null){
//                             generateCrowdedness(previousSeed,ts,trainOffered,selectedTrain,actualTrainCar);
//                     }
//                     generateNextSeed(rt, ts);
//                     break;
//                 }
//                 
//                 if(trainOffered.fetchRunningTrain(ts) != null){                     
//                   
//                     rt = trainOffered.fetchRunningTrain(trainOffered.getTrain().getTrainSchedule().get(previousIndex));
//                  
//                 }
//                 else {
//                     
//                    previousSeed= generateCrowdedness(previousSeed, ts,trainOffered,selectedTrain,actualTrainCar);
//
//                 }
//            }
//            
//        showExpectedCrowdedness(selectedTrain);  
//      
//      }
//    

    private void showExpectedCrowdedness(Train selectedTrain){
       
        DefaultTableModel dtm = (DefaultTableModel) tblTrainDetails.getModel();       
        dtm.setRowCount(0);
        
                 tblTrainDetails.getTableHeader().getColumnModel().getColumn(5).setHeaderRenderer(new IconHeaderRenderer());

        tblTrainDetails.getColumnModel().getColumn(5).setHeaderValue(new TextAndIcon("Space", new ImageIcon("src\\Images\\bicycle.png")));
        
        tblTrainDetails.getTableHeader().getColumnModel().getColumn(4).setHeaderRenderer(new IconHeaderRenderer());

        tblTrainDetails.getColumnModel().getColumn(4).setHeaderValue(new TextAndIcon("Restroom", new ImageIcon("src\\Images\\restroom.png")));
        
        tblTrainDetails.getTableHeader().getColumnModel().getColumn(3).setHeaderRenderer(new IconHeaderRenderer());

        tblTrainDetails.getColumnModel().getColumn(3).setHeaderValue(new TextAndIcon("Empty Seats", new ImageIcon("src\\Images\\disabled.png")));
        
        tblTrainDetails.getTableHeader().getColumnModel().getColumn(2).setHeaderRenderer(new IconHeaderRenderer());

        tblTrainDetails.getColumnModel().getColumn(2).setHeaderValue(new TextAndIcon("Empty Seats", new ImageIcon("src\\Images\\sitting.png")));
        
        tblTrainDetails.getTableHeader().getColumnModel().getColumn(1).setHeaderRenderer(new IconHeaderRenderer());

        tblTrainDetails.getColumnModel().getColumn(1).setHeaderValue(new TextAndIcon("Standing Space", new ImageIcon("src\\Images\\standing.GIF")));
                
        tblTrainDetails.getTableHeader().getColumnModel().getColumn(0).setHeaderRenderer(new IconHeaderRenderer());

        tblTrainDetails.getColumnModel().getColumn(0).setHeaderValue(new TextAndIcon("Car", new ImageIcon("src\\Images\\car.png")));
       

        
        if(rt == null){
            for(int i = 0 ; i< 3 ; i++){             
             Object[] row = new Object[4];
                row[0] = "Car" + (i+1);
                row[1] = 0;
                row[2] = 0;
                row[3] = 0;
                dtm.addRow(row);
            }
           txtTrainStatus.setText("Not Started");     
//           txtExtraTravellers.setText("0");
        }
        
        else{  

            for(TrainCar tc : rt.getTrainCars()){
               Object[] row = new Object[6]; 
                row[0] = tc.getCarNumber();                
                row[1] = (tc.getReport().getEmptySpaceToStand() < 0)? 0 : tc.getReport().getEmptySpaceToStand();
                row[2] = (tc.getReport().getEmptyNormalSeats()< 0)? 0 : tc.getReport().getEmptyNormalSeats();
                row[3] = (tc.getReport().getEmptyReservedSeats()< 0)? 0 : tc.getReport().getEmptyReservedSeats();;
                row[4] = (tc.isIsRestroomPresent())? "Yes" : "No";
                row[5] = (tc.isIsBicyclesSkisAllowed())? "Yes" : "No";
                dtm.addRow(row);               
            }
            
            
            txtTrainStatus.setText(rt.getStatus());
            
            
            int predictiveValue = rt.getRunningCapacity() - rt.getPredictiveDrop() + rt.getPredictiveUp();            
            String predictiveStatus;
            predictiveStatus =  (predictiveValue > rt.getRunningCapacity() )? "COUNT RISE" : "COUNT DROP" ;               
            if(predictiveValue == rt.getRunningCapacity()){
                  predictiveStatus = "NO RISE";
            }

            txtExpectedStatus.setText(predictiveStatus); 
            txtDrop.setText(Integer.toString(rt.getPredictiveDrop()));
            txtUp.setText(Integer.toString(rt.getPredictiveUp()));
            
//            int diff= selectedTrain.getTrainCapacity() - rt.getRunningCapacity();
//            if (diff  < 0){
//               txtExtraTravellers.setText(Integer.toString(-diff));              
//            }
//            else {
//                txtExtraTravellers.setText("0");
//            }
            
            txtCrowdednessRecommendation.setText(calculatePercentage(rt.getRunningCapacity(), selectedTrain.getTrainCapacity()));           
        }
        
        
       
    }
   
    public boolean compareTime(Date dtFirst, Date dtSecond){
          boolean bReturn;
          Calendar compCalendar;
               int      iHour;
               int      iMinute;
               
               compCalendar = Calendar.getInstance();
               compCalendar.setTime(dtFirst);
               iHour   = compCalendar.get(Calendar.HOUR_OF_DAY);
               iMinute = compCalendar.get(Calendar.MINUTE);
               compCalendar.setTime(dtSecond);
         
               if((iHour < compCalendar.get(Calendar.HOUR_OF_DAY))){
                   bReturn = true;
               }
               else if(iHour == compCalendar.get(Calendar.HOUR_OF_DAY)){
                   if(iMinute <= compCalendar.get(Calendar.MINUTE)){
                       bReturn = true;
                   }
                   else {
                         bReturn = false;
                   }                   
               }
               else {
                   bReturn = false;
               }
               
//               bReturn = ((iHour <= compCalendar.get(Calendar.HOUR_OF_DAY))
//                         && (iMinute <= compCalendar.get(Calendar.MINUTE))
//                         );
               
               return bReturn;
        
    }
       
    public void populateEndStations(){
        
        cboEndStations.removeAllItems();
        
        Line l = (Line) cboLines.getSelectedItem();
        
        if(l == null){
            return;
        }
        
        Station selectedStation = (Station) cboStations.getSelectedItem();
        
        if(selectedStation == null){
            return;
        }
        
        selectedSegments = new ArrayList<>();
        for(Segment s : l.getSegments()){
           if( selectedStation.getStationName().equals(s.getEndStation1().getStationName()) || selectedStation.getStationName().equals(s.getEndStation2().getStationName())){
               selectedSegments.add(s);
           }            
        } 
        for(Segment s : selectedSegments){
           if(!s.getEndStation1().getStationName().equals(selectedStation.getStationName())){
               cboEndStations.addItem(s.getEndStation1());
           }
           else if (!s.getEndStation2().getStationName().equals(selectedStation.getStationName())){
                cboEndStations.addItem(s.getEndStation2());
           }
        }
        
    }
    
    private void populateAllLines(){
        cboAlertLines.removeAllItems();
        cboIncidentLines.removeAllItems();
 
        for(Line lines : enterprise.getLineCatalog().getLinesList()){    
            if(lines.isIsActive() && lines.isHasStarted()){
                cboAlertLines.addItem(lines);     
                cboIncidentLines.addItem(lines);  
            }
        }
        
        if(cboAlertLines.getItemCount() <=0){
             btnAskHelp.setEnabled(false);
        }
        else{
            
            btnAskHelp.setEnabled(true);
       
        }
    }

    private void populateLines(){
        cboLines.removeAllItems();
       // linesCompletelyPopulated = false;
        Station s = (Station) cboStations.getSelectedItem();
        
        for(Line lines : enterprise.getLineCatalog().getLinesList()){
            if(s.getLinesOnStation().contains(lines)){
              if(lines.isIsActive() && lines.isHasStarted()) //add only if line is active
                 cboLines.addItem(lines);
            }            
        }
       
        if(cboLines.getItemCount()<=0){
            cboLines.setEnabled(false);
            cboEndStations.setEnabled(false);
            cboTimeSlots.setEnabled(false);
            txtRouteName.setEnabled(false);
            btnCalculateCrowdedness.setEnabled(false);
            btnFindRoute.setEnabled(false);
            
        }
        else {
            cboLines.setEnabled(true);
            cboEndStations.setEnabled(true);
            cboTimeSlots.setEnabled(true);
            txtRouteName.setEnabled(true);
            btnCalculateCrowdedness.setEnabled(true);
            btnFindRoute.setEnabled(true);
            
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
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cboStations = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cboLines = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cboEndStations = new javax.swing.JComboBox();
        btnFindRoute = new javax.swing.JButton();
        cboTimeSlots = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTrainDetails = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        btnCalculateCrowdedness = new javax.swing.JButton();
        txtRouteName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTrainName = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTrainStatus = new javax.swing.JTextField();
        txtDrop = new javax.swing.JTextField();
        txtUp = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtTodayDate = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtExpectedStatus = new javax.swing.JTextField();
        txtStationPopularity = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        prgBar = new javax.swing.JProgressBar();
        jLabel22 = new javax.swing.JLabel();
        txtCrowdednessRecommendation = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblAlertsTrains = new javax.swing.JTable(){

            public Component prepareRenderer(TableCellRenderer renderer, int row, int col)
            {

                Component c = super.prepareRenderer(renderer, row, col);
                try{
                    String status = tblAlertsTrains.getValueAt(row, 3).toString();

                    if (status.startsWith("Un")) {
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
        cboAlertLines = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMessage = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cboIncidentRoutes = new javax.swing.JComboBox();
        btnAskHelp = new javax.swing.JButton();
        cboIncidentLines = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        rbtnMedical = new javax.swing.JRadioButton();
        rbtnAccidental = new javax.swing.JRadioButton();
        rbtnOther = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        rbtnSecurity = new javax.swing.JRadioButton();
        cboTrainCars = new javax.swing.JComboBox();
        jLabel21 = new javax.swing.JLabel();
        txtCurrentTime = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/traveller.PNG"))); // NOI18N
        jLabel1.setText("Traveller Work Area Panel");

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Station Start: ");

        cboStations.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cboStations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboStationsActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Station Line:");

        cboLines.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cboLines.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLinesActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Station End:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Time Slot :");

        cboEndStations.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnFindRoute.setBackground(new java.awt.Color(0, 0, 0));
        btnFindRoute.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnFindRoute.setForeground(new java.awt.Color(255, 255, 255));
        btnFindRoute.setText("Get Route >>");
        btnFindRoute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindRouteActionPerformed(evt);
            }
        });

        cboTimeSlots.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        tblTrainDetails.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblTrainDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Car Number", "Standing Space For", "Empty Seats For", "Empty Reserved Seats", "Restroom", "Space"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTrainDetails.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblTrainDetails);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Route:");

        btnCalculateCrowdedness.setBackground(new java.awt.Color(0, 0, 0));
        btnCalculateCrowdedness.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCalculateCrowdedness.setForeground(new java.awt.Color(255, 255, 255));
        btnCalculateCrowdedness.setText("Get Crowdedness >>");
        btnCalculateCrowdedness.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalculateCrowdednessActionPerformed(evt);
            }
        });

        txtRouteName.setEditable(false);
        txtRouteName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtRouteName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRouteNameActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Train Name :");

        txtTrainName.setEditable(false);
        txtTrainName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Running Status :");

        txtTrainStatus.setEditable(false);
        txtTrainStatus.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTrainStatus.setForeground(new java.awt.Color(0, 102, 51));
        txtTrainStatus.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                if(txtTrainStatus.getText().equals("OVER LIMIT")){
                    txtTrainStatus.setBackground(Color.RED);
                    txtTrainStatus.setForeground(Color.WHITE);
                }
                else if(txtTrainStatus.getText().equals("UNDER LIMIT")){
                    txtTrainStatus.setBackground(new Color(006600));
                    txtTrainStatus.setForeground(Color.WHITE);
                }
            }
        });

        txtDrop.setEditable(false);
        txtDrop.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtUp.setEditable(false);
        txtUp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Expected Status :");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("Expected Drop:");

        txtTodayDate.setEditable(false);
        txtTodayDate.setBackground(new java.awt.Color(255, 255, 255));
        txtTodayDate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setText("Today's Date:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setText("Expected Increase:");

        txtExpectedStatus.setEditable(false);
        txtExpectedStatus.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtExpectedStatus.setForeground(new java.awt.Color(0, 102, 0));
        txtExpectedStatus.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                if(txtExpectedStatus.getText().equals("COUNT DROP")){
                    txtExpectedStatus.setBackground(new Color(006600));
                    txtExpectedStatus.setForeground(Color.WHITE);
                }
                else if(txtExpectedStatus.getText().equals("COUNT RISE")){
                    txtExpectedStatus.setBackground(Color.RED);
                    txtExpectedStatus.setForeground(Color.WHITE);
                }
            }
        });

        txtStationPopularity.setEditable(false);
        txtStationPopularity.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setText("End Station Popularity:");

        prgBar.setBackground(Color.DARK_GRAY);
        prgBar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        prgBar.setForeground(new java.awt.Color(0, 0, 0));
        prgBar.setString("Journey Covered");
        prgBar.setStringPainted(true);

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel22.setText("Crowdedness:");

        txtCrowdednessRecommendation.setEditable(false);
        txtCrowdednessRecommendation.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(prgBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnFindRoute, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCalculateCrowdedness, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboStations, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboLines, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboEndStations, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cboTimeSlots, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(43, 43, 43)
                                        .addComponent(txtRouteName, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtTrainName, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtDrop, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(68, 68, 68)
                                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(65, 65, 65)
                                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCrowdednessRecommendation, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtUp, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(88, 88, 88)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel20)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(36, 36, 36))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTrainStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtExpectedStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtStationPopularity, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTodayDate, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane2))))
                .addGap(83, 83, 83))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtTodayDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStationPopularity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtExpectedStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTrainStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txtCrowdednessRecommendation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDrop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel19))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTrainName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboStations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboLines, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboEndStations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(27, 27, 27)
                        .addComponent(btnFindRoute, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtRouteName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cboTimeSlots, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(20, 20, 20)
                        .addComponent(btnCalculateCrowdedness, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(prgBar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(251, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Expected Crowdedness", jPanel1);

        tblAlertsTrains.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblAlertsTrains.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Route Name", "Time Slot", "Train", "Crowdedness"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAlertsTrains.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(tblAlertsTrains);

        cboAlertLines.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cboAlertLines.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAlertLinesActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Line:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1326, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(cboAlertLines, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(334, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cboAlertLines, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(471, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Today's Alerts", jPanel4);

        txtMessage.setColumns(20);
        txtMessage.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtMessage.setRows(5);
        txtMessage.setToolTipText("Please specify the condition for improved help.");
        jScrollPane1.setViewportView(txtMessage);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Line:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Message:");

        cboIncidentRoutes.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnAskHelp.setBackground(new java.awt.Color(0, 0, 0));
        btnAskHelp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnAskHelp.setForeground(new java.awt.Color(255, 255, 255));
        btnAskHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/alert.png"))); // NOI18N
        btnAskHelp.setText("Report Incident");
        btnAskHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAskHelpActionPerformed(evt);
            }
        });

        cboIncidentLines.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cboIncidentLines.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboIncidentLinesActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("Train Car ");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Current Time:");

        buttonGroup1.add(rbtnMedical);
        rbtnMedical.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rbtnMedical.setText("Medical");

        buttonGroup1.add(rbtnAccidental);
        rbtnAccidental.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rbtnAccidental.setText("Fire");

        buttonGroup1.add(rbtnOther);
        rbtnOther.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rbtnOther.setText("Other");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("Type of Emergency");

        buttonGroup1.add(rbtnSecurity);
        rbtnSecurity.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rbtnSecurity.setText("Security");

        cboTrainCars.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cboTrainCars.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Car1", "Car2", "Car3", "Unaware" }));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setText("Route:");

        txtCurrentTime.setEditable(false);
        txtCurrentTime.setBackground(new java.awt.Color(255, 255, 255));
        txtCurrentTime.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel6)
                    .addComponent(jLabel15)
                    .addComponent(jLabel12)
                    .addComponent(jLabel21))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cboIncidentLines, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 995, Short.MAX_VALUE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cboIncidentRoutes, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbtnMedical)
                                    .addComponent(rbtnSecurity))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbtnOther)
                                    .addComponent(rbtnAccidental))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCurrentTime, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboTrainCars, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAskHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(1142, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel11)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(cboIncidentLines, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cboIncidentRoutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(55, 55, 55)
                                    .addComponent(jLabel15)
                                    .addGap(1, 1, 1))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rbtnMedical)
                                        .addComponent(rbtnAccidental))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rbtnOther)
                                .addComponent(rbtnSecurity))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtCurrentTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(161, 161, 161)))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTrainCars, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jLabel12)))
                .addGap(52, 52, 52)
                .addComponent(btnAskHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(167, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Report Incident", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(537, 537, 537)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboIncidentLinesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboIncidentLinesActionPerformed
        // TODO add your handling code here:
        if(cboIncidentLines.getItemCount() > 0 ){
          //  btnAskHelp.setEnabled(true);
            populateRoutes();
        }
      

    }//GEN-LAST:event_cboIncidentLinesActionPerformed

    private void btnAskHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAskHelpActionPerformed
        // TODO add your handling code here:

        Line incidentLine = (Line) cboIncidentLines.getSelectedItem();
        Route incidentRoute = (Route) cboIncidentRoutes.getSelectedItem();  
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
       
        RouteSchedule incidentRouteSchedule = null;
        boolean checkWithNextFlag = false;      
        Date previousDate = null;
        Date incidentDate = new Date();
        txtCurrentTime.setText(formatter.format(incidentDate));
        //int routeScheduleIndex = -1;
        
        for(RouteSchedule rs: incidentRoute.getRouteSchedules()){
          //  routeScheduleIndex ++;
            String firstTime =   rs.getTimeSlotsTrain().getValue().split(" ")[0].trim();
            String secondTime =   rs.getTimeSlotsTrain().getValue().split(" ")[2].trim();

            try{                
                Date firstDate = formatter.parse(firstTime);
                Date secondDate = formatter.parse(secondTime);

                if(checkWithNextFlag && previousDate !=null){
                    if(compareTime(previousDate, incidentDate)){
                        if(!compareTime(firstDate, incidentDate)){                      
                            incidentRouteSchedule = rs;
                            break;
                       }
                    }
                }

                if (compareTime(firstDate, incidentDate)){
                    if(!compareTime(secondDate, incidentDate)){
                        incidentRouteSchedule = rs;
                        break;
                    }
                    else {
                        checkWithNextFlag= true;
                        previousDate = secondDate;
                    }

                }

            }
            catch(Exception e){

            }
        }
        
        if(incidentRouteSchedule == (null)){
            JOptionPane.showMessageDialog(this, "There is no train running currently running for the route :" + incidentRoute.getRouteName(), "Report Incident", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Train incidentTrain = incidentRoute.fetchRunningTrain(incidentRouteSchedule.getTimeSlotsTrain());
        String incidentCauseMessage = txtMessage.getText();
     
        TrainOffered incidentTrainOffered = enterprise.getTrainsOfferedHistory().fetchOrAddTrain(incidentTrain, incidentDate);
        TrainStatus incidentRunningTrain = incidentTrainOffered.fetchRunningTrainByTimeSlot(incidentRouteSchedule.getTimeSlotsTrain());
       
        if(incidentRunningTrain == null){
             String value = incidentRouteSchedule.getTimeSlotsTrain().name().substring(8);             
             incidentRunningTrain = simulateData.runTrainTillSchedule(incidentRouteSchedule, enterprise, rt, value);            
//            incidentRunningTrain = incidentTrainOffered.fetchRunningTrainByTimeSlot(incidentRouteSchedule.getTimeSlotsTrain());
        }
      
        IncidentWorkRequest request = new IncidentWorkRequest();
        request.setSender(account);
        request.setStatus(IncidentWorkRequest.StatusType.NEW_INCIDENT.getValue());
        request.setIncidentCauseMessage(incidentCauseMessage);
        request.setIncidentDate(incidentDate);
        request.setIncidentLine(incidentLine);
        request.setIncidentRoute(incidentRoute);
        request.setIncidentRouteSchedule(incidentRouteSchedule);
        request.setCrowdednessLevel(incidentRunningTrain.getRunningCapacity());
        request.setIncidentTrain(incidentTrain);
        request.setRequestDate(new Date());
        request.setIncidentTrainCarNumber(cboTrainCars.getSelectedItem().toString());     
        request.setId();
        
        if(rbtnAccidental.isSelected()){
            request.setTypeOfEmergency("Accidental");
        }
        else if (rbtnMedical.isSelected()){
            request.setTypeOfEmergency("Medical");
        }
        else if(rbtnOther.isSelected()){
            request.setTypeOfEmergency("Other");
        }
        else if(rbtnSecurity.isSelected()){
            request.setTypeOfEmergency("Security");
        }
        
        
        sae.getWorkQueue().getWorkRequestList().add(request);
        
        JOptionPane.showMessageDialog(this, "The Incident reported successfully. Help will reach soon at Station :" + incidentRoute.getEndStation(), "Report Incident", JOptionPane.INFORMATION_MESSAGE);

        txtMessage.setText("");
        buttonGroup1.clearSelection();
        rbtnAccidental.setSelected(true);
        
    }//GEN-LAST:event_btnAskHelpActionPerformed

    private void cboAlertLinesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAlertLinesActionPerformed
        // TODO add your handling code here

        if(cboAlertLines.getItemCount() > 0 ){

            populateAlerts();

        }

    }//GEN-LAST:event_cboAlertLinesActionPerformed

    int slotNumber;
    private void btnCalculateCrowdednessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalculateCrowdednessActionPerformed
        // TODO add your handling code here:
        RouteSchedule selectedSchedule = (RouteSchedule) cboTimeSlots.getSelectedItem();        
        String value = selectedSchedule.getTimeSlotsTrain().name().substring(8);
        int coveredJourney = (Integer.parseInt(value)==15)? 14:Integer.parseInt(value);        
        slotNumber = (coveredJourney * 100)/15;
        
        rt = simulateData.runTrain(selectedSchedule,enterprise,rt,value);    
        
        txtTrainName.setText(selectedSchedule.getTrainOffered().getTrainName());
        
        prgBar.setValue(slotNumber);
        prgBar.setBackground(Color.red);
        prgBar.setString("Journey Covered : " + slotNumber +"%");
                    
        showExpectedCrowdedness(selectedSchedule.getTrainOffered()); 
        
    }//GEN-LAST:event_btnCalculateCrowdednessActionPerformed

    private void btnFindRouteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindRouteActionPerformed
        // TODO add your handling code here:

        Station startStation = (Station) cboStations.getSelectedItem();
        Station endStation = (Station) cboEndStations.getSelectedItem();
        boolean routeFound = false;

        for(Segment seg :selectedSegments){
            for(Route r : seg.getRoutes()){
                if(r.getStartStation()== startStation && r.getEndStation()== endStation){
                    selectedRoute = r;
                    txtRouteName.setText(r.getRouteName());
                    txtStationPopularity.setText(r.getEndStation().getStationPopularity().getValue());
                    routeFound = true;
                    break;
                }
            }
            if(routeFound){
                populateTimeSlots();
                break;
            }
        }
        

        
    }//GEN-LAST:event_btnFindRouteActionPerformed

    private void cboLinesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLinesActionPerformed
        // TODO add your handling code here:
        populateEndStations();
    }//GEN-LAST:event_cboLinesActionPerformed

    private void cboStationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboStationsActionPerformed
        // TODO add your handling code here:
        populateLines();
    }//GEN-LAST:event_cboStationsActionPerformed

    private void txtRouteNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRouteNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRouteNameActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAskHelp;
    private javax.swing.JButton btnCalculateCrowdedness;
    private javax.swing.JButton btnFindRoute;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cboAlertLines;
    private javax.swing.JComboBox cboEndStations;
    private javax.swing.JComboBox cboIncidentLines;
    private javax.swing.JComboBox cboIncidentRoutes;
    private javax.swing.JComboBox cboLines;
    private javax.swing.JComboBox cboStations;
    private javax.swing.JComboBox cboTimeSlots;
    private javax.swing.JComboBox cboTrainCars;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JProgressBar prgBar;
    private javax.swing.JRadioButton rbtnAccidental;
    private javax.swing.JRadioButton rbtnMedical;
    private javax.swing.JRadioButton rbtnOther;
    private javax.swing.JRadioButton rbtnSecurity;
    private javax.swing.JTable tblAlertsTrains;
    private javax.swing.JTable tblTrainDetails;
    private javax.swing.JTextField txtCrowdednessRecommendation;
    private javax.swing.JTextField txtCurrentTime;
    private javax.swing.JTextField txtDrop;
    private javax.swing.JTextField txtExpectedStatus;
    private javax.swing.JTextArea txtMessage;
    private javax.swing.JTextField txtRouteName;
    private javax.swing.JTextField txtStationPopularity;
    private javax.swing.JTextField txtTodayDate;
    private javax.swing.JTextField txtTrainName;
    private javax.swing.JTextField txtTrainStatus;
    private javax.swing.JTextField txtUp;
    // End of variables declaration//GEN-END:variables
}

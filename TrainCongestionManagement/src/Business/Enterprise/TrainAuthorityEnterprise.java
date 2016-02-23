/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.TrainManagementSystem.Line;
import Business.TrainManagementSystem.LineCatalog;
import Business.TrainManagementSystem.Route;
import Business.TrainManagementSystem.RouteSchedule;
import Business.TrainManagementSystem.Segment;
import Business.TrainManagementSystem.StationCatalog;
import Business.TrainManagementSystem.Train;
import Business.TrainManagementSystem.TrainCatalog;
import Business.TrainManagementSystem.TrainOfferedHistory;
import Business.TrainManagementSystem.TrainSchedule;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Manasi Laddha
 */
public class TrainAuthorityEnterprise  extends Enterprise {

    private StationCatalog stationCatalog;
    private TrainCatalog trainCatalog;
    private LineCatalog lineCatalog;
    private TrainOfferedHistory trainsOfferedHistory;
    
    public TrainAuthorityEnterprise(String name) {
        super(name, EnterpriseType.TRAIN_AUTHORITY_ENTERPRISE);        
        stationCatalog = new StationCatalog();
        trainCatalog = new TrainCatalog();
        lineCatalog = new LineCatalog();
        trainsOfferedHistory = new TrainOfferedHistory();
    }
    
    public StationCatalog getStationCatalog() {
        return stationCatalog;
    }

    public void setStationCatalog(StationCatalog stationCatalog) {
        this.stationCatalog = stationCatalog;
    }

    public TrainCatalog getTrainCatalog() {
        return trainCatalog;
    }

    public void setTrainCatalog(TrainCatalog trainCatalog) {
        this.trainCatalog = trainCatalog;
    }

    public LineCatalog getLineCatalog() {
        return lineCatalog;
    }

    public void setLineCatalog(LineCatalog lineCatalog) {
        this.lineCatalog = lineCatalog;
    }
    
    public TrainOfferedHistory getTrainsOfferedHistory() {
        return trainsOfferedHistory;
    }

    public void setTrainsOfferedHistory(TrainOfferedHistory trainsOfferedHistory) {
        this.trainsOfferedHistory = trainsOfferedHistory;
    }
    
     public void setSchedule(Line selectedLine){
       
        ArrayList<Train> trainsOffered = new ArrayList<>();
        for(Train t : this.getTrainCatalog().getTrainsList()){                 
            if (t.getLineOnTrain() == selectedLine ){
                 trainsOffered.add(t);
            }
        }
        
        if(trainsOffered.size()<= 0){
            JOptionPane.showMessageDialog(null, "There are no trains offered for the line. Please offer some trains.", "Create Line Schedule", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        ArrayList<Route> routeList = new ArrayList<>();
        
        for(Segment s : selectedLine.getSegments()){
            routeList.add(s.getRoutes().get(0));
        }
        
        ArrayList<Segment> segmentList = selectedLine.getSegments();
        for(int i = segmentList.size() - 1; i >=0 ; i --){            
            routeList.add(segmentList.get(i).getRoutes().get(1));
        }
            
        int k = 0;
            int maxK = routeList.size();
            for(Train t : trainsOffered){                 
                for(TrainSchedule ts : t.getTrainSchedule()){
                    k = (k >= maxK) ? k = 0 : k ;
                    
                    ts.setRoute(routeList.get(k));
                    ts.setSlotTaken(true);
                    ts.setTimeSlot(ts.getTimeSlot());
                   
                     k ++;
                }
                               
            }     
        
        
//           int count = 0;
//            // int loopCount = 0;
//           int maxCount = trainsOffered.size();
//             
//           for(Route r : routeList){
//                for(RouteSchedule rs: r.getRouteSchedules()){    
//                       count = (count >= maxCount) ? count = 0 : count ;  
//                       boolean flag = false;
//                       for(TrainSchedule ts : trainsOffered.get(count).getTrainSchedule()){
//                           if(ts.getTimeSlot() == rs.getTimeSlotsTrain()){
//                              if(!ts.isSlotTaken()){
//                                rs.setTrainOffered(trainsOffered.get(count));
//                                rs.setTimeSlotsTrain(rs.getTimeSlotsTrain());                      
//                                rs.setSlotTaken(true);                      
//                                count ++;
//                                flag = true;
//                                break;
//                              }
//                           } 
//                       }                       
//                       if(flag){
//                           break;
//                       }                    
//                   }
//            }
//                
            for(Train selectedTrain : trainsOffered){ 
               for(int i=0; i < 15 ; i++){  
                    Route selectedRoute = selectedTrain.getTrainSchedule().get(i).getRoute(); 
                    RouteSchedule rs  = selectedRoute.getRouteSchedules().get(i);
                    rs.setTrainOffered(selectedTrain);
                    rs.setSlotTaken(true);
                }
                
//               for(TrainSchedule ts: selectedTrain.getTrainSchedule()){  
//                    Route selectedRoute = ts.getRoute();
//                    for(RouteSchedule rs: selectedRoute.getRouteSchedules()){
//                        if (rs.getTimeSlotsTrain().getValue().equals(ts.getTimeSlot().getValue())){
//                           rs.setTrainOffered(selectedTrain);
//                           rs.setSlotTaken(true);
//                           break;
//                        }
//                   }                    
//                }              
            }

        selectedLine.setIsActive(true);
    }
    
    
    
}

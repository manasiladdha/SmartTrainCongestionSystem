/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.TrainManagementSystem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Manasi Laddha
 */
public class TrainOfferedHistory {
    
    private ArrayList<TrainOffered> trainsOffered ;

    public TrainOfferedHistory() {
        
        trainsOffered = new ArrayList<TrainOffered>();
        
    }
    
    public boolean addTrainOffered(TrainOffered tOff){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        for(TrainOffered toof : trainsOffered ){            
            if((tOff.getTrain().getTrainName() == toof.getTrain().getTrainName()) && formatter.format(tOff.getDayOffered()).equals(formatter.format(toof.getDayOffered()))){
                return false;
            }
        }      
        
        trainsOffered.add(tOff);
        return true;
    }
    
    public TrainOffered fetchTrainOffered(Train t , Date searchDate){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        for(TrainOffered toof : trainsOffered ){
            if(formatter.format(toof.getDayOffered()).equals(formatter.format(searchDate)) && t.getTrainName().equals(toof.getTrain().getTrainName())){
                return toof;
            }
        } 
        
        
        return null;
    }
    
     public TrainOffered fetchOrAddTrain(Train t , Date searchDate){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        for(TrainOffered toof : trainsOffered ){
            if(formatter.format(toof.getDayOffered()).equals(formatter.format(searchDate)) && t.getTrainName().equals(toof.getTrain().getTrainName())){
                return toof;
            }
        } 
        
        return addTrainOffered(t,searchDate);
        
    }
     
    public TrainOffered addTrainOffered(Train t,Date searchDate){
         TrainOffered tt = new TrainOffered(t);
         tt.setDayOffered(searchDate);
         
         trainsOffered.add(tt);
         return tt;
    }

    public ArrayList<TrainOffered> getTrainsOffered() {
        return trainsOffered;
    }

    public void setTrainsOffered(ArrayList<TrainOffered> trainsOffered) {
        this.trainsOffered = trainsOffered;
    }

    
    
    
}

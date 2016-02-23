/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.TrainManagementSystem;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Manasi Laddha
 */
public class TrainOffered {
   
    private Train train;    
    private Date dayOffered; 
    private ArrayList<TrainStatus> runningTrains;
 
    public TrainOffered(Train t) {          
        train = t;
        runningTrains = new ArrayList<>();     
    }

    public Date getDayOffered() {
        return dayOffered;
    }
    
    public void setDayOffered(Date dayOffered) {
        this.dayOffered = dayOffered;
    }

    public ArrayList<TrainStatus> getRunningTrains() {
        return runningTrains;
    }

    public void setRunningTrains(ArrayList<TrainStatus> instantPositionofTrain) {
        this.runningTrains = instantPositionofTrain;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public TrainStatus fetchRunningTrain(TrainSchedule ts){
        for(TrainStatus rt : runningTrains){
            if(rt.getTrainSchedule().equals(ts)){
                return rt;
            }
        }
        return null;
    }
    
    public TrainStatus fetchRunningTrainByTimeSlot(TimeSlot.TimeSlotRange tsr){
        for(TrainStatus rt : runningTrains){
            if(rt.getTrainSchedule().getTimeSlot().getValue().equals(tsr.getValue())){
                return rt;
            }
        }
        return null;
    }
    
    public int getAllAverageRunningCapacity(){
        int max=0;
        for(TrainStatus rt : runningTrains){
            max+=rt.getRunningCapacity();
        }
        return (max/runningTrains.size());
    }
    
    
   }

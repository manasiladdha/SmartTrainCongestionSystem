/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.TrainManagementSystem;

import Business.TrainManagementSystem.TimeSlot.TimeSlotRange;
import java.util.ArrayList;

/**
 *
 * @author Manasi Laddha
 */
public class Train {
    
    private int trainID;
    private String trainName; 
    private final ArrayList<TrainSchedule> trainSchedule;
    private ArrayList<TrainCar> trainCars; 
    private Line lineOnTrain;
    

    public Train(String name, Line lineTrain) {
        trainName = name;
        this.lineOnTrain = lineTrain;
        trainSchedule = new ArrayList<>();
        trainCars = new ArrayList<>();
        addSlots();
    }
    
    public int getTrainID() {
        return trainID;
    }

    public void setTrainID(int trainID) {
        this.trainID = trainID;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public ArrayList<TrainSchedule> getTrainSchedule() {
        return trainSchedule;
    }

    @Override
    public String toString() {
        return  trainName ;
    }

    public ArrayList<TrainCar> getTrainCars() {
        return trainCars;
    }

    public void setTrainCars(ArrayList<TrainCar> trainCars) {
        this.trainCars = trainCars;
    }

    public Line getLineOnTrain() {
        return lineOnTrain;
    }

    public void setLineOnTrain(Line lineOnTrain) {
        this.lineOnTrain = lineOnTrain;
    }
    
    private void addSlots(){
        
        trainSchedule.add(new TrainSchedule(TimeSlot.TimeSlotRange.TimeSlot1));
        trainSchedule.add(new TrainSchedule(TimeSlot.TimeSlotRange.TimeSlot2));
        trainSchedule.add(new TrainSchedule(TimeSlot.TimeSlotRange.TimeSlot3));
        trainSchedule.add(new TrainSchedule(TimeSlot.TimeSlotRange.TimeSlot4));
        trainSchedule.add(new TrainSchedule(TimeSlot.TimeSlotRange.TimeSlot5));
        trainSchedule.add(new TrainSchedule(TimeSlot.TimeSlotRange.TimeSlot6));
        trainSchedule.add(new TrainSchedule(TimeSlot.TimeSlotRange.TimeSlot7));
        trainSchedule.add(new TrainSchedule(TimeSlot.TimeSlotRange.TimeSlot8));
        trainSchedule.add(new TrainSchedule(TimeSlot.TimeSlotRange.TimeSlot9));
        trainSchedule.add(new TrainSchedule(TimeSlot.TimeSlotRange.TimeSlot10));
        trainSchedule.add(new TrainSchedule(TimeSlot.TimeSlotRange.TimeSlot11));
        trainSchedule.add(new TrainSchedule(TimeSlot.TimeSlotRange.TimeSlot12));
        trainSchedule.add(new TrainSchedule(TimeSlot.TimeSlotRange.TimeSlot13));
        trainSchedule.add(new TrainSchedule(TimeSlot.TimeSlotRange.TimeSlot14));
        trainSchedule.add(new TrainSchedule(TimeSlot.TimeSlotRange.TimeSlot15));
        
    }
    
    
    public TrainSchedule fetchTrainSchedule(TimeSlot.TimeSlotRange slot){
        
        for(TrainSchedule ts : trainSchedule){
            if(ts.getTimeSlot()== (slot)){
                return ts;
            }            
        }
        return null;        
    }
    
    public int getTrainCapacity(){
        int max =0;
        for(TrainCar tc: trainCars){
            max += tc.getMaximumCapacity();
        }
        return max;   
    }
    
    
    public TrainSchedule addTrainSchedule(TimeSlotRange tsr){
      TrainSchedule ts = fetchTrainSchedule(tsr);
      trainSchedule.add(ts);
      return ts;
        
    }
}

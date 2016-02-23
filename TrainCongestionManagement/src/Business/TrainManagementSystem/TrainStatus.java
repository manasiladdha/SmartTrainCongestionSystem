/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.TrainManagementSystem;

import java.util.ArrayList;

/**
 * SENSOR POPULATED DATA
 * @author Manasi Laddha
 */
public class TrainStatus {
    
    private TrainSchedule trainSchedule;
    private ArrayList<TrainCar> trainCars; 
    private String status;  
    private String sendAlert;
    private int runningCapacity;
    private int predictiveDrop;
    private int predictiveUp;

    public TrainStatus() {
        this.trainCars = new ArrayList<>();      
                
    }

    public int getRunningCapacity() {
        return runningCapacity;
    }

    public void setRunningCapacity(int runningCapacity) {
        this.runningCapacity = runningCapacity;
    }
    
    public ArrayList<TrainCar> getTrainCars() {
        return trainCars;
    }

    public void setTrainCars(ArrayList<TrainCar> trainCars) {
        this.trainCars = trainCars;
    }

    public TrainSchedule getTrainSchedule() {
        return trainSchedule;
    }

    public void setTrainSchedule(TrainSchedule trainSchedule) {
        this.trainSchedule = trainSchedule;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPredictiveDrop() {
        return predictiveDrop;
    }

    public void setPredictiveDrop(int predictiveDrop) {
        this.predictiveDrop = predictiveDrop;
    }

    public int getPredictiveUp() {
        return predictiveUp;
    }

    public void setPredictiveUp(int predictiveUp) {
        this.predictiveUp = predictiveUp;
    }

    @Override
    public String toString() {
        return this.getTrainSchedule().getTimeSlot().getValue();
    }

    public String getSendAlert() {
        return sendAlert;
    }

    public void setSendAlert(String sendAlert) {
        this.sendAlert = sendAlert;
    }
    
    
      
    
    
}

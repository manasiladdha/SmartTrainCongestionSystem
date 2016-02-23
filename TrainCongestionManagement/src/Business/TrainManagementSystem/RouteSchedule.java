/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.TrainManagementSystem;

import Business.TrainManagementSystem.TimeSlot.TimeSlotRange;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Manasi Laddha
 */
public class RouteSchedule {
    
    private int scheduleID;   
    private int count = 0;    
    private TimeSlotRange timeSlotsTrain;
    private Train trainOffered; 
    private boolean slotTaken;
 
    public RouteSchedule(TimeSlotRange timeSlot) {
        scheduleID = count ++;        
        timeSlotsTrain = timeSlot;       
       
    }

    @Override
    public String toString() {
        return this.getTimeSlotsTrain().getValue();
    }
    

    public int getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }

    public TimeSlotRange getTimeSlotsTrain() {
        return timeSlotsTrain;
    }

    public void setTimeSlotsTrain(TimeSlotRange timeSlotsTrain) {
        this.timeSlotsTrain = timeSlotsTrain;
    }

    public boolean isSlotTaken() {
        return slotTaken;
    }

    public void setSlotTaken(boolean slotTaken) {
        this.slotTaken = slotTaken;
    }

    public Train getTrainOffered() {
        return trainOffered;
    }

    public void setTrainOffered(Train trainOffered) {
        this.trainOffered = trainOffered;
    }

}

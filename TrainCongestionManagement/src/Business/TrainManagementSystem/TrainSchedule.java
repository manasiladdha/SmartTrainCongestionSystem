/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.TrainManagementSystem;

import Business.TrainManagementSystem.TimeSlot.TimeSlotRange;

/**
 *
 * @author Manasi Laddha
 */
public class TrainSchedule {
    
      private int scheduleID;   
      private int count = 0;

      private TimeSlotRange timeSlot;
      private Route route;
      private boolean slotTaken;
        
    public TrainSchedule(TimeSlotRange tr) {
        scheduleID = count ++;
        this.timeSlot = tr;
                 
    }

    public int getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }

    public TimeSlotRange getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlotRange timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public boolean isSlotTaken() {
        return slotTaken;
    }

    public void setSlotTaken(boolean slotTaken) {
        this.slotTaken = slotTaken;
    }

    @Override
    public String toString() {
        return timeSlot.getValue();
    }
    
    
    
    
}

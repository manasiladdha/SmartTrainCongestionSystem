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
public class Route {
    
    private Station startStation;
    private Station endStation;
    private String routeName;
    
    private final ArrayList<RouteSchedule> routeSchedules;
    
   
    public Route(Station startStation, Station endStation) {
        this.startStation = startStation;
        this.endStation = endStation;       
        this.routeName = this.startStation + " to " + this.endStation;     
        routeSchedules = new ArrayList<>();
        addSlots();
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    @Override
    public String toString() {
        return  routeName;
    }
    
    private void addSlots(){
        
        routeSchedules.add(new RouteSchedule(TimeSlot.TimeSlotRange.TimeSlot1));
        routeSchedules.add(new RouteSchedule(TimeSlot.TimeSlotRange.TimeSlot2));
        routeSchedules.add(new RouteSchedule(TimeSlot.TimeSlotRange.TimeSlot3));
        routeSchedules.add(new RouteSchedule(TimeSlot.TimeSlotRange.TimeSlot4));
        routeSchedules.add(new RouteSchedule(TimeSlot.TimeSlotRange.TimeSlot5));
        routeSchedules.add(new RouteSchedule(TimeSlot.TimeSlotRange.TimeSlot6));
        routeSchedules.add(new RouteSchedule(TimeSlot.TimeSlotRange.TimeSlot7));
        routeSchedules.add(new RouteSchedule(TimeSlot.TimeSlotRange.TimeSlot8));
        routeSchedules.add(new RouteSchedule(TimeSlot.TimeSlotRange.TimeSlot9));
        routeSchedules.add(new RouteSchedule(TimeSlot.TimeSlotRange.TimeSlot10));
        routeSchedules.add(new RouteSchedule(TimeSlot.TimeSlotRange.TimeSlot11));
        routeSchedules.add(new RouteSchedule(TimeSlot.TimeSlotRange.TimeSlot12));
        routeSchedules.add(new RouteSchedule(TimeSlot.TimeSlotRange.TimeSlot13));
        routeSchedules.add(new RouteSchedule(TimeSlot.TimeSlotRange.TimeSlot14));
        routeSchedules.add(new RouteSchedule(TimeSlot.TimeSlotRange.TimeSlot15));
        
    }

    public Station getStartStation() {
        return startStation;
    }

    public void setStartStation(Station startStation) {
        this.startStation = startStation;
    }

    public Station getEndStation() {
        return endStation;
    }

    public void setEndStation(Station endStation) {
        this.endStation = endStation;
    }

    public ArrayList<RouteSchedule> getRouteSchedules() {
        return routeSchedules;
    }
    
    public RouteSchedule fetchRouteSchedule(TimeSlot.TimeSlotRange slot){
        
        for(RouteSchedule rs : routeSchedules){
            if(rs.getTimeSlotsTrain() == (slot)){
                return rs;
            }            
        }
        return null;        
    }
    
    public ArrayList<TimeSlotRange> fetchEmptyTimeSlots(){
        
        ArrayList<TimeSlotRange> emptyTimeSlots = new ArrayList<>();
        
        for(RouteSchedule rs: routeSchedules){
            if(!rs.isSlotTaken()){
                emptyTimeSlots.add(rs.getTimeSlotsTrain());
            }
        }
        return emptyTimeSlots;
    }
    
    public Train fetchRunningTrain(TimeSlotRange tsr){
        
        for(RouteSchedule rs : routeSchedules){
            if(rs.getTimeSlotsTrain().equals(tsr)){
                return rs.getTrainOffered();
            }
            
        }
        return null;        
    }
}

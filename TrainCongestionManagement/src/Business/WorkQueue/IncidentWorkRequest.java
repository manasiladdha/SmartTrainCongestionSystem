/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import Business.TrainManagementSystem.Line;
import Business.TrainManagementSystem.Route;
import Business.TrainManagementSystem.RouteSchedule;
import Business.TrainManagementSystem.Train;
import java.util.Date;

/**
 *
 * @author Manasi Laddha
 */
public class IncidentWorkRequest extends WorkRequest{
    
  
    private Line incidentLine;
    private Route incidentRoute;
    private RouteSchedule incidentRouteSchedule;
    private Date incidentDate;
    private Train incidentTrain;
    private String incidentCauseMessage;
    private int crowdednessLevel;
    private String typeOfEmergency;
    private String incidentTrainCarNumber;

    public int getCrowdednessLevel() {
        return crowdednessLevel;
    }

    public void setCrowdednessLevel(int crowdednessLevel) {
        this.crowdednessLevel = crowdednessLevel;
    }
    
    public enum StatusType{
        NEW_INCIDENT("New Incident"),
        ASSIGNED("Assigned"),
        ACTION_TAKEN("Action Taken");
        
        private String value;
        private StatusType(String val){
           this.value = val;                  
        }
        
        public String getValue(){
            return this.value;
        }
        
    }
    
    public Line getIncidentLine() {
        return incidentLine;
    }

    public void setIncidentLine(Line incidentLine) {
        this.incidentLine = incidentLine;
    }

    public Route getIncidentRoute() {
        return incidentRoute;
    }

    public void setIncidentRoute(Route incidentRoute) {
        this.incidentRoute = incidentRoute;
    }

    public RouteSchedule getIncidentRouteSchedule() {
        return incidentRouteSchedule;
    }

    public void setIncidentRouteSchedule(RouteSchedule incidentRouteSchedule) {
        this.incidentRouteSchedule = incidentRouteSchedule;
    }

    public Date getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(Date incidentDate) {
        this.incidentDate = incidentDate;
    }

    public Train getIncidentTrain() {
        return incidentTrain;
    }

    public void setIncidentTrain(Train incidentTrain) {
        this.incidentTrain = incidentTrain;
    }

    public String getIncidentCauseMessage() {
        return incidentCauseMessage;
    }

    public void setIncidentCauseMessage(String incidentCauseMessage) {
        this.incidentCauseMessage = incidentCauseMessage;
    }

    public String getTypeOfEmergency() {
        return typeOfEmergency;
    }

    public void setTypeOfEmergency(String typeOfEmergency) {
        this.typeOfEmergency = typeOfEmergency;
    }

    public String getIncidentTrainCarNumber() {
        return incidentTrainCarNumber;
    }

    public void setIncidentTrainCarNumber(String incidentTrainCarNumber) {
        this.incidentTrainCarNumber = incidentTrainCarNumber;
    }

    @Override
    public String toString() {
        return Integer.toString(this.getId());
    }

    
    
    
}

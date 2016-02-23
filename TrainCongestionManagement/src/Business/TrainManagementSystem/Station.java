/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.TrainManagementSystem;

import java.util.ArrayList;

/**
 *
 * @author Manasi Laddha
 */
public class Station {
    
    private String stationName;
    private int stationID;
    private ArrayList<Line> linesOnStation ;
    private boolean activeFlag;
    private boolean multiLineStation;
    private Popularity stationPopularity;

    public Popularity getStationPopularity() {
        return stationPopularity;
    }

    public void setStationPopularity(Popularity stationPopularity) {
        this.stationPopularity = stationPopularity;
    }
    
    public enum Popularity{
        HIGH_RIDERSHIP("High Ridership"),
        LOW_RIDERSHIP("Low Ridership");
        
        private String value;
        private Popularity(String val){
            this.value = val;
        }
        
        public String getValue(){
            return this.value;
        }
        
    }
    
    
    public Station(String name) {
        this.stationName = name;
        linesOnStation = new ArrayList<>();        
    }
    
    public int getStationID() {
        return stationID;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

 
    public void addLineOnStation(Line l){
        if (!checkIfStationContainsLine(l)){
          linesOnStation.add(l);
        }        
    }

    @Override
    public String toString() {
        return  stationName ;
    }

    public boolean isActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(boolean activeFlag) {
        this.activeFlag = activeFlag;
    }

    public boolean isMultiLineStation() {
        return multiLineStation;
    }

    public void setMultiLineStation(boolean multiLineStation) {
        this.multiLineStation = multiLineStation;
    }
    
    public boolean checkIfStationContainsLine(Line lineToBeSearched){
        for(Line l : linesOnStation){
            if (lineToBeSearched == l){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Line> getLinesOnStation() {
        return linesOnStation;
    }

    public void setLinesOnStation(ArrayList<Line> linesOnStation) {
        this.linesOnStation = linesOnStation;
    }
    
    
}

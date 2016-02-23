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
public class StationCatalog {
    
    private ArrayList<Station> stationsList;
    private int count = 0;

    public StationCatalog() {           
        stationsList = new ArrayList<>();     
    }
    
    public Station addStation(String name){
        Station s = new Station(name);        
        s.setStationID(count++);
        s.setActiveFlag(true);
        s.setMultiLineStation(true);
        this.stationsList.add(s);
        return s;
    }
    
    public Station addStation(Station s){
               
        s.setStationID(count++);
       
        this.stationsList.add(s);
        return s;
    }    
    
   public Station getStation(String stationName){
       for(Station s: stationsList){
           if (s.getStationName().equalsIgnoreCase(stationName)){
             return s;
          }
       }
       return  null;
   }

    public ArrayList<Station> getStationsList() {
        return stationsList;
    }

    public void setStationsList(ArrayList<Station> stationsList) {
        this.stationsList = stationsList;
    }
}

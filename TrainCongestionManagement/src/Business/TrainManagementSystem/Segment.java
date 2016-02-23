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
public class Segment {
    
    private Station endStation1;
    private Station endStation2;
    
    private int segmentID;
  
    
    private ArrayList<Route> routes; 

    public Segment(Station endStation1, Station endStation2) {
        this.endStation1 = endStation1;
        this.endStation2 = endStation2;
        
        routes = new ArrayList<>();
       
        routes.add(new Route(this.endStation1, this.endStation2));
        routes.add(new Route(this.endStation2, this.endStation1));
        
    }
       
    public Station getEndStation1() {
        return endStation1;
    }

    public void setEndStation1(Station endStation1) {
        this.endStation1 = endStation1;
    }

    public Station getEndStation2() {
        return endStation2;
    }

    public void setEndStation2(Station endStation2) {
        this.endStation2 = endStation2;
    }

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(ArrayList<Route> routes) {
        this.routes = routes;
    }

    public int getSegmentID() {
        return segmentID;
    }

    public void setSegmentID(int segmentID) {
        this.segmentID = segmentID;
    }

 
    
    
}

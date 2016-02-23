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
public class Line {
    
    private String lineName;
    private int LineID; 
    private boolean  isActive;
    private boolean  hasStarted;
    private int countSegment =0 ;
    
    private Station startStation;
    private Station endStation;    
    private ArrayList<Segment> segments;

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public int getLineID() {
        return LineID;
    }

    public void setLineID(int LineID) {
        this.LineID = LineID;
    }

    public Line(String lineName) {
        this.lineName = lineName;
        segments = new ArrayList<>();
    }

    @Override
    public String toString() {
        return  lineName ;
    }

    public Station getStartStation() {
        return startStation;
    }

    public void setStartStation(Station startStation) {
        this.startStation = startStation;
        startStation.addLineOnStation(this);
    }

    public Station getEndStation() {
        return endStation;
    }

    public void setEndStation(Station endStation) {
        this.endStation = endStation;
        endStation.addLineOnStation(this);
    }

    public ArrayList<Segment> getSegments() {
        return segments;
    }

    public void setSegments(ArrayList<Segment> segments) {
        this.segments = segments;
    }
   
    public boolean isStationPresentOnLine(Station stationtoFound){
        
        for(Segment segs : segments){
            if (segs.getEndStation1().equals(stationtoFound) || segs.getEndStation2().equals(stationtoFound)){
                return true;
            }
        }
        return false;
        
    }
    
    public Segment addSegment(Station segStartStation, Station segEndStation){
        Segment s = new Segment(segStartStation, segEndStation);
        segStartStation.addLineOnStation(this);
        segEndStation.addLineOnStation(this);
        s.setSegmentID(countSegment++);
        segments.add(s);
        return s;
    }
    
    public void extendSegment(Station nextStation){
        
       Station previousStation  = segments.get(segments.size() - 1).getEndStation2();
       
       Segment nextSeg = new Segment(previousStation, nextStation);
       nextStation.addLineOnStation(this);
       segments.add(nextSeg);
       
       this.endStation = nextStation;
       
        
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isHasStarted() {
        return hasStarted;
    }

    public void setHasStarted(boolean hasStarted) {
        this.hasStarted = hasStarted;
    }
       
    
}

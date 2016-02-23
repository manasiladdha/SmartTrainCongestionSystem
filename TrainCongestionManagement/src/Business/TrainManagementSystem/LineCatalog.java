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
public class LineCatalog {
    
    private ArrayList<Line> linesList ;
    private static int count = 0;

    public LineCatalog() {
        linesList = new ArrayList<>();
    }

    public ArrayList<Line> getLinesList() {
        return linesList;
    }

    public void setLinesList(ArrayList<Line> linesList) {
        this.linesList = linesList;
    }
    
    public Line addLine(String name){
       Line l = new Line(name);
       l.setLineID(count ++);
       linesList.add(l);
       return l;
    }
    
//    public void addExistingLine(Line l){
////        boolean flag = false;
////       
////        for(Line l : linesList){
////            if (l.getLineName().equalsIgnoreCase(lineName)){               
//               this.linesList.add(l);
////               return l;               
////            }
////        }  
//        // if line is not find.
////            Line l = new Line(lineName);
////            l.setLineID(count ++);
////            this.getLinesList().add(new Line(lineName));
////            return l;
//    }
    
     public Line getExistingLine(String lineName){
            
        for(Line l : linesList){
            if (l.getLineName().equalsIgnoreCase(lineName)){             
               return l;               
            }
        }  
       return null;
    }
    
}

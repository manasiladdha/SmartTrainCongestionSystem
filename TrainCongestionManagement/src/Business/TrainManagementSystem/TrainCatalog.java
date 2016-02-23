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
public class TrainCatalog {
    
    private ArrayList<Train> trainsList;
    private static int count =0;

    public TrainCatalog() {
        trainsList = new ArrayList<Train>();
    }
    
    public ArrayList<Train> getTrainsList() {
        return trainsList;
    }

    public void setTrainsList(ArrayList<Train> trainsList) {
        this.trainsList = trainsList;
    }
    
    public Train addNewTrain(String trainName, Line lineTrain){
        Train t = new Train(trainName, lineTrain);
        t.setTrainID(count++);
        trainsList.add(t);
        return t;
    }
    
    public Train getTrain(String name){
       for(Train t : trainsList){
           if(t.getTrainName().equalsIgnoreCase(name)){
               return t;
           }
       }
       return null;
    }
    
    
}

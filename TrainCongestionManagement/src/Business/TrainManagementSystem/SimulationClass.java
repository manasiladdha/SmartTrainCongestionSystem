/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.TrainManagementSystem;

import Business.Enterprise.TrainAuthorityEnterprise;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author Manasi Laddha
 */
public class SimulationClass {
    
    public TrainStatus runTrainTillSchedule(RouteSchedule selectedSchedule, TrainAuthorityEnterprise enterprise,TrainStatus rt, String slotValue){
        
        int currentIndex = Integer.parseInt(slotValue);
        currentIndex--;
        
        Train selectedTrain = selectedSchedule.getTrainOffered();
        
        TrainCar actualTrainCar = selectedTrain.getTrainCars().get(0);                      
       
        TrainOffered trainOffered = enterprise.getTrainsOfferedHistory().fetchOrAddTrain(selectedTrain, new Date());
       
        int previousSeed = 0;
            
            for(TrainSchedule ts : trainOffered.getTrain().getTrainSchedule()){
                
                if(trainOffered.fetchRunningTrain(ts) != null){                     
                   
                     rt = trainOffered.fetchRunningTrain(trainOffered.getTrain().getTrainSchedule().get(currentIndex));
                  
                 }
                 else {
                     
                    previousSeed= generateCrowdedness(previousSeed,rt, ts,trainOffered,selectedTrain,actualTrainCar);
                         
                 }
                          
                 if(ts.getTimeSlot() == selectedSchedule.getTimeSlotsTrain()){   
                    rt = trainOffered.fetchRunningTrain(trainOffered.getTrain().getTrainSchedule().get(currentIndex));
                    break;
                 }
                 
                
            }
            
        return rt;
         
      
      }
  
    public TrainStatus runTrain(RouteSchedule selectedSchedule, TrainAuthorityEnterprise enterprise,TrainStatus rt, String slotValue){
        
        int currentIndex = Integer.parseInt(slotValue);
        currentIndex--;
        int previousIndex;
        
        if (currentIndex ==0){
         previousIndex = 0;
        }
        else {
            previousIndex = currentIndex - 1;
        }
        
        //Train selectedTrain = selectedRoute.getRouteSchedules().get(currentIndex).getTrainOffered();
        Train selectedTrain = selectedSchedule.getTrainOffered();
        
        TrainCar actualTrainCar = selectedTrain.getTrainCars().get(0);                      
       
        TrainOffered trainOffered = enterprise.getTrainsOfferedHistory().fetchOrAddTrain(selectedTrain, new Date());
       
        int previousSeed = 0;
            
            for(TrainSchedule ts : trainOffered.getTrain().getTrainSchedule()){
                          
                 if(ts.getTimeSlot() == selectedSchedule.getTimeSlotsTrain()){
                     rt = trainOffered.fetchRunningTrain(trainOffered.getTrain().getTrainSchedule().get(previousIndex));                    
                     if(rt == null){
                        generateCrowdedness(previousSeed,rt,ts,trainOffered,selectedTrain,actualTrainCar);
                     }
                     generateNextSeed(rt, ts);
                     break;
                 }
                 
                 if(trainOffered.fetchRunningTrain(ts) != null){                     
                   
                     rt = trainOffered.fetchRunningTrain(trainOffered.getTrain().getTrainSchedule().get(previousIndex));
                  
                 }
                 else {
                     
                    previousSeed= generateCrowdedness(previousSeed,rt, ts,trainOffered,selectedTrain,actualTrainCar);

                 }
            }
            
        return rt;
         
      
      }
    
    private void generateNextSeed(TrainStatus rt,TrainSchedule ts){
               
                Station.Popularity stationpopular = ts.getRoute().getEndStation().getStationPopularity();
               
                int dropLimitUpperBound, dropLimitLowerBound, upLimitUpperBound, upLimitLowerBound ;
                
                if (stationpopular.equals(Station.Popularity.HIGH_RIDERSHIP)){
                    
                    if(ts.getTimeSlot().equals(TimeSlot.TimeSlotRange.TimeSlot1)){
                       dropLimitUpperBound = 0;
                       dropLimitLowerBound = 0; 
                       
                       upLimitLowerBound = 10;
                       upLimitUpperBound = 20;
                       
                    }
                    else if(ts.getTimeSlot() == TimeSlot.TimeSlotRange.TimeSlot3 ||
                            ts.getTimeSlot() == TimeSlot.TimeSlotRange.TimeSlot4 ||
                            ts.getTimeSlot() == TimeSlot.TimeSlotRange.TimeSlot5 ||
                            ts.getTimeSlot() == TimeSlot.TimeSlotRange.TimeSlot11 ||
                            ts.getTimeSlot() == TimeSlot.TimeSlotRange.TimeSlot12 ||
                            ts.getTimeSlot() == TimeSlot.TimeSlotRange.TimeSlot13) {
                        dropLimitLowerBound = 25; 
                        dropLimitUpperBound = 35;
                                   
                        upLimitLowerBound = 20;
                        upLimitUpperBound = 30;
                    }  
                    else if(ts.getTimeSlot().equals(TimeSlot.TimeSlotRange.TimeSlot15)){
                       dropLimitUpperBound = 25;
                       dropLimitLowerBound = 35; 
                       
                       upLimitLowerBound = 0;
                       upLimitUpperBound = 0;                       
                    }
                    else{
                       dropLimitLowerBound = 15; 
                       dropLimitUpperBound = 25;
                                              
                       upLimitLowerBound = 10;
                       upLimitUpperBound = 20;                        
                    }
                }
                else {
                    
                     if(ts.getTimeSlot().equals(TimeSlot.TimeSlotRange.TimeSlot1)){
                        dropLimitLowerBound = 0; 
                        dropLimitUpperBound = 0;
                       
                        upLimitLowerBound = 3;
                        upLimitUpperBound = 6; 
                    }
                    else if(ts.getTimeSlot() == TimeSlot.TimeSlotRange.TimeSlot3 ||
                            ts.getTimeSlot() == TimeSlot.TimeSlotRange.TimeSlot4 ||
                            ts.getTimeSlot() == TimeSlot.TimeSlotRange.TimeSlot5 ||
                            ts.getTimeSlot() == TimeSlot.TimeSlotRange.TimeSlot11 ||
                            ts.getTimeSlot() == TimeSlot.TimeSlotRange.TimeSlot12 ||
                            ts.getTimeSlot() == TimeSlot.TimeSlotRange.TimeSlot13){
                        dropLimitLowerBound = 9;   
                        dropLimitUpperBound = 21;
                                 
                        upLimitLowerBound = 9;
                        upLimitUpperBound = 18;    
                         
                    }
                    else if(ts.getTimeSlot().equals(TimeSlot.TimeSlotRange.TimeSlot15)){
                       dropLimitUpperBound = 9;
                       dropLimitLowerBound = 21; 
                       
                       upLimitLowerBound = 0;
                       upLimitUpperBound = 0;                       
                    }                    
                    else {
                       dropLimitLowerBound = 3;    
                       dropLimitUpperBound = 15;
                                              
                       upLimitLowerBound = 3;
                       upLimitUpperBound = 6; 
                    }               
                }
                
                rt.setPredictiveDrop(generateData(dropLimitLowerBound, dropLimitUpperBound));
                rt.setPredictiveUp(generateData(upLimitLowerBound, upLimitUpperBound));
    }
        
    private int generateCrowdedness(int previousSeed,TrainStatus rt, TrainSchedule ts,TrainOffered trainOffered, Train selectedTrain, TrainCar actualTrainCar){
        
                rt = new TrainStatus();
                rt.setTrainSchedule(ts);
            
                trainOffered.getRunningTrains().add(rt);
                
                //*************PREDICTIVE ANALAYSIS************//
                
                Station.Popularity stationpopular = ts.getRoute().getEndStation().getStationPopularity();
           
                
                int dropLimitUpperBound, dropLimitLowerBound, upLimitUpperBound, upLimitLowerBound ;
                
                if (stationpopular.equals(Station.Popularity.HIGH_RIDERSHIP)){
                    
                    if(ts.getTimeSlot().equals(TimeSlot.TimeSlotRange.TimeSlot1)){
                       dropLimitUpperBound = 0;
                       dropLimitLowerBound = 0; 
                       
                       upLimitLowerBound = 10;
                       upLimitUpperBound = 20;
                       
                    }
                    else if(ts.getTimeSlot() == TimeSlot.TimeSlotRange.TimeSlot3 ||
                            ts.getTimeSlot() == TimeSlot.TimeSlotRange.TimeSlot4 ||
                            ts.getTimeSlot() == TimeSlot.TimeSlotRange.TimeSlot5 ||
                            ts.getTimeSlot() == TimeSlot.TimeSlotRange.TimeSlot11 ||
                            ts.getTimeSlot() == TimeSlot.TimeSlotRange.TimeSlot12 ||
                            ts.getTimeSlot() == TimeSlot.TimeSlotRange.TimeSlot13) {
                        dropLimitLowerBound = 22; 
                        dropLimitUpperBound = 32;
                                   
                        upLimitLowerBound = 50;
                        upLimitUpperBound = 80;
                    }  
                    else if(ts.getTimeSlot().equals(TimeSlot.TimeSlotRange.TimeSlot15)){
                       dropLimitUpperBound = 40;
                       dropLimitLowerBound = 30; 
                       
                       upLimitLowerBound = 0;
                       upLimitUpperBound = 0;                       
                    }
                    else{
                       dropLimitLowerBound = 15; 
                       dropLimitUpperBound = 25;
                                              
                       upLimitLowerBound = 40;
                       upLimitUpperBound = 70;                        
                    }
                }
                else {
                    
                     if(ts.getTimeSlot().equals(TimeSlot.TimeSlotRange.TimeSlot1)){
                        dropLimitLowerBound = 0; 
                        dropLimitUpperBound = 0;
                       
                        upLimitLowerBound = 3;
                        upLimitUpperBound = 6; 
                    }
                    else if(ts.getTimeSlot() == TimeSlot.TimeSlotRange.TimeSlot3 ||
                            ts.getTimeSlot() == TimeSlot.TimeSlotRange.TimeSlot4 ||
                            ts.getTimeSlot() == TimeSlot.TimeSlotRange.TimeSlot5 ||
                            ts.getTimeSlot() == TimeSlot.TimeSlotRange.TimeSlot11 ||
                            ts.getTimeSlot() == TimeSlot.TimeSlotRange.TimeSlot12 ||
                            ts.getTimeSlot() == TimeSlot.TimeSlotRange.TimeSlot13){
                        dropLimitLowerBound = 10;   
                        dropLimitUpperBound = 22;
                                 
                        upLimitLowerBound = 30;
                        upLimitUpperBound = 50;    
                         
                    }
                    else if(ts.getTimeSlot().equals(TimeSlot.TimeSlotRange.TimeSlot15)){
                       dropLimitUpperBound = 9;
                       dropLimitLowerBound = 21; 
                       
                       upLimitLowerBound = 0;
                       upLimitUpperBound = 0;                       
                    }                    
                    else {
                       dropLimitLowerBound = 3;    
                       dropLimitUpperBound = 15;
                                              
                       upLimitLowerBound = 3;
                       upLimitUpperBound = 6; 
                    }               
                }

                
                rt.setPredictiveDrop(generateData(dropLimitLowerBound, dropLimitUpperBound));
                rt.setPredictiveUp(generateData(upLimitLowerBound, upLimitUpperBound));
          
                previousSeed = previousSeed - rt.getPredictiveDrop() + rt.getPredictiveUp();
                //System.out.println("previousSeed : " + previousSeed);
                
                //*************PREDICTIVE ANALAYSIS************//
                
                int seedForEachCar = (previousSeed/selectedTrain.getTrainCars().size());
                                            
                int normalStart = (int)Math.round((seedForEachCar * actualTrainCar.getNormalSeatCapacity())/selectedTrain.getTrainCapacity());
                
                int packedStart = (int)Math.round((seedForEachCar * actualTrainCar.getPackagedCapacity())/selectedTrain.getTrainCapacity());
              
                int reservedStart = (int)Math.round((seedForEachCar * actualTrainCar.getReservedSeats())/selectedTrain.getTrainCapacity());
         
               
                
                for(TrainCar tc : selectedTrain.getTrainCars()){ 
                    TrainCar t1 = new TrainCar(tc.getCarNumber());
                    rt.getTrainCars().add(t1);
                    t1.setIsBicyclesSkisAllowed(tc.isIsBicyclesSkisAllowed());
                    t1.setIsRestroomPresent(tc.isIsRestroomPresent());
                    int internalLimit2 = 20;             
                
                    if(ts.getTimeSlot() == TimeSlot.TimeSlotRange.TimeSlot3 ||
                            ts.getTimeSlot() == TimeSlot.TimeSlotRange.TimeSlot4 ||
                            ts.getTimeSlot() == TimeSlot.TimeSlotRange.TimeSlot5 ||
                            ts.getTimeSlot() == TimeSlot.TimeSlotRange.TimeSlot11 ||
                            ts.getTimeSlot() == TimeSlot.TimeSlotRange.TimeSlot12 ||
                            ts.getTimeSlot() == TimeSlot.TimeSlotRange.TimeSlot13){
                        
                        Date presentDate = trainOffered.getDayOffered();
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(presentDate);
                        int day = cal.get(Calendar.DAY_OF_WEEK);
                        
                        if(day == Calendar.SUNDAY || day == Calendar.SATURDAY){
                            internalLimit2 = 30;                      
                        }
                        else {                        
                            internalLimit2 = 45;
                        }
                    }
                    
                    
                    t1.setNormalSeatCapacity(generateData(normalStart , (actualTrainCar.getNormalSeatCapacity())));
                    t1.setReservedSeats(generateData(reservedStart, (actualTrainCar.getReservedSeats())));                    
                    t1.setPackagedCapacity(generateData(packedStart, (actualTrainCar.getPackagedCapacity()))); 
                    t1.setPackagedCapacity(t1.getPackagedCapacity() + internalLimit2);
                    
                    t1.updateMaximunCapacity();
                    rt.setRunningCapacity(rt.getRunningCapacity() + t1.getMaximumCapacity());
                    t1.generateReport(t1,actualTrainCar);
                    
                    

                }
                
                String status =  (selectedTrain.getTrainCapacity() >= rt.getRunningCapacity() )? "UNDER LIMIT" : "OVER LIMIT" ;
                
                rt.setStatus(status);                   
                previousSeed = rt.getRunningCapacity();                       
                

          return previousSeed;
    }
    
    private int generateData(int START, int END){
        Random randomNumber = new Random();        
        //get the range, casting to long to avoid overflow problems
        long range = (long)END - (long)START + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long)(range * randomNumber.nextDouble());
        int result =  (int)(fraction + START);    
        //log("Generated : " + randomNumber);
        return result;
        
    }
    
    public void generatePreviousCrowdedData(TrainOffered selectedTrainOfferd){
        
       int previousSeed = 0;            
            for(TrainSchedule ts : selectedTrainOfferd.getTrain().getTrainSchedule()){
                TrainStatus rt = null;
                 rt = selectedTrainOfferd.fetchRunningTrain(ts);
                 if(rt == null){
                     previousSeed= generateCrowdedness(previousSeed,rt, ts,selectedTrainOfferd,selectedTrainOfferd.getTrain(),selectedTrainOfferd.getTrain().getTrainCars().get(0));
                   }
                 else {
                    previousSeed= rt.getRunningCapacity();

                 }
        }
    }
    
     
}

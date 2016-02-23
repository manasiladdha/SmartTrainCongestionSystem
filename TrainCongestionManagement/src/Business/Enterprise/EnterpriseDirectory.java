/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Organization.Organization;
import Business.TrainManagementSystem.Line;
import Business.TrainManagementSystem.Station;
import Business.TrainManagementSystem.Train;
import Business.TrainManagementSystem.TrainCar;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Manasi Laddha
 */
public class EnterpriseDirectory {
    
    private ArrayList<Enterprise> enterpriseList;

    public EnterpriseDirectory() {
        enterpriseList = new ArrayList<>();
    }

    public ArrayList<Enterprise> getEnterpriseList() {
        return enterpriseList;
    }
    
    public Enterprise createAndAddEnterprise(String name, Enterprise.EnterpriseType type){
        Enterprise enterprise = null;
        if (type == Enterprise.EnterpriseType.TRAIN_AUTHORITY_ENTERPRISE){
            enterprise = new TrainAuthorityEnterprise(name);
            enterprise.getOrganizationDirectory().createOrganization(Organization.OrganizationType.TM_ADMINISTRATOR_ORGANIZATION);
            enterpriseList.add(enterprise);         
            initializeData((TrainAuthorityEnterprise)enterprise);   
        }
        else if (type == Enterprise.EnterpriseType.SECURITY_AUTHORITY_ENTERPRISE){
            enterprise = new SecurityAuthorityEnterprise(name);
            enterprise.getOrganizationDirectory().createOrganization(Organization.OrganizationType.SECURITY_ADMIN_ORGANIZATION);
            enterpriseList.add(enterprise);
        }
        return enterprise;
    }
    
    public boolean checkIfEnterpriseTypeExists(Enterprise.EnterpriseType eType){
        
        for(Enterprise e : enterpriseList){
            if (e.getEnterpriseType().equals(eType)){
                return true;
            }
        }
        return false;
        
    }
    
    public boolean checkIfEnterpriseNameExists(String name){
        
        for(Enterprise e : enterpriseList){
            if (e.getEnterpriseName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
        
    }
    
    private void initializeData(TrainAuthorityEnterprise enterprise){
      
        Line greenLine = enterprise.getLineCatalog().addLine("Green Line");
        Line redLine = enterprise.getLineCatalog().addLine("Red Line");
        Line blueLine = enterprise.getLineCatalog().addLine("Blue Line");
        Line orangeLine = enterprise.getLineCatalog().addLine("Orange Line");
       
       
        enterprise.getTrainCatalog().addNewTrain("Green Train 1", greenLine);
        enterprise.getTrainCatalog().addNewTrain("Green Train 2", greenLine);
        enterprise.getTrainCatalog().addNewTrain("Green Train 3", greenLine); 
        enterprise.getTrainCatalog().addNewTrain("Green Train 4", greenLine);
        enterprise.getTrainCatalog().addNewTrain("Green Train 5", greenLine);
        enterprise.getTrainCatalog().addNewTrain("Green Train 6", greenLine);
        enterprise.getTrainCatalog().addNewTrain("Green Train 7", greenLine);
        enterprise.getTrainCatalog().addNewTrain("Green Train 8", greenLine);
        
        enterprise.getTrainCatalog().addNewTrain("Red Train 1", redLine);
        enterprise.getTrainCatalog().addNewTrain("Red Train 2", redLine);
        enterprise.getTrainCatalog().addNewTrain("Red Train 3", redLine);
        enterprise.getTrainCatalog().addNewTrain("Red Train 4",redLine);
        enterprise.getTrainCatalog().addNewTrain("Red Train 5",redLine);
        enterprise.getTrainCatalog().addNewTrain("Red Train 6",redLine);
        enterprise.getTrainCatalog().addNewTrain("Red Train 7",redLine);
        enterprise.getTrainCatalog().addNewTrain("Red Train 8",redLine);
        
        enterprise.getTrainCatalog().addNewTrain("Blue Train 1",blueLine);
        enterprise.getTrainCatalog().addNewTrain("Blue Train 2",blueLine);
        enterprise.getTrainCatalog().addNewTrain("Blue Train 3",blueLine);
        enterprise.getTrainCatalog().addNewTrain("Blue Train 4",blueLine);
        
        enterprise.getTrainCatalog().addNewTrain("Orange Train 1",orangeLine);
        enterprise.getTrainCatalog().addNewTrain("Orange Train 2",orangeLine);
        enterprise.getTrainCatalog().addNewTrain("Orange Train 3",orangeLine);
        enterprise.getTrainCatalog().addNewTrain("Orange Train 4",orangeLine);
        
        Station A = enterprise.getStationCatalog().addStation("Heath St");
        A.setStationPopularity(Station.Popularity.LOW_RIDERSHIP);
        Station B = enterprise.getStationCatalog().addStation("Northeastern");
        B.setStationPopularity(Station.Popularity.LOW_RIDERSHIP);
        Station C = enterprise.getStationCatalog().addStation("BrainTree");
        C.setStationPopularity(Station.Popularity.LOW_RIDERSHIP);
        Station D  = enterprise.getStationCatalog().addStation("Quincy Cntr");
        D.setStationPopularity(Station.Popularity.HIGH_RIDERSHIP);
        Station E  = enterprise.getStationCatalog().addStation("Bowdoin");
        E.setStationPopularity(Station.Popularity.HIGH_RIDERSHIP);
        Station F  = enterprise.getStationCatalog().addStation("Acquarium");
        F.setStationPopularity(Station.Popularity.LOW_RIDERSHIP);
        Station G  = enterprise.getStationCatalog().addStation("Maverick");
        G.setStationPopularity(Station.Popularity.LOW_RIDERSHIP);
        Station H  = enterprise.getStationCatalog().addStation("Wonderland");
        H.setStationPopularity(Station.Popularity.HIGH_RIDERSHIP);
        Station I  = enterprise.getStationCatalog().addStation("Downtown Crossing");
        I.setStationPopularity(Station.Popularity.HIGH_RIDERSHIP);
        Station J  = enterprise.getStationCatalog().addStation("Harvard");
        J.setStationPopularity(Station.Popularity.LOW_RIDERSHIP);
        Station K  = enterprise.getStationCatalog().addStation("K");
        K.setStationPopularity(Station.Popularity.LOW_RIDERSHIP);
        Station L  = enterprise.getStationCatalog().addStation("Park St");
        L.setStationPopularity(Station.Popularity.HIGH_RIDERSHIP);
        Station M  = enterprise.getStationCatalog().addStation("Haymarket");
        M.setStationPopularity(Station.Popularity.HIGH_RIDERSHIP);
        Station N  = enterprise.getStationCatalog().addStation("Lechmere");
        N.setStationPopularity(Station.Popularity.HIGH_RIDERSHIP);
        Station O  = enterprise.getStationCatalog().addStation("OakGrove");
        O.setStationPopularity(Station.Popularity.HIGH_RIDERSHIP);
        Station P  = enterprise.getStationCatalog().addStation("P");
        P.setStationPopularity(Station.Popularity.LOW_RIDERSHIP);
        Station Q  = enterprise.getStationCatalog().addStation("Q");        
        Q.setStationPopularity(Station.Popularity.HIGH_RIDERSHIP);
        Station R  = enterprise.getStationCatalog().addStation("R");
        R.setStationPopularity(Station.Popularity.LOW_RIDERSHIP);
     
        //enterprise.getLineCatalog().getExistingLine("Green Line").setStartStation(A);
        
        greenLine.setStartStation(A);        
        greenLine.setEndStation(B);        
        greenLine.addSegment(greenLine.getStartStation(), greenLine.getEndStation());
        greenLine.extendSegment(L);
        greenLine.extendSegment(M);
        greenLine.extendSegment(N);
        
        redLine.setStartStation(C);
        redLine.setEndStation(D);
        redLine.addSegment(redLine.getStartStation(), redLine.getEndStation());
        redLine.extendSegment(I);
        redLine.extendSegment(L);
        redLine.extendSegment(J);
        
        blueLine.setStartStation(E);
        blueLine.setEndStation(F);
        blueLine.addSegment(blueLine.getStartStation(), blueLine.getEndStation());
        blueLine.extendSegment(G);
        blueLine.extendSegment(L);
        blueLine.extendSegment(H);
        
        orangeLine.setStartStation(G);
        orangeLine.setEndStation(I);
        orangeLine.addSegment(orangeLine.getStartStation(), orangeLine.getEndStation());
        orangeLine.extendSegment(M);
        orangeLine.extendSegment(O);
        
        
        for(Train t : enterprise.getTrainCatalog().getTrainsList()){
            Random randomNumber = new Random();
            int restRoomCar = randomNumber.nextInt(2) + 1;     
            int bicycleCar = randomNumber.nextInt(2) + 0;
            for(int i =0 ; i <3 ; i++){
                TrainCar tc = new TrainCar(i+1);                
                tc.setNormalSeatCapacity(80);
                tc.setPackagedCapacity(20);
                tc.setReservedSeats(10);   
                tc.updateMaximunCapacity();                
                t.getTrainCars().add(tc);  
                
                if(restRoomCar >=0){
                    tc.setIsRestroomPresent(true);  
                    restRoomCar--;
                }
                
                if(bicycleCar >=0){
                   tc.setIsBicyclesSkisAllowed(true);
                   bicycleCar--;
                }                
            }   
        }
     
      enterprise.setSchedule(greenLine); 

    }
    
   
}

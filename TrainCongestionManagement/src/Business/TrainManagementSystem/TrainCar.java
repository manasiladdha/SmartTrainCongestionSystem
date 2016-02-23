/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.TrainManagementSystem;

/**
 *
 * @author Manasi Laddha
 */
public class TrainCar {
    
    private int carNumber;    
    private TrainCarReport report;    
    private int normalSeatCapacity;
    private int packagedCapacity;
    private int reservedSeats;
    private int maximumCapacity;
    private boolean isRestroomPresent;
    private boolean isBicyclesSkisAllowed;
           
    public TrainCar(int carNumber) {
       this.carNumber = carNumber;
       report = new TrainCarReport();
    }

    public int getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(int reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    public int getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(int carNumber) {
        this.carNumber = carNumber;
    }

    public int getNormalSeatCapacity() {
        return normalSeatCapacity;
    }

    public void setNormalSeatCapacity(int normalSeatCapacity) {
        this.normalSeatCapacity = normalSeatCapacity;
    }

    public int getMaximumCapacity() {
        return maximumCapacity;
    }

    public void setMaximumCapacity(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }
    
    public void updateMaximunCapacity(){
        this.maximumCapacity = this.normalSeatCapacity + this.packagedCapacity + this.reservedSeats;
    }
    
    public TrainCarReport getReport() {
        return report;
    }

    public void setReport(TrainCarReport report) {
        this.report = report;
    }

    public int getPackagedCapacity() {
        return packagedCapacity;
    }

    public void setPackagedCapacity(int packagedCapacity) {
        this.packagedCapacity = packagedCapacity;
    }
    
    
    
    public void generateReport(TrainCar tc, TrainCar actualTc){
        
        //************SEAT CORRECTION*****************
        int defaultSeatsCapacity = actualTc.getNormalSeatCapacity();
        int defaultSpaceCapacity = actualTc.getPackagedCapacity();
        int defaultReservedCapacity = actualTc.getReservedSeats();
        
        int currentSeatsFilled = tc.getNormalSeatCapacity();
        int currentSpaceFilled = tc.getPackagedCapacity();
        int currentReservedFilled = tc.getReservedSeats();
        
//        System.out.println("currentSeatsFilled :" + currentSeatsFilled);
//        System.out.println("currentSpaceFilled :" + currentSpaceFilled);
//        System.out.println("currentReservedFilled :" + currentReservedFilled);
        
        int seatDiff = defaultSeatsCapacity - currentSeatsFilled;
           if(seatDiff >= currentSpaceFilled){
               currentSeatsFilled = currentSeatsFilled + currentSpaceFilled;
               currentSpaceFilled = 0;            
           }
           else {
               currentSeatsFilled = defaultSeatsCapacity;
               currentSpaceFilled = currentSpaceFilled - seatDiff;
           }
           
//        System.out.println("currentSeatsFilled :" + currentSeatsFilled);
//        System.out.println("currentSpaceFilled :" + currentSpaceFilled);
//        System.out.println("currentReservedFilled :" + currentReservedFilled);
    
        tc.setNormalSeatCapacity(currentSeatsFilled);
        tc.setPackagedCapacity(currentSpaceFilled);
        
        int empty = defaultSeatsCapacity - currentSeatsFilled;
        report.setEmptyNormalSeats(empty);
        
        empty = defaultReservedCapacity - currentReservedFilled;
        report.setEmptyReservedSeats(empty);
        
        empty = defaultSpaceCapacity - currentSpaceFilled;
        report.setEmptySpaceToStand(empty);
        
        updateMaximunCapacity();
        
    }

    public boolean isIsRestroomPresent() {
        return isRestroomPresent;
    }

    public void setIsRestroomPresent(boolean isRestroomPresent) {
        this.isRestroomPresent = isRestroomPresent;
    }

    public boolean isIsBicyclesSkisAllowed() {
        return isBicyclesSkisAllowed;
    }

    public void setIsBicyclesSkisAllowed(boolean isBicyclesSkisAllowed) {
        this.isBicyclesSkisAllowed = isBicyclesSkisAllowed;
    }
    
    
}

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
public class TimeSlot {
    
    private TimeSlotRange slot;
    private boolean timeSlotTaken;

    public TimeSlot(){
        //this.slot = slot;
    }

    public TimeSlotRange getSlot() {
        return slot;
    }

    public void setSlot(TimeSlotRange slot) {
        this.slot = slot;
    }

    public boolean isTimeSlotTaken() {
        return timeSlotTaken;
    }

    public void setTimeSlotTaken(boolean timeSlotTaken) {
        this.timeSlotTaken = timeSlotTaken;
    }
    
    
    public enum TimeSlotRange {
        TimeSlot1("6:00 to 7:00"),
        TimeSlot2("7:10 to 8:10"),
        TimeSlot3("8:20 to 9:20"),
        TimeSlot4("9:30 to 10:30"),
        TimeSlot5("10:40 to 11:40"),
        TimeSlot6("11:50 to 12:50"),
        TimeSlot7("13:00 to 14:00"),
        TimeSlot8("14:10 to 15:10"),
        TimeSlot9("15:20 to 16:20"),
        TimeSlot10("16:30 to 17:30"),
        TimeSlot11("17:40 to 18:40"),
        TimeSlot12("18:50 to 19:50"),
        TimeSlot13("20:00 to 21:00"),
        TimeSlot14("21:10 to 22:10"),
        TimeSlot15("22:20 to 23:20");

        
        private String timeRange;
        private TimeSlotRange(String timeRange){
            this.timeRange = timeRange;
        }
        
        public String getValue(){
            return this.timeRange;
        }

        @Override
        public String toString() {
            return timeRange ;
        }
        
        
        
    }
    
    
 
    
    
    
//      public boolean isTimeSlotMatch(TimeSlot ts1){
//          try {
//
//          
//          if (startTime.equals(ts1.startTime) || endTime.equals(ts1.endTime)){
//              return true;
//          }
//          
//          SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
//          Date parsedStartDate1 = dateFormat.parse(startTime);
//          Date parsedStartDate2 = dateFormat.parse(ts1.startTime);
//          
//          Date parsedEndDate1 = dateFormat.parse(endTime);
//          Date parsedEndDate2 = dateFormat.parse(ts1.endTime);
//          
//          Timestamp startTimeStamp1 = new Timestamp(parsedStartDate1.getTime());
//          Timestamp startTimeStamp2 = new Timestamp(parsedStartDate2.getTime());
//          
//          Timestamp endTimestamp1 = new Timestamp(parsedEndDate1.getTime());
//          Timestamp endTimestamp2 = new Timestamp(parsedEndDate2.getTime());
//          
//          
//          
//          if ((startTimeStamp2.before(endTimestamp1))) {
//                  
////                  || (  
////                  startTimeStamp1.before(endTimestamp2)
////                  
////                  ))
////                  
////                  &&  
////                  && endTimestamp2.before(endTimestamp1)
////                  && endTimestamp2.after(startTimeStamp1)) {
////              
//              return true;
//          
//          }
//          }catch (Exception e){
//             return true;
//          }            
//          return false;
//      }

    @Override
    public String toString() {
        return  slot.getValue();
    }
    
    
    
    
    
}

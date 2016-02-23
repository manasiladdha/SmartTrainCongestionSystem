/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import Business.TrainManagementSystem.Line;
import java.util.Date;

/**
 *
 * @author Manasi Laddha
 */
public class AlertWorkRequest  extends WorkRequest{
    
    private String routeName;
    private String timeSlot;
    private String trainName;
    private Line alertedline;
    private String result;
    private Date alertDate;
  

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public Line getAlertedline() {
        return alertedline;
    }

    public void setAlertedline(Line alertedline) {
        this.alertedline = alertedline;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getAlertDate() {
        return alertDate;
    }

    public void setAlertDate(Date alertDate) {
        this.alertDate = alertDate;
    }


    
}

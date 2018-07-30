/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activityplaec;

import java.util.Date;

/**
 *
 * @author Student
 */
public class Reservation {
   private int id;
   static int runningID;
   private Date reservationDate;
   private Date startDate;
   private Date endDate;
   private Place researvedPlace;
   private Student reservingStudent;

    public Reservation(Date reservationDate, Date startDate, Date endDate, Place researvedPlace, Student reservingStudent) {
        this.id = runningID;
        runningID++;
        this.reservationDate = reservationDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.researvedPlace = researvedPlace;
        this.reservingStudent = reservingStudent;
    }

    public Student getReservingStudent() {
        return reservingStudent;
    }
    public void setReservingStudent(Student reservingStudent) {
        this.reservingStudent = reservingStudent;
    }
    public int getId() {
        return id;
    }  

    public Date getReservationDate() {
        return reservationDate;
    }
    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public Place getResearvedPlace() {
        return researvedPlace;
    }
    public void setResearvedPlace(Place researvedPlace) {
        this.researvedPlace = researvedPlace;
    }
    
   
   
}

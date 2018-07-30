/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activityplaec;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Student
 */
public class ActivityPlaec {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Place p1 = new Place("SIT lawn", 15, 70);
        System.out.println(p1.getCapacity()+" "+p1.size());
        p1.setLength(100);
        System.out.println(p1.getCapacity()+" "+p1.size());
        
        Student s1 = new Student(1, "Aaron", "SIT");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");	
        Date d1;
        try {
            d1 = sdf.parse("31/05/2018");             
            Reservation r1 = new Reservation( d1 , sdf.parse("15/06/2018"), 
                sdf.parse("17/06/2018"), p1, s1);
            System.out.println(r1.getReservationDate().toString());
            Reservations rList = new Reservations();
            rList.add(r1);
             r1 = new Reservation( sdf.parse("19/05/2018") , sdf.parse("19/06/2018"), 
                sdf.parse("20/06/2018"), p1, s1);
            rList.add(r1);
            rList.add(sdf.parse("21/02/2018"), sdf.parse("04/04/2018"), 
                    sdf.parse("09/04/2018"), p1, s1);            
            rList.add(sdf.parse("21/02/2018"), sdf.parse("14/04/2018"), 
                    sdf.parse("19/04/2018"), new Place("Engineering space", 10, 12), 
                    new Student(2, "Tone", "Science"));
            rList.add(sdf.parse("12/02/2018"), sdf .parse("20/04/2018"), 
                    sdf.parse("25/04/2018"), new Place("Engineering space", 10, 12), 
                    new Student(2, "Tone", "Science"));         
            rList.add(sdf.parse("01/02/2018"), sdf.parse("24/04/2018"), 
                    sdf.parse("28/04/2018"), new Place("Engineering space", 10, 12), 
                    new Student(3, "one", "Science"));
               rList.printAllReservations();
               
            Students stList = new Students();
            stList.addNewStudent(101, "Mod", "SIT");
            stList.addNewStudent(102, "Cartoon", "Physical Education");
            stList.addNewStudent(103, "Bam", "Medicine");
            stList.addNewStudent(102, "Tiger", "MI:3");
            stList.addNewStudent(105, "Ball", "Science");
            stList.addNewStudent(103, "Odd", "Rocket Engineering");
            stList.printAllStudents();
            stList.addNewStudent(104, "Chanut", "IT");
            stList.printAllStudents();
            int newStudents = stList.addStudentsFromFile("reservePlace2.csv");
            System.out.println("There are "+newStudents+" new students added.");
            ArrayList<Place> placeList = new ArrayList<Place>();
            placeList.add(new Place("Science Field",20,30));
            placeList.add(new Place("1st Floor Engineer Building",60,80));
            placeList.add(new Place("FIBO Field",100,165));
            placeList.add(new Place("Front of SIT",15,30));
            int newReserve = rList.addReservationsFromFile("reservePlace3.csv", placeList);
            System.out.println("There are "+newReserve+" new reservations.");
            System.out.println(rList.reserveList.size());
            rList.writeToRandomAccessFile("reservationRandomAcccessFile");
            rList.searchReservationByStudentName("Tom","reservationRandomAcccessFile");
            
         } catch (ParseException ex) {
            Logger.getLogger(ActivityPlaec.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
}

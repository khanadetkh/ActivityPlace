/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activityplaec;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Student
 */
public class Reservations {

    ArrayList<Reservation> reserveList;
    ArrayList<StudentNameIndex> stIndex;

    public Reservations() {
        reserveList = new ArrayList<Reservation>();
        stIndex = new ArrayList<StudentNameIndex>();
    }

    public boolean add(Reservation r) {
        boolean found = false;
        for (Reservation res : reserveList) {
            if (res.getResearvedPlace().getName()
                    .compareToIgnoreCase(r.getResearvedPlace().getName()) == 0) {
                if ((r.getStartDate().after(res.getStartDate())
                        && r.getStartDate().before(res.getEndDate()))
                        || (r.getEndDate().after(res.getStartDate())
                        && r.getEndDate().before(res.getEndDate()))
                        || (r.getStartDate().compareTo(res.getStartDate()) == 0)
                        || (r.getStartDate().compareTo(res.getEndDate()) == 0)
                        || (r.getEndDate().compareTo(res.getStartDate()) == 0)
                        || (r.getEndDate().compareTo(res.getEndDate()) == 0)) {
                    found = true;
                    System.out.println(r.getResearvedPlace().getName()
                            + " has been reserved on "
                            + r.getStartDate().getDate() + "/" + (r.getStartDate().getMonth() + 1)
                            + " already. Please try again on different place or "
                            + "different date!");
                    System.out.println(res.getStartDate().toString());
                    System.out.println(res.getEndDate().toString());
                    break;
                }
            }
        }
        if (!found) {
            reserveList.add(r);
            return true;
        }
        return false;
    }

    public boolean add(Date resDate, Date stDate, Date endDate, Place p, Student d) {
        Reservation res = new Reservation(resDate, stDate, endDate, p, d);
        return this.add(res);
    }

    public int addReservationsFromFile(String filename, ArrayList<Place> pList) {
        int count = 0;
        try {
            FileReader freader = new FileReader(filename);
            Scanner in = new Scanner(freader);
            String line = in.nextLine();
            String[] dataInLine = new String[9];
            while (in.hasNextLine()) {
                line = in.nextLine();
                dataInLine = line.split(",");
                Place p = null;
                for (Place p2 : pList) {
                    if (p2.getName().compareToIgnoreCase(dataInLine[3]) == 0) {
                        p = p2;
                    }
                }
                Student s = new Student(Integer.parseInt(dataInLine[0]), dataInLine[1], dataInLine[2]);
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                Date reserveDate = sdf.parse(dataInLine[4]);
                Date startDate = sdf.parse(dataInLine[5]);
                Date endDate = sdf.parse(dataInLine[7]);
                if (this.add(reserveDate, startDate, endDate, p, s)) {
                    count++;
                };
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public void printAllReservations() {
        for (Reservation res : reserveList) {
            System.out.println(res.getResearvedPlace().getName() + " from "
                    + res.getStartDate().toString() + " to "
                    + res.getEndDate().toString());
        }
    }

    public void writeToRandomAccessFile(String filename) {
        try {
            // resID + placename + width + length + capcacity + reDate + 
            //   stDate + endDate + studentid + st_name + faculty
            // 4 + 50 + 8 + 8 + 4 + 10 + 10 +10 +4 + 22 + 44
            // = 174
            RandomAccessFile fPointer = new RandomAccessFile(filename, "rw");
            int recordNumber = 0;
            for (Reservation res : reserveList) {
                fPointer.writeInt(res.getId());//resID
                String st = res.getResearvedPlace().getName();
                st += "                                                      ";
                fPointer.write(st.getBytes(), 0, 50); //placename
                fPointer.writeDouble(res.getResearvedPlace().getWidth());//width
                fPointer.writeDouble(res.getResearvedPlace().getLength());//length
                fPointer.writeInt(res.getResearvedPlace().getCapacity());//capacity
                st = "" + res.getReservationDate().getDate() + "/";
                st += (res.getReservationDate().getMonth() + 1) + "/";
                st += res.getReservationDate().getYear() + 1900;
                st += "             ";
                fPointer.write(st.getBytes(), 0, 10);  //reservation date
                st = "" + res.getStartDate().getDate() + "/";
                st += (res.getStartDate().getMonth() + 1) + "/";
                st += res.getStartDate().getYear() + 1900;
                st += "             ";
                fPointer.write(st.getBytes(), 0, 10);  //start date
                st = "" + res.getEndDate().getDate() + "/";
                st += (res.getEndDate().getMonth() + 1) + "/";
                st += res.getEndDate().getYear() + 1900;
                st += "             ";
                fPointer.write(st.getBytes(), 0, 10);  //end date
                fPointer.writeInt(res.getReservingStudent().getId());//studentid
                st = res.getReservingStudent().getName();
                st += "                            ";
                fPointer.write(st.getBytes(), 0, 22); //student name
                st = res.getReservingStudent().getFaculty();
                st += "                                                        ";
                fPointer.write(st.getBytes(), 0, 44); //Faculty     
                stIndex.add(new StudentNameIndex(recordNumber,
                        res.getReservingStudent().getName()));
                recordNumber++;
            }
            fPointer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchReservationByStudentNameUsingIndex(String stname, String filename) {

    }

    public void searchReservationByStudentName(String stname, String filename) {
        try {
            // resID + placename + width + length + capcacity + reDate + 
            //   stDate + endDate + studentid + st_name + faculty
            // 4 + 50 + 8 + 8 + 4 + 10 + 10 +10 +4 + 22 + 44
            // = 174
            RandomAccessFile fPointer = new RandomAccessFile(filename, "r");
            byte[] studentName = new byte[22];
            byte[] startDate = new byte[10];
            byte[] endDate = new byte[10];
            long position = 108;
            fPointer.seek(position);
            while (fPointer.getFilePointer() <= fPointer.length()) {
                fPointer.read(studentName, 0, 22);
                String studentInString = new String(studentName);
                studentInString = studentInString.substring(0, stname.length());
                if (studentInString.compareToIgnoreCase(stname) == 0) {
                    position = fPointer.getFilePointer()-(10+10+4+22);
                    fPointer.seek(position);
                    fPointer.read(startDate, 0, 10);
                    String stDate = new String(startDate);
                    fPointer.read(endDate, 0, 10);
                    String eDate = new String(endDate);
                    System.out.println(studentInString + " " + stDate + " " + eDate);
                    fPointer.seek(fPointer.getFilePointer()+174+4);
                } else {
                    fPointer.seek(fPointer.getFilePointer()+(174-22));
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

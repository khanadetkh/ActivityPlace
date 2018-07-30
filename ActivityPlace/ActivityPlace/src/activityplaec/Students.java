/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activityplaec;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Student
 */
public class Students {
    ArrayList<Student> studentList;
    public Students() {
        studentList = new ArrayList<Student>();    
    }
    public int addNewStudent(int id, String name, String faculty){
        for (Student st : studentList) {
            if(st.getId()==id){
                return studentList.size();
            }
        }
        studentList.add(new Student(id, name, faculty));
        return studentList.size();
    }
    public void deleteStudent(int id){
        for (Student st : studentList) {
            if(st.getId()==id){
                studentList.remove(st);
            }
        }
    }
    public void printAllStudents(){
        for (Student stu : studentList) {
            System.out.println(stu.getId()+" "+stu.getName()+" "+stu.getFaculty());
        }
    }
    public void sortStudentList(){        
        
    }
    public int addStudentsFromFile(String fileName){
        int count=0;
        try {
            FileReader fr = new FileReader(fileName);
            Scanner input = new Scanner(fr);
            String content = input.nextLine();
            String[] dataEachLine = new String[9];            
            while (input.hasNextLine()){
                content = input.nextLine();                
                dataEachLine = content.split(",");
                //System.out.println(dataEachLine[0]+" "+dataEachLine[1]+" "+dataEachLine[2]);
                int oldSize=studentList.size();
                int newSize;
                newSize=addNewStudent( Integer.parseInt(dataEachLine[0]),dataEachLine[1],
                        dataEachLine[2] );
                if(oldSize!=newSize){
                    count++;
                }
            }            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
}

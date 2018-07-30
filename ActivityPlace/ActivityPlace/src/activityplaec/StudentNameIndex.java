/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activityplaec;

/**
 *
 * @author Student
 */
public class StudentNameIndex {
    private int recordNumber;
    private String studentName;

    public StudentNameIndex(int recordNumber, String studentName) {
        this.recordNumber = recordNumber;
        this.studentName = studentName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }
    
}

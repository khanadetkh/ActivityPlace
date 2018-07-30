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
public class Place {
    private String name;
    private double width;
    private double length;
    private int capacity;

    public Place(String name, double width, double length) {
        this.name = name;
        this.width = width;
        this.length = length;
        setCapacity();
    }

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity() {
        this.capacity = (int) size()/4;                
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
        setCapacity();
    }

    public double getLength() {
        return length;
    }
    public void setLength(double length) {
        this.length = length;
        setCapacity();
    }
    public double size(){
        return length*width;
    }
}

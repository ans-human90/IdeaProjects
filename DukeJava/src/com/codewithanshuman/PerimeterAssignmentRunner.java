package com.codewithanshuman;
import edu.duke.*;
import java.io.*;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int count = 0;
        for(Point currPt : s.getPoints()){
            count = count + 1;
        }
        return count;
    }

    public double getAverageLength (Shape s) {
        double avg = 0.0;
        avg = (getPerimeter(s))/((getNumPoints(s)));
        return avg;
    }

    public double getLargestSide (Shape s) {
        double largestSide = 0.0;
        Point prevPt = s.getLastPoint();
        for ( Point currPt : s.getPoints()){
            double currDist = prevPt.distance(currPt);
            if(largestSide < currDist){
                largestSide = currDist;
            }
            prevPt = currPt;
        }
        return largestSide;
    }

    public double getLargestX (Shape s) {
        int largestX = 0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()){
            if( largestX < currPt.getX()){
                largestX = currPt.getX();
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        double largestPerimMF = 0.0;
        DirectoryResource drf = new DirectoryResource();
        for (File fl : drf.selectedFiles()) {
            FileResource mf = new FileResource(fl);
            Shape s = new Shape(mf);
            double currPerimeter = getPerimeter(s);
            if ( largestPerimMF < currPerimeter){
                largestPerimMF = currPerimeter;
            }
        }
        return largestPerimMF;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int numOfPoints = getNumPoints(s);
        double avgLen = getAverageLength(s);
        double largestSide = getLargestSide(s);
        double largestX = getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("Count = " + numOfPoints);
        System.out.println("Avg Length = " + avgLen);
        System.out.println("Largest Side = " + largestSide);
        System.out.println("Largest x = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("Largest Perimeter form MultipleFiles = " + largestPerimeter);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

   public static void main (String[] args) {
         PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
         pr.testPerimeter();
    }
}

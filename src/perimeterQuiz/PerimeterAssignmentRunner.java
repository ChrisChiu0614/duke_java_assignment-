package src.perimeterQuiz;

import edu.duke.*;

import java.io.File;

public class PerimeterAssignmentRunner {

    public double getPerimeter(Shape s) {
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

    public int getNumPoints(Shape s) {
        // Put code here
        int counter = 0;
        for (Point p : s.getPoints()) {
            counter++;
        }
        return counter;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double len = getPerimeter(s);
        double num = (int) getNumPoints(s);
        return len / num;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        // Start with largestSide = 0
        double largestSide = 0.0;
        // Start wth prevPt = the last point
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            double currDist = prevPt.distance(currPt);
            // Update prevPt to be currPt
            prevPt = currPt;
            // compare side
            if (currDist > largestSide) {
                largestSide = currDist;
            }
        }
        // largestSide is the answer
        return largestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double largestX = 0.0;
        for (Point p : s.getPoints()) {
            double x = (double) p.getX();
            if (x > largestX) {
                largestX = x;
            }
        }

        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        DirectoryResource dr = new DirectoryResource();

        double largestPerimeter = 0.0;

        for (File f : dr.selectedFiles()) {
            System.out.println(f);
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perimeter = pr.getPerimeter(s);
            if (perimeter > largestPerimeter) {
                largestPerimeter = perimeter;
            }

        }
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0.0;

        for (File f : dr.selectedFiles()) {
            System.out.println(f);
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perimeter = pr.getPerimeter(s);
            if (perimeter > largestPerimeter) {
                largestPerimeter = perimeter;
                temp = f;
            }

        }


        return temp!=null?temp.getName():"";
    }

    public void testPerimeter() {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int num = getNumPoints(s);
        System.out.println("num = " + num);
        double avg = getAverageLength(s);
        System.out.println("avg = " + avg);
        double largestSide = getLargestSide(s);
        System.out.println("largest Side = " + largestSide);
        double largestX = getLargestX(s);
        System.out.println("largest X = " + largestX);


    }

    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("largest Perimeter = " + largestPerimeter);

    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String largestFileName = getFileWithLargestPerimeter();
        System.out.println("largest File Name = " + largestFileName);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle() {
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0, 0));
        triangle.addPoint(new Point(6, 0));
        triangle.addPoint(new Point(3, 6));
        for (Point p : triangle.getPoints()) {
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = " + peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main(String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        //pr.testFileWithLargestPerimeter();
    }
}

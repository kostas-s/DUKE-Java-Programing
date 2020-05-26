import edu.duke.*;
import java.io.File;

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
        int numPoints = 0;
        
        for (Point p : s.getPoints()){
        numPoints++;
        }
              
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        int numPoints = getNumPoints(s);
        double currTotal = 0;
        Point prevPoint = s.getLastPoint();
    
        for (Point currPoint : s.getPoints()){
        double distance = currPoint.distance(prevPoint);
        currTotal += distance;
        prevPoint = currPoint;        
        }       
        return currTotal / numPoints;
    }

    public double getLargestSide(Shape s) {
        double currLargest=0;
        Point prevPoint = s.getLastPoint();
        for (Point currPoint : s.getPoints()){
        double distance = currPoint.distance(prevPoint);
        
        if (distance>currLargest){
            currLargest=distance;
        }
        prevPoint = currPoint;
   
        }
        return currLargest;
    }

    public double getLargestX(Shape s) {
        Point lastPoint = s.getLastPoint();
        double currLargestX = lastPoint.getX();
        
        for(Point currPoint : s.getPoints()){
        double currX = currPoint.getX();
        if (currX > currLargestX){
            currLargestX = currX;
        }
        }
        
        
        return currLargestX;
    }

    public double getLargestPerimeterMultipleFiles() {
             
        double largestPerimeter=0;
        
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            
            double currPerimeter = getPerimeter(s); 
            
            if (currPerimeter>largestPerimeter){
                largestPerimeter = currPerimeter;
            }     
                      
        }
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        
        double largestPerimeter=0;
        File answer = null;
        
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            
            double currPerimeter = getPerimeter(s); 
            
            if (currPerimeter>largestPerimeter){
                largestPerimeter = currPerimeter;
                answer = f;
            }     
        
        }
        
        
        return answer.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);

        int numPoints = getNumPoints(s);
        System.out.println("number of points = " + numPoints);
        
        double avgLength = getAverageLength(s);
        System.out.println("average side length = " + avgLength);
        
        double largestSide = getLargestSide(s);
        System.out.println("largest side is = " + largestSide);

        double largestX = getLargestX(s);
        System.out.println("largest X is = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeter between selected files = " + largestPerimeter);
    }

    public void testFileWithLargestPerimeter() {
        String fileName = getFileWithLargestPerimeter();
        System.out.println("file with largest perimeter is = " + fileName);
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

public class DistanceFilter implements Filter{
    Location loc;
    double maxDistance;
    public DistanceFilter(Location passedLoc, double dist){
        loc = passedLoc;
        maxDistance = dist;
    }
    public boolean satisfies(QuakeEntry qe){
     return (loc.distanceTo(qe.getLocation())<maxDistance);   
    }
    public String getName(){
    return "Distance";
    }
}

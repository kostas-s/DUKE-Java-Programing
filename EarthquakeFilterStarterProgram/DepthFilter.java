public class DepthFilter implements Filter {
    double minDepth;
    double maxDepth;
    public DepthFilter(double min, double max){
     minDepth = min;
     maxDepth = max;
    }
    public boolean satisfies(QuakeEntry qe){
     return (qe.getDepth()>=minDepth && qe.getDepth()<=maxDepth);   
    }
    public String getName(){
    return "Depth";
    }
}

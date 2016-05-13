package Dijkstra;
import PQHeap.FLandWP;


public class vertex {

    private FLandWP FLandWP;
    private Double cost;
    private vertex predecessor = null;

    public vertex(Integer FL, Integer WP) {
        this.FLandWP = new FLandWP(FL,WP);
        this.cost = Double.POSITIVE_INFINITY;
    }

    /**
     * checks the position of a given flight level compared to the current flight level
     * returns 1 if given flight level is greater than current
     * returns 0 if given flight level is the same
     * returns -1 if given flight level is lower than current
     * @param FL
     * @return
     */
    public int FLPosition(Integer FL){
        if(this.FLandWP.getFL() > FL){
            return 1;
        }else if(this.FLandWP.getFL() == FL){
            return 0;
        }else{
            return -1;
        }
    }



    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public vertex getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(vertex predecessor) {
        this.predecessor = predecessor;
    }

    public PQHeap.FLandWP getFLandWP() {
        return FLandWP;
    }
}

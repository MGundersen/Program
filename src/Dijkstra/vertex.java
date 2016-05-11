package Dijkstra;
import PQHeap.FLandWP;


public class vertex {

    private FLandWP FLandWP;
    private Integer d;
    private vertex predecessor = null;

    public vertex(Integer FL, Integer WP) {
        this.FLandWP = new FLandWP(FL,WP);
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



    public Integer getD() {
        return d;
    }

    public void setD(Integer d) {
        this.d = d;
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

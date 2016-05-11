package Dijkstra;
import Distance.coordinate;


public class vertex {

    private coordinate coordinate;
    private Integer d;
    private Integer FL;
    private vertex predecessor = null;

    public vertex(Integer d, Integer FL) {
        this.d = d;
        this.FL = FL;
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
        if(this.FL > FL){
            return 1;
        }else if(this.FL == FL){
            return 0;
        }else{
            return -1;
        }
    }

    public coordinate getCoordinate() { return coordinate; }

    public void setCoordinate(coordinate c){ this.coordinate = c; }

    public Integer getD() {
        return d;
    }

    public void setD(Integer d) {
        this.d = d;
    }

    public Integer getFL() {
        return FL;
    }

    public void setFL(Integer FL) {
        this.FL = FL;
    }

    public vertex getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(vertex predecessor) {
        this.predecessor = predecessor;
    }
}

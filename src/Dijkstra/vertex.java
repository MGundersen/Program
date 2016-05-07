package Dijkstra;


public class vertex {

    private Integer d;
    private Integer FL;
    private vertex predecessor = null;

    public vertex(Integer d, Integer FL) {
        this.d = d;
        this.FL = FL;
    }

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

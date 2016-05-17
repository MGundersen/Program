package PQHeap;
import Dijkstra.vertex;

public interface PQ {
    public vertex extractMin();
    public void insert (vertex e);
    public vertex search (FLandWP e);
    public void remove (FLandWP fw);
}
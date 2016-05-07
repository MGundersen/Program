package PQHeap;
import Dijkstra.vertex;

public interface PQ {
    public vertex extractMin();
    public void insert (vertex e);
}
package PQHeap;

import Dijkstra.vertex;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by:
 * Kasper Skov Johansen (kajoh14@student.sdu.dk)
 * Morten Kristian Jaeger (mojae15@student.sdu.dk)
 */
public class PQHeap implements PQ {

    // Queue used for the heap
    public ArrayList<vertex> Queue;

    // Hashmap used for searching
    private HashMap<FLandWP, vertex> hm = new HashMap<>();

    // constructor
    public PQHeap(int MaxElements){
        this.Queue = new ArrayList<>(MaxElements);
    }

    /**
     * Extracts the element with least priority from a min-heap structurized heap. This is done by swapping the root element
     * with the last element in the heap and then removing the new last element. To maintain min-heap structure the new root element
     * is heapified.
     */
    @Override
    public vertex extractMin() {
        try{
            vertex min = Queue.get(0);
            vertex max = Queue.get(Queue.size()-1);
            Queue.set(0, max);
            Queue.remove(Queue.size()-1);
            Min_heapify(Queue, 0);
            return min;
        }catch(ArrayIndexOutOfBoundsException k){
            System.out.println("No elements in queue");
            return null;
        }
    }

    /**
     * Inserts an element e into the min-heap structurized ArrayList queue. The element e is added as the last element in queue
     * and therefore to maintain min-heap structure e is compared to its parent. If its smaller it swaps with its parent and is
     * then compared to its new parent. This is done until the element e is either larger than its parent or is the root.
     */
    @Override
    public void insert(vertex e) {
        FLandWP FLandWPKey = e.getFLandWP();
        hm.put(FLandWPKey,e);
        int i = Queue.size();
        try {
            Queue.add(e);
        } catch (ArrayIndexOutOfBoundsException k) {
            System.out.println("Array is out of bounds");
        }
        while (i > 0 && Queue.get(Parent(i)).getCost() >= Queue.get(i).getCost()) {
            Collections.swap(Queue, i, Parent(i));
            i = Parent(i);
        }
    }

    @Override
    public vertex search(FLandWP e) {
        for ( Map.Entry<FLandWP, vertex> s : hm.entrySet() ) {
            if (e.getWP() == s.getKey().getWP() && e.getFL() == s.getKey().getFL()) {
                return s.getValue();
            }
        }
        return null;
    }

    /**
     * Heapifies an object of type vertex at a given index i in a given arraylist A.
     */
    public void Min_heapify(ArrayList<vertex> A, int i){
        int l = Left(i);
        int r = Right(i);
        int smallest;
        if (l < A.size() && A.get(l).getCost() <= A.get(i).getCost()) {
            smallest = l;
        }
        else {
            smallest = i;
        }
        if (r < A.size() && A.get(r).getCost() <= A.get(smallest).getCost()) {
            smallest = r;
        }
        if (smallest != i) {
            Collections.swap(A, i, smallest);
            Min_heapify(A, smallest);
        }
    }

    /**
     * Returns the parent
     * Example using the root's left child: ceil(1/2.)-1 = 0, which is the root's index
     */
    private int Parent(int i){
        return (int)Math.ceil(i/2.)-1;
    }

    /**
     * Returns the left child in the 0-indexed queue
     * Example by using the root: (2*0)+1 = 1, which is the left child
     */
    private int Left(int i){
        return (int)Math.ceil(2*i)+1;
    }
    /**
     * Returns the right child in the 0-indexed queue
     * Example by using the root: (2*0)+2 = 2, which is the right child
     */
    private int Right(int i){
        return (int)Math.ceil((2*i)+2);
    }

    public boolean empty() {
        if (Queue.size() == 0) return true;
        else return false;
    }

    public void remove(FLandWP fw){
        for (int i = 0; i < Queue.size()-1; i++){
            if (Queue.get(i).getFLandWP().getFL() == fw.getFL() && Queue.get(i).getFLandWP().getWP() == fw.getWP()){
                Collections.swap(Queue, i, Queue.size()-1);
                Queue.remove(Queue.size()-1);
                Min_heapify(Queue, 0);
            }
        }
    }

}
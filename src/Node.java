/**
 * Created by MGund on 5/5/2016.
 */
public class Node {

    private Node parent;
    private Node child;
    private Integer cost;

    public Node(Node parent, Node child, Integer cost) {
        this.parent = parent;
        this.child = child;
        this.cost = cost;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getChild() {
        return child;
    }

    public void setChild(Node child) {
        this.child = child;
    }
}

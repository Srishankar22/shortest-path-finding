import java.util.HashMap;

public class Vertex {
    private int rowLocation;
    private int colLocation;
    private HashMap<Vertex,Integer> AdjacencyNodes;
    private int totalDistance;
    private int fCost;
    private Vertex previousVertex;

    public Vertex(int colLocation, int rowLocation) {
        this.colLocation = colLocation;
        this.rowLocation = rowLocation;
        this.totalDistance = Integer.MAX_VALUE;
        this.previousVertex= null;
        this.AdjacencyNodes = new HashMap<>();
    }

    // Getter and setter methods for totalDistance and nearestVertex
    public int getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(int totalDistance) {
        this.totalDistance = totalDistance;
    }

    public HashMap<Vertex, Integer> getAdjacencyNodes() {
        return AdjacencyNodes;
    }

    public int getRowLocation() {

        return rowLocation;
    }

    public void setRowLocation(int rowLocation) {

        this.rowLocation = rowLocation;
    }

    public int getColLocation() {

        return colLocation;
    }

    public void setColLocation(int colLocation) {

        this.colLocation = colLocation;
    }

    public void printAdjacencyNodes() {
        for (HashMap.Entry<Vertex, Integer> entry : AdjacencyNodes.entrySet()) {
            Vertex vertex = entry.getKey();
            int distance = entry.getValue();
            System.out.println("Adjacent vertex: " + vertex + ", Distance: " + distance);
        }
    }
    public void addOrUpdateAdjacencyNode(Vertex vertex, int distance) {

        AdjacencyNodes.put(vertex, distance);
    }

    public Vertex getPreviousVertex() {
        return previousVertex;
    }

    public void setPreviousVertex(Vertex previousVertex) {
        this.previousVertex = previousVertex;
    }

    public int getfCost() {
        return fCost;
    }

    public void setfCost(int fCost) {
        this.fCost = fCost;
    }

    @Override
    public String toString() {

        return "[" + (colLocation+1) + "," + (rowLocation+1) + "]";
    }

}

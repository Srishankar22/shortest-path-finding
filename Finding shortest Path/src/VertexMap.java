import java.util.ArrayList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Comparator;

public class VertexMap {
    ArrayList<Vertex> visited = new ArrayList<>();
    ArrayList<Vertex> shortestPath = new ArrayList<>();
    Vertex source;
    Vertex endPoint;
    int count = 1;


    public void vertexCreate(int[][] map) {

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {

                if (map[i][j] == 2) {
                    source = new Vertex(j, i);
                }
                if (map[i][j] == 3) {
                    endPoint = new Vertex(j, i);
                }
            }
        }
    }

    public void distanceCal(int[][] map, Vertex currentVertex) {

        int downDistance = 0;
        int rightDistance = 0;
        int leftDistance = 0;
        int upDistance = 0;

        boolean foundDown = false;
        boolean foundRight = false;
        boolean foundLeft = false;
        boolean foundUp = false;

        for (int i = currentVertex.getRowLocation() + 1; i < map.length; i++) {

            if (map[i][currentVertex.getColLocation()] == 0) {
                downDistance++;
            } else if (map[i][currentVertex.getColLocation()] == 2 || map[i][currentVertex.getColLocation()] == 3) {
                downDistance++;

                Vertex newVertex = new Vertex(currentVertex.getColLocation(), i);
                currentVertex.addOrUpdateAdjacencyNode(newVertex, downDistance);

                foundDown = true;
                break;
            } else if (map[i][currentVertex.getColLocation()] == 1) {
                if (downDistance > 0) {
                    Vertex newVertex = new Vertex(currentVertex.getColLocation(), i - 1);
                    currentVertex.addOrUpdateAdjacencyNode(newVertex, downDistance);
                }
                foundDown = true;
                break;
            }
        }

        if (!foundDown) {
            downDistance = map.length - currentVertex.getRowLocation() - 1;
            if (downDistance > 0) {
                Vertex newVertex = new Vertex(currentVertex.getColLocation(), map.length - 1);
                currentVertex.addOrUpdateAdjacencyNode(newVertex, downDistance);
            }
        }

        for (int j = currentVertex.getColLocation() + 1; j < map[currentVertex.getRowLocation()].length; j++) {
            if (map[currentVertex.getRowLocation()][j] == 0) {
                rightDistance++;
            } else if (map[currentVertex.getRowLocation()][j] == 2 || map[currentVertex.getRowLocation()][j] == 3) {
                rightDistance++;
                Vertex newVertex = new Vertex(j, currentVertex.getRowLocation());
                currentVertex.addOrUpdateAdjacencyNode(newVertex, rightDistance);
                foundRight = true;
                break;

            } else if (map[currentVertex.getRowLocation()][j] == 1) {
                if (rightDistance > 0) {
                    Vertex newVertex = new Vertex(j - 1, currentVertex.getRowLocation());
                    currentVertex.addOrUpdateAdjacencyNode(newVertex, rightDistance);
                }
                foundRight = true;
                break;
            }
        }

        if (!foundRight) {
            rightDistance = map[currentVertex.getRowLocation()].length - currentVertex.getColLocation() - 1;
            if (rightDistance > 0) {
                Vertex newVertex = new Vertex(map[currentVertex.getRowLocation()].length - 1, currentVertex.getRowLocation());
                currentVertex.addOrUpdateAdjacencyNode(newVertex, rightDistance);
            }

        }


        for (int k = currentVertex.getColLocation() - 1; k >= 0; k--) {
            if (map[currentVertex.getRowLocation()][k] == 0) {
                leftDistance++;
            } else if (map[currentVertex.getRowLocation()][k] == 2 || map[currentVertex.getRowLocation()][k] == 3) {
                leftDistance++;
                Vertex newVertex = new Vertex(k, currentVertex.getRowLocation());
                currentVertex.addOrUpdateAdjacencyNode(newVertex, leftDistance);
                foundLeft = true;
                break;

            } else if (map[currentVertex.getRowLocation()][k] == 1) {
                if (leftDistance > 0) {
                    Vertex newVertex = new Vertex(k + 1, currentVertex.getRowLocation());
                    currentVertex.addOrUpdateAdjacencyNode(newVertex, leftDistance);
                }
                foundLeft = true;
                break;
            }
        }

        if (!foundLeft) {
            leftDistance = currentVertex.getColLocation();
            if (leftDistance > 0) {
                Vertex newVertex = new Vertex(0, currentVertex.getRowLocation());
                currentVertex.addOrUpdateAdjacencyNode(newVertex, leftDistance);
            }
        }


        for (int l = currentVertex.getRowLocation() - 1; l >= 0; l--) {
            if (map[l][currentVertex.getColLocation()] == 0) {
                upDistance++;
            } else if (map[l][currentVertex.getColLocation()] == 2 || map[l][currentVertex.getColLocation()] == 3) {
                upDistance++;
                Vertex newVertex = new Vertex(currentVertex.getColLocation(), l);
                currentVertex.addOrUpdateAdjacencyNode(newVertex, upDistance);
                foundUp = true;
                break;
            } else if (map[l][currentVertex.getColLocation()] == 1) {
                if (upDistance > 0) {
                    Vertex newVertex = new Vertex(currentVertex.getColLocation(), l + 1);
                    currentVertex.addOrUpdateAdjacencyNode(newVertex, upDistance);
                }
                foundUp = true;
                break;
            }
        }

        if (!foundUp) {
            upDistance = currentVertex.getRowLocation();
            if (upDistance > 0) {
                Vertex newVertex = new Vertex(currentVertex.getColLocation(), 0);
                currentVertex.addOrUpdateAdjacencyNode(newVertex, upDistance);
            }
        }
    }

    public void pathFinding(int[][] map){

        long startTime = System.currentTimeMillis();
        PriorityQueue<Vertex> minQueue = new PriorityQueue<>(Comparator.comparingInt(Vertex::getfCost));
        source.setTotalDistance(0);
        minQueue.add(source);


        Vertex vertex = null;
        boolean found = false;
        while(!minQueue.isEmpty()){
            vertex = minQueue.poll();
            visited.add(vertex);
            distanceCal(map, vertex);

            if(vertex.getColLocation() == endPoint.getColLocation() && vertex.getRowLocation() == endPoint.getRowLocation()){
                found = true;
                break;
            }

            for (Map.Entry<Vertex, Integer> entry : vertex.getAdjacencyNodes().entrySet()) {
                boolean found2 = false;
                for(Vertex vertex2:visited){
                    if(vertex2.getColLocation() == entry.getKey().getColLocation() && vertex2.getRowLocation() == entry.getKey().getRowLocation()) {
                        found2 = true;
                        break;
                    }
                }
                if(found2){
                    continue;
                }
                if(entry.getKey().getTotalDistance() > vertex.getTotalDistance() + entry.getValue()) {
                    entry.getKey().setTotalDistance(vertex.getTotalDistance() + entry.getValue());
                    entry.getKey().setfCost(entry.getKey().getTotalDistance() + (Math.abs(entry.getKey().getColLocation() - endPoint.getColLocation())) + (Math.abs(entry.getKey().getRowLocation() - endPoint.getRowLocation())));
                    entry.getKey().setPreviousVertex(vertex);
                    minQueue.add(entry.getKey());
                }
            }
        }

        long runTime = System.currentTimeMillis() - startTime;
        if(found) {
            // Loop to backtrack and reconstruct the shortest path
            while (true) {
                shortestPath.add(0,vertex);
                Vertex previousVertex = vertex.getPreviousVertex();
                vertex = previousVertex;
                if (previousVertex.getColLocation() == source.getColLocation() && previousVertex.getRowLocation() == source.getRowLocation()) {
                    shortestPath.add(0,vertex);
                    break;
                }
            }
        }
        else{
            System.out.println("No available path for the puzzle.");
        }




        if (!shortestPath.isEmpty()) {
            Vertex previousVertex = shortestPath.get(0);
            StringBuilder output = new StringBuilder(count + ". Start at " + previousVertex);
            count++;

            for (int i = 1; i < shortestPath.size(); i++) {
                Vertex currentVertex = shortestPath.get(i);

                if (currentVertex.getRowLocation() == previousVertex.getRowLocation()) {
                    output.append("\n").append(count).append(". ");
                    if (currentVertex.getColLocation() > previousVertex.getColLocation()) {
                        output.append("Move right to ");
                    } else {
                        output.append("Move left to ");
                    }
                    output.append(currentVertex);
                } else {
                    output.append("\n").append(count).append(". ");
                    if (currentVertex.getRowLocation() > previousVertex.getRowLocation()) {
                        output.append("Move down to ");
                    } else {
                        output.append("Move up to ");
                    }
                    output.append(currentVertex);
                }

                count++;
                previousVertex = currentVertex;
            }

            output.append("\n").append(count).append(". Done");
            System.out.println(output);
        }

       // System.out.println("Runtime : " + runTime + "ms");
    }
}
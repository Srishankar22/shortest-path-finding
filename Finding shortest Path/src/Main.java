import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter the file name: ");
        String fileName = input.next();

        mapInsert map = new mapInsert();
        VertexMap vertex = new VertexMap();
        int[][] mapArray = map.mapping(fileName);
        vertex.vertexCreate(mapArray);
        vertex.pathFinding(mapArray);

    }
}

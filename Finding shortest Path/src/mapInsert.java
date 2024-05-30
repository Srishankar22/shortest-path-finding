import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class mapInsert {

    public int[][] mapping(String filename){

        int [][] map = null;

        try {
            String filePath = "src/examples/"+filename+".txt";

            String line;
            int row = 0;
            int column = 0;

            // Open the file
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);

            // Read the file line by line
            while ((line = reader.readLine()) != null){
                row++;

                // Check if it's the first row
                if (row == 1) {
                    column = line.length(); // Set the number of columns based on the length of the first row
                }
            }

            // Reset the reader to read from the beginning of the file
            fileReader = new FileReader(filePath);
            reader = new BufferedReader(fileReader);

            // Initialize the 2D array
            map = new int[row][column];

            // Read the file again to populate the 2D array
            row = 0;
            while ((line = reader.readLine()) != null) {

                if (line.length() == 0) {
                    continue; // Skip empty lines
                }

                for (int col = 0; col < column; col++) {
                    char ch = line.charAt(col);
                    switch (ch) {
                        case '.':
                            map[row][col] = 0;
                            break;
                        case '0':
                            map[row][col] = 1;
                            break;
                        case 'S':
                            map[row][col] = 2;
                            break;
                        case 'F':
                            map[row][col] = 3;
                            break;
                    }
                }
                row++;
            }

            reader.close();


        } catch (IOException e){
            e.printStackTrace();
        }

        return map;
    }

}

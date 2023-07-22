package org.meas.Square;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * This class represent the process of beetle jumping in a square when a bird approaches.
 */
public class BeetlesSquareJump {

    private final String FILE_NAME = "src/main/resources/board.txt";

    /**
     * This is an empty constructor
     */
    public BeetlesSquareJump() {

    }

    /**
     * This method is help to build the whole process of the bird approaching the beetles
     * @param sizeOfOneEdge
     * @param totalIteration_plus_1
     */
    public void beetleBoard(int sizeOfOneEdge, int totalIteration_plus_1) throws IOException {

        File targetFile = new File(FILE_NAME);
        // Getting the file by creating object of File class
        if(targetFile.exists()){
            targetFile.delete();
        }else{
            try {
                Files.createFile(Paths.get(FILE_NAME));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        int n_minus_1 = sizeOfOneEdge - 1;

        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));


        // Initialization of the Board
        int[][] beetlesSquare = boardInitialization(sizeOfOneEdge, writer);

        // We have to remove the edges (1st and last rows + 1st and last columns) since no one moves
        for(int b=1; b < totalIteration_plus_1; b++){
            for(int row = 1; row < n_minus_1; row++){
                for(int col = 1; col < n_minus_1; col++){

                    if(beetlesSquare[row][col] != 0){
                        //Get a list of adjacent indexes of the element of coordinate (row,col)
                        List<SquareCoordinate> adjacentCoordinates = getAdjacentIndexes(row, col, sizeOfOneEdge, sizeOfOneEdge);

                        beetlesSquare = jumpOfAllBeetlesFromCell(beetlesSquare, adjacentCoordinates, row, col);

                        // Remove all the beetles in the old cell
                        beetlesSquare[row][col] = 0;
                    }

                }
            }

            // Print the Board in the console
            if(b == 25 || b == 50 || b == 100) {
                printBoard(beetlesSquare, sizeOfOneEdge, b, writer);
            }

        }


        writer.close();

    }


    /**
     * This method help to get all the adjacent coordinates of an
     * element (which is not in an edge) of a 2D matrix
     * @param xCoordinate
     * @param yCoordinate
     * @param nRows
     * @param nCols
     * @return A list of adjacent coordinates
     */
    List<SquareCoordinate> getAdjacentIndexes(int xCoordinate, int yCoordinate, int nRows, int nCols)
    {
        // Initialisation of an 2D array where adjacent elements will be stored
        List<SquareCoordinate> adjacentElements = new ArrayList<>();

        // One could remove this condition. Since we will not take the edges.
        if (xCoordinate == 0 || yCoordinate == 0 || xCoordinate == nRows - 1 || yCoordinate == nCols - 1) {
            return adjacentElements;
        }


        // Take all the possible adjacent positions: for each cell inside the 4 edges, they are 8 in total
        adjacentElements.add( new SquareCoordinate(xCoordinate-1, yCoordinate-1));
        adjacentElements.add(new SquareCoordinate(xCoordinate-1, yCoordinate));
        adjacentElements.add(new SquareCoordinate(xCoordinate-1, yCoordinate+1));
        adjacentElements.add(new SquareCoordinate(xCoordinate, yCoordinate-1));
        adjacentElements.add(new SquareCoordinate(xCoordinate, yCoordinate+1));
        adjacentElements.add(new SquareCoordinate(xCoordinate+1, yCoordinate-1));
        adjacentElements.add(new SquareCoordinate(xCoordinate+1, yCoordinate));
        adjacentElements.add(new SquareCoordinate(xCoordinate+1, yCoordinate+1));

        return adjacentElements;
    }

    /**
     * This method help to compute the new population of each cell of a square array.
     * All the beetles will not move in the same direction.
     * For each one of them there is a random adjacent to jump in.
     * @param beetlesList
     * @param adjacentCoordinates
     * @param row
     * @param col
     * @return a square array
     */
    int[][] jumpOfAllBeetlesFromCell(
            int[][] beetlesList,
            List<SquareCoordinate> adjacentCoordinates,
            int row,
            int col
    ){
        Random random = new Random();

        for(int beetle=0; beetle < beetlesList[row][col]; beetle++){
            int squareToJump = random.nextInt(adjacentCoordinates.size()); // Chose a random adjacent coordinate

            // Increase the number of beetle (with 1) in that new cell
            beetlesList[adjacentCoordinates.get(squareToJump).getAbscisse()][adjacentCoordinates.get(squareToJump).getOrdinate()]++;
        }

        return beetlesList;
    }

    /**
     * This method help to create a 2D array and to initialize each cell with 1.
     *
     * @param anEdgeSize
     * @param writer
     * @return A square array.
     */
    int[][] boardInitialization(int anEdgeSize, BufferedWriter writer) throws IOException {

        writer.write("****************** Beetles Board App ******************\n\n\n");

        writer.write("---> Initialization:\n\n");

        int[][] beetleSquareMatrix = new int[anEdgeSize][anEdgeSize];
        Arrays.stream(beetleSquareMatrix).forEach(r -> Arrays.fill(r,1));

        for (int row = 0; row < anEdgeSize; row++) {
            for (int col = 0; col < anEdgeSize; col++) {
                System.out.print(beetleSquareMatrix[row][col] + "  |  ");
                writer.write(beetleSquareMatrix[row][col] + "  |  ");
            }

            System.out.println("");
            System.out.print("----------------------------------------------------------------------------------------");
            System.out.println("");

            writer.write("\n");
            writer.write("----------------------------------------------------------------------------------------\n");
        }

        return beetleSquareMatrix;
    }

    /**
     * This method help to print a board
     *
     * @param beetleSquareList
     * @param sizeOfAnEdge
     * @param birdIterationNumber
     * @param writer
     */
    void printBoard(int[][] beetleSquareList, int sizeOfAnEdge, int birdIterationNumber, BufferedWriter writer) throws IOException {
        System.out.println();
        System.out.println("********************* after the "+ birdIterationNumber + "th approach ********************");
        System.out.println("the number of beetles in each square:");


        writer.write("\n\n********************* after the "+ birdIterationNumber + "th approach ********************\n");

        writer.write("\n");
        writer.write("the number of beetles in each square:\n\n");

        double averageNumberOfBeetlesPerOccupiedSquare = 0;
        double numberOfBeetles = 0;
        double numberOfSquareOccupied = 0;
        List<SquareCoordinate> squareWithHighestBeetlePopulation = new ArrayList<SquareCoordinate>();
        squareWithHighestBeetlePopulation.add(new SquareCoordinate(0,0));
        int highestPopulation = beetleSquareList[0][0];

        for (int i = 0; i < sizeOfAnEdge; i++) {
            for (int j = 0; j < sizeOfAnEdge; j++) {
                System.out.print(beetleSquareList[i][j] + "  |  "); // write in the console

                writer.write(beetleSquareList[i][j] + "  |  "); // write in the file

                if(beetleSquareList[i][j] != 0){
                    numberOfSquareOccupied++;
                    numberOfBeetles += beetleSquareList[i][j];

                    if(beetleSquareList[i][j] > highestPopulation){
                        highestPopulation = beetleSquareList[i][j];
                        squareWithHighestBeetlePopulation = new ArrayList<SquareCoordinate>();
                        squareWithHighestBeetlePopulation.add(new SquareCoordinate(i,j));

                    }else if(beetleSquareList[i][j] == highestPopulation){
                        squareWithHighestBeetlePopulation.add(new SquareCoordinate(i,j));
                    }
                }
            }
            System.out.println("");
            System.out.print("----------------------------------------------------------------------------------------");
            System.out.println("");

            writer.write("\n");
            writer.write("----------------------------------------------------------------------------------------\n");
        }
        System.out.println("Number of squares occupied: "+numberOfSquareOccupied);
        System.out.println("Total number of beetles: "+numberOfBeetles);

        writer.write("Number of squares occupied: "+numberOfSquareOccupied + "\n");
        writer.write("Total number of beetles: "+numberOfBeetles + "\n");

        averageNumberOfBeetlesPerOccupiedSquare = numberOfBeetles / numberOfSquareOccupied;
        System.out.println("The average number of beetles per occupied square: " + averageNumberOfBeetlesPerOccupiedSquare);

        writer.write("The average number of beetles per occupied square: " + averageNumberOfBeetlesPerOccupiedSquare + "\n");

        System.out.println("The square(s) with the highest beetle population: ");

        writer.write("The square(s) with the highest beetle population: \n");

        for(int idx=0; idx<squareWithHighestBeetlePopulation.size(); idx++){
            System.out.println(squareWithHighestBeetlePopulation.get(idx).toString());
        }

        System.out.println("with number of population = " + highestPopulation + "\n");
        writer.write("with number of population = " + highestPopulation + "\n");

    }

}

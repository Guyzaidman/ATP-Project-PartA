package algorithms.mazeGenerators;

import javax.swing.*;
import java.util.Arrays;

/**
 * A maze class.
 * Holds maze's rows and columns.
 * Holds it's Start position and goal position.
 * mazeArr - int array for the maze's presentation
 * posArr - Array of positions holding each position at it's place.
 */
public class Maze {
    private int rows;
    private int columns;
    private Position StartPosition;
    private Position GoalPosition;
    private int[][] mazeArr;
    private Position[][] posArr;

    public Maze(int rows, int columns , Position StartPosition,Position GoalPosition) {
        this.rows = rows;
        this.columns = columns;
        mazeArr = new int[rows+rows-1][columns+columns-1];
        posArr = new Position[rows][columns];
        buildPositions(rows,columns);
        setNeighbours(rows, columns);
        this.StartPosition = posArr[StartPosition.getRowIndex()][StartPosition.getColumnIndex()];
        this.GoalPosition = posArr[GoalPosition.getRowIndex()][GoalPosition.getColumnIndex()];


    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public Position getStartPosition() {
        return StartPosition;
    }

    public void setStartPosition(Position startPosition) {
        StartPosition = startPosition;
    }

    public Position getGoalPosition() {
        return GoalPosition;
    }

    public void setGoalPosition(Position goalPosition) {
        GoalPosition = goalPosition;
    }

    public int[][] getMazeArr() {
        return mazeArr;
    }

    public void setMazeArr(int[][] mazeArr) {
        this.mazeArr = mazeArr;
    }

    public Position[][] getPosArr() {
        return posArr;
    }

    public void setPosArr(Position[][] posArr) {
        this.posArr = posArr;
    }

    private void buildPositions(int rows, int columns){
        for(int i=0;i<rows; i++){
            for(int j=0;j<columns;j++){
                posArr[i][j] = new Position(i,j);
            }
        }
    }
    private void setNeighbours(int rows, int columns){
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                if(i==0 && j==0){
                    if(findPosition(1,0) != null)
                        posArr[i][j].addNeighbour(findPosition(1, 0));
                    if(findPosition(0,1) != null)
                        posArr[i][j].addNeighbour(findPosition(0, 1));
                }
                else if(i==0 && j == columns-1){
                    if(findPosition(0,j-1) != null)
                        posArr[i][j].addNeighbour(findPosition(0,j-1));
                    if(findPosition(1,j) != null)
                        posArr[i][j].addNeighbour(findPosition(1,j));
                }
                else if(j==0 && i == rows-1){
                    if(findPosition(i-1,0) != null)
                        posArr[i][j].addNeighbour(findPosition(i-1,0));
                    if(findPosition(i,1) != null)
                        posArr[i][j].addNeighbour(findPosition(i,1));
                }
                else if(j==columns-1 && i == rows-1){
                    if(findPosition(i-1,j) != null)
                        posArr[i][j].addNeighbour(findPosition(i-1, j));
                    if(findPosition(i,j-1) != null)
                        posArr[i][j].addNeighbour(findPosition(i, j-1));
                }
                else if(i==rows-1){
                    if(findPosition(i,j+1) != null)
                        posArr[i][j].addNeighbour(findPosition(i,j+1));
                    if(findPosition(i,j-1) != null)
                        posArr[i][j].addNeighbour(findPosition(i,j-1));
                    if(findPosition(i-1,j) != null)
                        posArr[i][j].addNeighbour(findPosition(i-1,j));
                }
                else if(j==columns-1){
                    if(findPosition(i+1,j) != null)
                        posArr[i][j].addNeighbour(findPosition(i+1,j));
                    if(findPosition(i-1,j) != null)
                        posArr[i][j].addNeighbour(findPosition(i-1,j));
                    if(findPosition(i,j-1) != null)
                        posArr[i][j].addNeighbour(findPosition(i,j-1));
                }
                else if(i==0){
                    if(findPosition(i+1,j) != null)
                        posArr[i][j].addNeighbour(findPosition(i+1,j));
                    if(findPosition(i,j+1) != null)
                        posArr[i][j].addNeighbour(findPosition(i,j+1));
                    if(findPosition(i,j-1) != null)
                        posArr[i][j].addNeighbour(findPosition(i,j-1));
                }
                else if( j==0){
                    if(findPosition(i+1,j) != null)
                        posArr[i][j].addNeighbour(findPosition(i+1,j));
                    if(findPosition(i-1,j) != null)
                        posArr[i][j].addNeighbour(findPosition(i-1,j));
                    if(findPosition(i,j+1) != null)
                        posArr[i][j].addNeighbour(findPosition(i,j+1));
                }
                else{
                    if(findPosition(i,j+1) != null)
                        posArr[i][j].addNeighbour(findPosition(i,j+1));
                    if(findPosition(i,j-1) != null)
                        posArr[i][j].addNeighbour(findPosition(i,j-1));
                    if(findPosition(i+1,j) != null)
                        posArr[i][j].addNeighbour(findPosition(i+1,j));
                    if(findPosition(i-1,j) != null)
                        posArr[i][j].addNeighbour(findPosition(i-1,j));
                }
            }
        }
    }

    public Position findPosition(int x, int y){
        try{
            return posArr[x][y];
        }
        catch(ArrayIndexOutOfBoundsException e){
            return null;
        }
    }

    public void print() {
        for(int i=0;i<mazeArr.length;i++){
            for(int j=0;j<mazeArr[i].length;j++){
                if(i==2*StartPosition.getRowIndex() && j == 2*StartPosition.getColumnIndex()){
                    System.out.print("S");

                }
                else if(i==2*GoalPosition.getRowIndex() && j== 2*GoalPosition.getColumnIndex()) {
                    System.out.print("E");
                }
                else if(mazeArr[i][j] == 1) {
                    System.out.print(String.format("%d", mazeArr[i][j]));
                }
                else if(mazeArr[i][j] == 0){
                    System.out.print(String.format("%d" , mazeArr[i][j]));
                }


            }
            System.out.println();
        }
    }
    public void printWithColor() {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_BLACK = "\u001b[30m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_WHITE = "\u001B[37m";
        final String	BACKGROUND_BLACK	= "\u001B[40m";
        final String	BACKGROUND_RED		= "\u001B[41m";
        final String	BACKGROUND_GREEN	= "\u001B[42m";
        final String	BACKGROUND_WHITE	= "\u001B[47m";
        for(int i=0;i<mazeArr.length;i++){
            for(int j=0;j<mazeArr[i].length;j++){
                if(i==2*StartPosition.getRowIndex() && j == 2*StartPosition.getColumnIndex()){
                    System.out.print(BACKGROUND_RED + ANSI_RED + " S "  + ANSI_RESET);

                }
                else if(i==2*GoalPosition.getRowIndex() && j== 2*GoalPosition.getColumnIndex()) {
                    System.out.print(BACKGROUND_GREEN + ANSI_GREEN + " E " + ANSI_RESET);
                }
                else if(mazeArr[i][j] == 1) {
                    System.out.print(BACKGROUND_WHITE + ANSI_WHITE + String.format(" %d ", mazeArr[i][j]) + ANSI_RESET);
                }
                else if(mazeArr[i][j] == 0){
                    System.out.print(BACKGROUND_BLACK + ANSI_BLACK + String.format(" %d " , mazeArr[i][j]) + ANSI_RESET);
                }


            }
            System.out.println("\u001b[107m");
        }
    }
}

import java.util.Random;
import java.util.Scanner;

public class Main {
    static class Board {
        final static int SIZE = 4; //grid size
        int[][] grid; 
        final static int WIN_SCORE = 2048; // final score to win the game

        public Board() {
            this.grid = new int[SIZE][SIZE];
            initBoard(); 
            printGrid();
        }

        void slide(int direction) { //moves the tiles in the direction
            int[][] tempGrid = new int[SIZE][SIZE]; // temporary grid to store the modified grid
            switch (direction) {
                case 1: //left
                    for (int i = 0; i < SIZE; i++) {
                        int curr_pos = 0;
                        for (int j = 0; j < SIZE; j++) {
                            if (grid[i][j] != 0) { // if tile is not empty, store the value
                                tempGrid[i][curr_pos] = grid[i][j];
                                curr_pos++;
                            }
                        }
                    }
                    grid = tempGrid; //replacing current grid with the modified grid
                    break;
                case 2: // right
                    for (int i = 0; i < SIZE; i++) {
                        int curr_pos = SIZE - 1;
                        for (int j = SIZE - 1; j >= 0; j--) {
                            if (grid[i][j] != 0) {
                                tempGrid[i][curr_pos] = grid[i][j];
                                curr_pos--;
                            }
                        }
                    }
                    grid = tempGrid;
                    break;
                case 3: // up
                    for (int j = 0; j < SIZE; j++) {
                        int curr_pos = 0;
                        for (int i = 0; i < SIZE; i++) {
                            if (grid[i][j] != 0) {
                                tempGrid[curr_pos][j] = grid[i][j];
                                curr_pos++;
                            }
                        }
                    }
                    grid = tempGrid;
                    break;
                case 4: // down
                    for (int j = 0; j < SIZE; j++) {
                        int curr_pos = SIZE - 1;
                        for (int i = SIZE - 1; i >= 0; i--) {
                            if (grid[i][j] != 0) {
                                tempGrid[curr_pos][j] = grid[i][j];
                                curr_pos--;
                            }
                        }
                    }
                    grid = tempGrid;
                    break;
                default:
                    System.out.println("Invalid MOVE");
            }
        }

        int gameStatus() { // checks if moves are available or not: returns 0 if available, 1 if game is won, -1 if game is lost
            int count = 0; // to track non-empty tiles
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (grid[i][j] != 0) {
                        count++;
                        if (grid[i][j] == WIN_SCORE) // game is won
                            return 1;
                    }
                }
            }
            if (count == SIZE * SIZE) // game is lost
                return -1;
            return 0; // moves available
        }

        void combine(int direction) { // combines the tile with same value in the direction played and reorganizes the grid, similar to slide()
            int[][] tempGrid = new int[SIZE][SIZE]; // temporary grid to store the modified grid
            switch (direction) {
                case 1: //left
                    for (int i = 0; i < SIZE; i++) {
                        int curr_pos = 0;
                        int j = 0;
                        while (j < SIZE - 1) {
                            if (grid[i][j] != 0 && grid[i][j] == grid[i][j + 1]) { // when the adjacent tile matches
                                tempGrid[i][curr_pos] = grid[i][j] * 2;
                                j++;
                            } else if (grid[i][j] != 0) { // storing non-zero tiles into tempGrid
                                tempGrid[i][curr_pos] = grid[i][j];
                            }
                            curr_pos++;
                            j++;
                        }
                        if (j < SIZE) // to prevent outOfBound error
                            tempGrid[i][curr_pos] = grid[i][j]; // storing boundary elements;
                    }
                    grid = tempGrid; //replacing current grid with the modified grid
                    break;
                case 2: // right
                    for (int i = 0; i < SIZE; i++) {
                        int curr_pos = SIZE - 1;
                        int j = SIZE - 1;
                        while (j > 0) {
                            if (grid[i][j] != 0 && grid[i][j] == grid[i][j - 1]) {
                                tempGrid[i][curr_pos] = grid[i][j] * 2;
                                j--;
                            } else if (grid[i][j] != 0) {
                                tempGrid[i][curr_pos] = grid[i][j];
                            }
                            curr_pos--;
                            j--;
                        }
                        if (j == 0)
                            tempGrid[i][curr_pos] = grid[i][j];
                    }
                    //    same=isSame(tempGrid);
                    grid = tempGrid;
                    break;
                case 3: // up
                    for (int j = 0; j < SIZE; j++) {
                        int curr_pos = 0;
                        int i = 0;
                        while (i < SIZE - 1) {
                            if (grid[i][j] != 0 && grid[i][j] == grid[i + 1][j]) {
                                tempGrid[curr_pos][j] = grid[i][j] * 2;
                                i++;
                                curr_pos++;
                            } else if (grid[i][j] != 0) {
                                tempGrid[curr_pos][j] = grid[i][j];
                                curr_pos++;
                            }
                            i++;
                        }
                        if (i < SIZE)
                            tempGrid[curr_pos][j] = grid[i][j];

                    }
                    grid = tempGrid;
                    break;
                case 4: // down
                    for (int j = 0; j < SIZE; j++) {
                        int curr_pos = SIZE - 1;
                        int i = SIZE - 1;
                        while (i > 0) {
                            if (grid[i][j] != 0 && grid[i][j] == grid[i - 1][j]) {
                                tempGrid[curr_pos][j] = grid[i][j] * 2;
                                i--;
                                curr_pos--;
                            } else if (grid[i][j] != 0) {
                                tempGrid[curr_pos][j] = grid[i][j];
                                curr_pos--;
                            }
                            i--;
                        }
                        if (i == 0)
                            tempGrid[curr_pos][j] = grid[i][j];

                    }
                    grid = tempGrid;
                    break;
                default:
                    // System.out.println("Invalid MOVE");
            }
        }

        void printGrid() { // to display the grid
            System.out.println("BOARD:");
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    System.out.print(grid[i][j] + " | ");
                }
                System.out.println();
                for (int j = 0; j < SIZE; j++) {
                    System.out.print("---");
                }
                System.out.println();

            }
        }

        int getRandomPos() { // returns a position to store a new random tile, if moves are available
            int status = gameStatus();
            if (status != 0) { // moves not available
                if (status == 1) {
                    System.out.println("GAME WON !!!");


                } else {
                    System.out.println("GAME LOST :(");
                }
                return -1;

            }
            Random rand = new Random();
            int i = -1, j = -1, pos = -1;
            do {
                pos = rand.nextInt(SIZE * SIZE);
                i = pos / SIZE;
                j = pos % SIZE;
            } while ((i == -1 && j == -1) || grid[i][j] != 0);
            return pos;
        }

        int getRandomVal() { // returns random tile value
            return (new Random().nextInt(2) + 1) * 2;
        }

        void initBoard() { // initiates the grid with two random tiles
            for (int k = 0; k < 2; k++) {
                int pos = getRandomPos();
                // decomposing pos to i,j coordinate
                int i = pos / SIZE;
                int j = pos % SIZE;
                int val = getRandomVal();
                grid[i][j] = val;
            }
        }

        boolean playMove(int direction) { // to play a game move in the given direction
            // 1,2,3,4 -> left,right,up,down
            slide(direction);
            combine(direction);
            int pos = getRandomPos();
            if (pos == -1) {
                printGrid();
                return false;
            }
            int i = pos / SIZE;
            int j = pos % SIZE;
            int val = getRandomVal();
            grid[i][j] = val;

            printGrid();
            return true;
        }
        void start(){ // to start the game
            System.out.println("WELCOME");
            System.out.println("ENTER DIRECTION:\n1 -> left\n2 -> right\n3 -> up\n4 -> down");
            Scanner s = new Scanner(System.in);
            while (true) {
                int dir = s.nextInt();
                boolean moveAvailable = playMove(dir);
                if (!moveAvailable)
                    break;
            }
            System.out.println("THE END");
            s.close();

        }

    }

    public static void main(String[] args) {
        Board board = new Board();
        board.start();



    }
}

import java.util.Random;

public class Main {
    static class Board{
        final static int SIZE=4;
        int[][] grid;
        public Board(){
            this.grid=new int[SIZE][SIZE];
            initBoard();
            printGrid();
        }
        void stack(int direction){
            int[][] tempGrid=new int[SIZE][SIZE];
            switch(direction){
                case 1: //left
                    for(int i=0;i<SIZE;i++){
                        int curr_pos=0;
                        for(int j=0;j<SIZE;j++){
                            if(grid[i][j]!=0){
                                tempGrid[i][curr_pos]=grid[i][j];
                                curr_pos++;
                            }
                        }
                    }
                    grid=tempGrid;
                    break;
                case 2: // right
                    for(int i=0;i<SIZE;i++){
                        int curr_pos=SIZE-1;
                        for(int j=SIZE-1;j>=0;j--){
                            if(grid[i][j]!=0){
                                tempGrid[i][curr_pos]=grid[i][j];
                                curr_pos--;
                            }
                        }
                    }
                    grid=tempGrid;
                    break;
                case 3: // up
                    for(int j=0;j<SIZE;j++){
                        int curr_pos=0;
                        for(int i=0;i<SIZE;i++){
                            if(grid[i][j]!=0){
                                tempGrid[curr_pos][j]=grid[i][j];
                                curr_pos++;
                            }
                        }
                    }
                    grid=tempGrid;
                    break;
                case 4: // down
                    for(int j=0;j<SIZE;j++){
                        int curr_pos=SIZE-1;
                        for(int i=SIZE-1;i>=0;i--){
                            if(grid[i][j]!=0){
                                tempGrid[curr_pos][j]=grid[i][j];
                                curr_pos--;
                            }
                        }
                    }
                    grid=tempGrid;
                    break;
                default:
                    System.out.println("Invalid MOVE");
            }
        }
        void combine(int direction){
            int[][] tempGrid=new int[SIZE][SIZE];
            switch(direction){
                case 1: //left
                    for(int i=0;i<SIZE;i++){
                        int curr_pos=0;
                        for(int j=0;j<SIZE;j++){
                            if(grid[i][j]!=0){
                                tempGrid[i][curr_pos]=grid[i][j];
                                curr_pos++;
                            }
                        }
                    }
                    grid=tempGrid;
                    break;
                case 2: // right
                    for(int i=0;i<SIZE;i++){
                        int curr_pos=SIZE-1;
                        for(int j=SIZE-1;j>=0;j--){
                            if(grid[i][j]!=0){
                                tempGrid[i][curr_pos]=grid[i][j];
                                curr_pos--;
                            }
                        }
                    }
                    grid=tempGrid;
                    break;
                case 3: // up
                    for(int j=0;j<SIZE;j++){
                        int curr_pos=0;
                        for(int i=0;i<SIZE;i++){
                            if(grid[i][j]!=0){
                                tempGrid[curr_pos][j]=grid[i][j];
                                curr_pos++;
                            }
                        }
                    }
                    grid=tempGrid;
                    break;
                case 4: // down
                    for(int j=0;j<SIZE;j++){
                        int curr_pos=SIZE-1;
                        for(int i=SIZE-1;i>=0;i--){
                            if(grid[i][j]!=0){
                                tempGrid[curr_pos][j]=grid[i][j];
                                curr_pos--;
                            }
                        }
                    }
                    grid=tempGrid;
                    break;
                default:
                    System.out.println("Invalid MOVE");
            }
        }
        void printGrid(){
            System.out.println("BOARD:");
            for(int i=0;i<SIZE;i++){
                for(int j=0;j<SIZE;j++){
                    System.out.print(grid[i][j]+" | ");
                }
                System.out.println();
                for(int j=0;j<SIZE;j++){
                    System.out.print("---");
                }
                System.out.println();

            }
        }
        int getRandomPos(){
            Random rand= new Random();
            int i=-1,j=-1,pos=-1;
            do {
                 pos = rand.nextInt(SIZE * SIZE);
                 i = pos / SIZE;
                 j = pos % SIZE;
            }while((i==-1 && j==-1) || grid[i][j]!=0);
            return pos;
        }
        int getRandomVal(){
            return (new Random().nextInt(2)+1)*2;
        }
        void initBoard(){
            for(int k=0;k<2;k++){
                int pos=getRandomPos();
                int i=pos/SIZE;
                int j=pos%SIZE;
                int val=getRandomVal();
                grid[i][j]=val;
            }
        }
        void playMove(int direction){
            // 1,2,3,4 -> left,right,up,down

        }


    }
    public static void main(String[] args) {
        Board board =new Board();


    }
}

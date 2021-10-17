import java.util.Random;
import java.util.Scanner;

public class Main {
    static class Board{
        final static int SIZE=4;
        int[][] grid;
        final static int WIN_SCORE=2048;
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
//        boolean isSame(int[][] tempGrid){
//            for(int i=0;i<SIZE;i++){
//                for(int j=0;j<SIZE;j++){
//                    if(grid[i][j]!=tempGrid[i][j])
//                        return false;
//                }
//            }
//            return true;
//        }
        int gameStatus(){
            int count=0;
            for(int i=0;i<SIZE;i++){
                for(int j=0;j<SIZE;j++){
                    if(grid[i][j]!=0){
                        count++;
                        if(grid[i][j]==WIN_SCORE)
                            return 1;
                    }
                }
            }
            if(count==SIZE*SIZE)
                return -1;
            return 0;
        }
        void combine(int direction){
            int[][] tempGrid=new int[SIZE][SIZE];
          //  boolean same=false;
            switch(direction){
                case 1: //left
                    for(int i=0;i<SIZE;i++){
                        int curr_pos=0;
                        int j=0;
                        while(j<SIZE-1){
                            if(grid[i][j]!=0 && grid[i][j]==grid[i][j+1]){
                                tempGrid[i][curr_pos]=grid[i][j]*2;
                                j++;
                                curr_pos++;
                            }else if(grid[i][j]!=0){
                                tempGrid[i][curr_pos]=grid[i][j];
                                curr_pos++;
                            }
                            j++;
                        }
                        if(j<SIZE)
                        tempGrid[i][curr_pos]=grid[i][j];
                    }
                //    same=isSame(tempGrid);
                    grid=tempGrid;
                    break;
                case 2: // right
                    for(int i=0;i<SIZE;i++){
                        int curr_pos=SIZE-1;
                        int j=SIZE-1;
                        while(j>0){
                            if(grid[i][j]!=0 && grid[i][j]==grid[i][j-1]){
                                tempGrid[i][curr_pos]=grid[i][j]*2;
                                j--;
                            }else if(grid[i][j]!=0){
                                tempGrid[i][curr_pos]=grid[i][j];
                            }
                            curr_pos--;
                            j--;
                        }
                        if(j==0)
                        tempGrid[i][curr_pos]=grid[i][j];
                    }
                //    same=isSame(tempGrid);
                    grid=tempGrid;
                    break;
                case 3: // up
                    for(int j=0;j<SIZE;j++){
                        int curr_pos=0;
                        int i=0;
                        while(i<SIZE-1){
                            if(grid[i][j]!=0 && grid[i][j]==grid[i+1][j]){
                                tempGrid[curr_pos][j]=grid[i][j]*2;
                                i++;
                                curr_pos++;
                            }else if(grid[i][j]!=0){
                                tempGrid[curr_pos][j]=grid[i][j];
                                curr_pos++;
                            }
                            i++;
                        }
                        if(i<SIZE)
                        tempGrid[curr_pos][j]=grid[i][j];

                    }
                  //  same=isSame(tempGrid);
                    grid=tempGrid;
                    break;
                case 4: // down
                    for(int j=0;j<SIZE;j++){
                        int curr_pos=SIZE-1;
                        int i=SIZE-1;
                        while(i>0){
                            if(grid[i][j]!=0 && grid[i][j]==grid[i-1][j]){
                                tempGrid[curr_pos][j]=grid[i][j]*2;
                                i--;
                                curr_pos--;
                            }else if(grid[i][j]!=0){
                                tempGrid[curr_pos][j]=grid[i][j];
                                curr_pos--;
                            }
                            i--;
                        }
                        if(i==0)
                        tempGrid[curr_pos][j]=grid[i][j];

                    }
                   // same=isSame(tempGrid);
                    grid=tempGrid;
                    break;
                default:
                   // System.out.println("Invalid MOVE");
            }
        //    return !same;
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
            stack(direction);
            combine(direction);
            int pos=getRandomPos();
            int i=pos/SIZE;
            int j=pos%SIZE;
            int val=getRandomVal();
            grid[i][j]=val;

            printGrid();


        }


    }
    public static void main(String[] args) {
        System.out.println("WELCOME");
        Board board =new Board();
        System.out.println("ENTER DIRECTION:\n1 -> left\n2 -> right\n3 -> up\n4 -> down");
        Scanner s =new Scanner(System.in);
        while(true){
            int dir=s.nextInt();
            board.playMove(dir);
            int status=board.gameStatus();
            if(status!=0){
                if(status==1){
                    System.out.println("GAME WON!");

                }else{
                    System.out.println("GAME LOST :(");
                }
                break;
            }
        }


    }
}

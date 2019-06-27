package DemoSimple.Task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sneaking {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        char [][] room = new char[n][];

        int[] samPos = new int[2];
        int[] niklaPos = new int[2];

        for (int i = 0; i < n ; i++) {
            String line = reader.readLine();
            room[i] = line.toCharArray();

            if (line.contains("N")){
                niklaPos[0] = i;
                niklaPos[1] = line.indexOf("N");

            }else if (line.contains("S")){
                samPos[0] = i;
                samPos[1] = line.indexOf("S");
            }
        }

        String command = reader.readLine();

        for (int i = 0; i < command.length() ; i++) {
            moveEnemies(room);

            boolean samIsDead = isSamDead(room,samPos);

            if (samIsDead) {
                room[samPos[0]][samPos[1]] = 'X';
                System.out.println("Sam died at " + samPos[0] +", "+samPos[1]);
                break;
            }else {
                moveSam(room,samPos,command.charAt(i));
            }

            if (niklaPos[0]==samPos[0]){
                System.out.println("Nikoladze killed!");
                room[niklaPos[0]][niklaPos[1]] = 'X';
                break;
            }


        }
        for (int r = 0; r < room.length; r++) {
            for (int c = 0; c < room[r].length; c++) {
                System.out.print(room[r][c]);
            }
            System.out.println();
        }
    }


    private static void moveSam(char[][] room, int[] samPos, char direction) {
        if (direction== 'U'){
            room[samPos[0]--][samPos[1]] = '.';
            room[samPos[0]][samPos[1]] = 'S';
        }else if(direction == 'D'){
            room[samPos[0]++][samPos[1]] = '.';
            room[samPos[0]][samPos[1]] = 'S';
        }else if(direction == 'L'){
            room[samPos[0]][samPos[1]--] = '.';
            room[samPos[0]][samPos[1]] = 'S';
        }else if(direction == 'R'){
            room[samPos[0]][samPos[1]++] = '.';
            room[samPos[0]][samPos[1]] = 'S';
        }else if(direction == 'W'){
        }
    }

    private static boolean isSamDead(char[][] room, int[] samPos) {
        for (int i = 0; i < samPos[1]; i++) {
            if (room[samPos[0]][i] == 'b'){
                return true;
            }
        }
        for (int i = samPos[1]+1; i < room[samPos[0]].length; i++) {
            if (room[samPos[0]][i]=='d'){
                return true;
            }
        }
        return false;
    }

    private static void moveEnemies(char[][] room) {
        for (int row = 0; row < room.length; row++) {
            for (int col = 0; col < room[row].length; col++) {
                if (room[row][col] == 'b'){
                    if (col == room[row].length-1){
                        room[row][col] = 'd';
                    }else {
                        room[row][col] = '.';
                        room[row][col+1]= 'b';
                        break;
                    }
                } else if (room[row][col] == 'd'){
                    if (col == 0){
                        room[row][col] = 'b';
                    }else {
                        room[row][col] = '.';
                        room[row][col-1]= 'd';
                        break;
                    }
                }
            }
        }



    }
}

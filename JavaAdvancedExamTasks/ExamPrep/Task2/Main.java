package DemoSimple.Task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        int[] samPos = new int[2];
        int[] nikPos = new int[2];

        char[][] field = new char[n][];
        for (int i = 0; i <field.length; i++) {
            String line = reader.readLine();

            field[i]=line.toCharArray();

            if (line.contains("N")){
                nikPos[0]=i;
                nikPos[1]=line.indexOf('N');
            } else if (line.contains("S")) {
                samPos[0]=i;
                samPos[1]=line.indexOf('S');
            }
        }

        String command = reader.readLine();
        
        for (int i = 0; i <command.length() ; i++) {

            moveEnemy(field);

            boolean samIsDead = isSamDead(field,samPos);
            if (samIsDead){
                field[samPos[0]][samPos[1]] = 'X';
                System.out.println("Sam died at " + samPos[0] + ", " + samPos[1]);
                break;
            } else {
                moveSam(field,samPos,command.charAt(i));
            }
            if (samPos[0]==nikPos[0]){
                System.out.println("Nikoladze killed!");
                field[nikPos[0]][nikPos[1]]='X';
                break;
            }
        }


        for (int r = 0; r < field.length; r++) {
            for (int c = 0; c < field[r].length; c++) {
                System.out.print(field[r][c]);
            }
            System.out.println();
        }
    }

    private static void moveSam(char[][] field, int[] samPos, char direction) {
        if (direction=='U'){
            field[samPos[0]--][samPos[1]]='.';
            field[samPos[0]][samPos[1]]='S';
        } else if (direction=='D'){
            field[samPos[0]++][samPos[1]]='.';
            field[samPos[0]][samPos[1]]='S';
        } else if (direction=='L'){
            field[samPos[0]][samPos[1]--]='.';
            field[samPos[0]][samPos[1]]='S';
        } else if (direction=='R'){
            field[samPos[0]][samPos[1]++]='.';
            field[samPos[0]][samPos[1]]='S';
        } else if (direction=='W'){

        }
    }

    private static boolean isSamDead(char[][] field, int[] samPos) {
        for (int i = 0; i < samPos[1]; i++) {
            if (field[samPos[0]][i]=='b'){
                return true;
            }
        }
        for (int i = samPos[1]+1; i < field[samPos[0]].length; i++) {
            if (field[samPos[0]][i]=='d'){
                return true;
            }
        }
        return false;
    }

    private static void moveEnemy(char[][] field) {
        for (int r = 0; r < field.length; r++) {
            for (int c = 0; c < field[r].length; c++) {
                if (field[r][c]=='b'){
                    if (c==field[r].length-1){
                        field[r][c]='d';
                    } else {
                        field[r][c++]='.';
                        field[r][c]='b';
                        break;
                    }
                }else if (field[r][c]=='d'){
                    if (c==0){
                        field[r][c]='b';
                    } else {
                        field[r][c--]='.';
                        field[r][c]='d';
                        break;
                    }
                }
            }
        }
    }
}

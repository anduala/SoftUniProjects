package Exam2402.Task02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class task2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        char[][] field = new char[n][n];

        for (int i = 0; i <n ; i++) {
            char[] line = reader.readLine().toCharArray();
            field[i] = line;
        }

        int[] cordinateF = new int[2];
        int[] cordinateS = new int[2];

        for (int r = 0; r < field.length; r++) {
            for (int c = 0; c < field[r].length; c++) {
                if (field[r][c]=='f'){
                    cordinateF[0]=r;
                    cordinateF[1]=c;
                } else if(field[r][c]=='s'){
                    cordinateS[0]=r;
                    cordinateS[1]=c;
                }
            }
        }
        int dead = 0;

        int changed=0;

        while (dead!=1) {
            String[] cmd = reader.readLine().split(" ");
            String p1cmd = cmd[0];
            String p2cmd = cmd[1];

            if (p1cmd.equals("up")){
                if (cordinateF[0]!=0){
                    cordinateF[0]--;
                    dead = p1Move(cordinateF, field[cordinateF[0]], 's', 'f', 0);
                } else{
                    cordinateF[0]=field.length-1;
                    dead = p1Move(cordinateF, field[cordinateF[0]], 's', 'f', 0);
                }
            }else if (p1cmd.equals("down")){
                if (cordinateF[0]!=field.length-1){
                    cordinateF[0]++;
                    dead = p1Move(cordinateF, field[cordinateF[0]], 's', 'f', p1Move());
                } else{
                    cordinateF[0]=0;
                    dead = p1Move(cordinateF, field[cordinateF[0]], 's', 'f', 0);
                }
            } else if (p1cmd.equals("left")){
                if (cordinateF[1]!=0){
                    cordinateF[1]--;
                    dead = p1Move(cordinateF, field[cordinateF[0]], 's', 'f', 0);
                } else{
                    cordinateF[1]=field.length-1;
                    dead = p1Move(cordinateF, field[cordinateF[0]], 's', 'f', 0);
                }
            }else if (p1cmd.equals("right")){
                if (cordinateF[1]!=field.length-1){
                    cordinateF[1]++;
                    dead = p1Move(cordinateF, field[cordinateF[0]], 's', 'f', 0);
                } else{
                    cordinateF[1]=0;
                    dead = p1Move(cordinateF, field[cordinateF[0]], 's', 'f', 0);
                }
            }
            if (dead==1){
                changed = 1;
                break;
            }
            if (p2cmd.equals("up")){
                    if (cordinateS[0]!=0){
                        cordinateS[0]--;
                        dead = p2Move(cordinateS, field[cordinateS[0]]);
                    } else{
                        cordinateS[0]=field.length-1;
                        dead = p2Move(cordinateS, field[cordinateS[0]]);
                    }
                }else if (p2cmd.equals("down")){
                    if (cordinateS[0]!=field.length-1){
                        cordinateS[0]++;
                        dead = p2Move(cordinateS, field[cordinateS[0]]);
                    } else{
                        cordinateS[0]=0;
                        dead = p2Move(cordinateS, field[cordinateS[0]]);
                    }
                } else if (p2cmd.equals("left")){
                    if (cordinateS[1]!=0){
                        cordinateS[1]--;
                        dead = p2Move(cordinateS, field[cordinateS[0]]);
                    } else{
                        cordinateS[1]=field.length-1;
                        dead = p2Move(cordinateS, field[cordinateS[0]]);
                    }
                }else if (p2cmd.equals("right")){
                    if (cordinateS[1]!=field.length-1){
                        cordinateS[1]++;
                        dead = p2Move(cordinateS, field[cordinateS[0]]);
                    } else{
                        cordinateS[1]=0;
                        dead = p2Move(cordinateS, field[cordinateS[0]]);
                    }
                }
            if (dead==1){
                changed = 1;
                break;
            }
            dead=changed;
            }
            printMatrix(field);
        }

    private static int p2Move(int[] cordinateS, char[] chars) {
        int dead;
        if (chars[cordinateS[1]] != 'f') {
            chars[cordinateS[1]] = 's';
            return dead = 0;
        } else {
            chars[cordinateS[1]] = 'x';
            return dead = 1;
        }
    }

    private static int p1Move(int[] cordinateF, char[] chars, char s, char f, int i2) {
        int dead;
        if (chars[cordinateF[1]] != s) {
            chars[cordinateF[1]] = f;
            dead = i2;
        } else {
            chars[cordinateF[1]] = 'x';
            dead = 1;
        }
        return dead;
    }

    private static int p1Move() {
        int dead;
        dead = 0;
        return dead;
    }


    private static void printMatrix(char[][] field) {
        for (int r = 0; r < field.length; r++) {
            for (int c = 0; c < field[r].length; c++) {
                System.out.print(field[r][c]);
            }
            System.out.println();
        }
    }


}

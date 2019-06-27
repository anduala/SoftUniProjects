package Exam2402.Task01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class task1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        String[]input = reader.readLine().split(" ");

        int persons = 0;
        String thatChar = "";

        LinkedHashMap<String,Integer> halls = new LinkedHashMap<>();
        HashMap<String, ArrayList<Integer>> hallsWithPersons = new HashMap<>();
        Deque<String> existingHalls = new ArrayDeque<>();




        for (int i = input.length-1; i >=0 ; i--) {
            if (input[i].matches("[a-zA-z]")){
                existingHalls.offer(input[i]);
                halls.put(input[i],n);
                hallsWithPersons.put(input[i],new ArrayList<>());
            }else {
                if (!halls.isEmpty()) {
                    thatChar = existingHalls.peek();
                    persons = Integer.parseInt(input[i]);
                    if (halls.get(thatChar) - persons >= 0) {
                        halls.put(thatChar, halls.get(thatChar) - persons);
                        hallsWithPersons.get(thatChar).add(persons);
                    } else {
                        System.out.print(thatChar + " -> ");
                        for (int j = 0; j < hallsWithPersons.get(thatChar).size(); j++) {
                            if (j != hallsWithPersons.get(thatChar).size() - 1) {
                                System.out.print(hallsWithPersons.get(thatChar).get(j) + ", ");
                            } else {
                                System.out.print(hallsWithPersons.get(thatChar).get(j));
                            }
                        }

                        System.out.println();
                        halls.remove(thatChar);
                        hallsWithPersons.remove(thatChar);
                        existingHalls.pop();
                        if (!existingHalls.isEmpty()) {
                            thatChar = existingHalls.peek();
                            halls.put(thatChar, halls.get(thatChar) - persons);
                            hallsWithPersons.get(thatChar).add(persons);
                        }
                    }
                }
            }
        }
    }
}

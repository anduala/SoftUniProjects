package DemoSimple.Task1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        LinkedList<String> roomMembers = new LinkedList<>();
        HashMap<String, TreeSet<String>> doctors = new HashMap<>();
        HashMap<String,LinkedList<String>> hospital = new HashMap<>();


        String input = reader.readLine();
        while (!input.equals("Output")){
            String [] tokens = input.split("\\s+");

            String department = tokens[0];
            String doctor = tokens[1] + " " + tokens[2];
            String patient = tokens[3];
            doctors.putIfAbsent(doctor, new TreeSet<>());
            hospital.putIfAbsent(department, new LinkedList<>());

            if (hospital.get(department).size()<60) {
                doctors.get(doctor).add(patient);
                hospital.get(department).add(patient);
            }
            input =reader.readLine();
        }
        String output = reader.readLine();

        while (!output.equals("End")){
            String[] tokens = output.split(" ");
            if(tokens.length == 1) {
                hospital.get(output).forEach(System.out::println);
            }
            else if (Character.isDigit(tokens[1].charAt(0))) {
                String department = tokens[0];
                int room = Integer.parseInt(tokens[1]);
                    for (String dep : hospital.keySet()) {
                        if (dep.equals(department)) {
                            int start = (room * 3) - 3;
                            int end = (room * 3) - 1;
                            for (int i = start; i <= end; i++) {
                                if (i < hospital.get(department).size()) {
                                    roomMembers.add(hospital.get(dep).get(i));
                                }
                            }
                        }
                    }
                    roomMembers.stream().filter(Objects::nonNull).sorted().forEach(System.out::println);
            } else {
                String name = tokens[0] + " " + tokens[1];
                doctors.get(name).stream().sorted().forEach(System.out::println);
            }
            output = reader.readLine();
        }
    }
}

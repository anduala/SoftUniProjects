package DemoSimple.softUniParking;

import java.util.HashMap;
import java.util.List;

public class Parking {
    private HashMap<String,Car> data;
    private int capacity;

    public Parking(int capacity) {
        this.data = new HashMap<String, Car>();
        this.capacity = capacity;
    }

    int counterOfCapacity =0;
    public void addCar(Car car){
        for (String data : data.keySet()) {
            if (data.equals(car.getRegistrationNumber())){
                System.out.println("Car with that registration number, already exists!");
                return;
            }
        }
        if(counterOfCapacity<this.capacity){
            counterOfCapacity++;
            this.data.put(car.getRegistrationNumber(),car);
            System.out.println("Successfully added new car " + car.getMake() + " " + car.getRegistrationNumber());
        }else {
            System.out.println("Parking is full!");
        }
    }
    public void removeCar(String registrationNumber){
        for (String auto : data.keySet()) {
            if (auto.equals(registrationNumber)){
                data.remove(auto);
                counterOfCapacity--;
                System.out.println("Successfully removed " + registrationNumber);
                return;
            }
        }
        System.out.println("Car with that registration number, doesn't exists!");
    }
    public String getCar(String registrationNumber){
        for (String auto : data.keySet()) {
            if (auto.equals(registrationNumber)){
                System.out.println(data.get(auto).toString());

            }
        }
        return null;
    }

    public int getCount(){
        return counterOfCapacity;
    }

    public void removeSetOfRegistrationNumber(List<String> registrationNumbers){
        for (String registrationNumber : registrationNumbers) {
            for (String auto : data.keySet()) {
                if (auto.equals(registrationNumber)){
                    data.remove(auto);
                    counterOfCapacity--;
                    System.out.println("Successfully removed " + registrationNumber);
                    return;
                }
            }
            System.out.println("Car with that registration number, doesn't exists!");
        }

    }
}

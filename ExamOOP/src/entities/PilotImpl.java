package entities;

import entities.interfaces.Machine;

import java.util.List;

public class PilotImpl implements entities.interfaces.Pilot {
    private String name;
    private List<Machine> machines;

    public PilotImpl(String name) {
        setName(name);
    }

    public void setName(String name) {
        if (name.equals("")||name==null){
            throw new IllegalArgumentException("PilotImpl name cannot be null or empty string.\n");
        }else {
            this.name = name;
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addMachine(Machine machine) {
        if (machine==null){
            throw new NullPointerException("Null machine cannot be added to the pilot.");
        }else {
            this.machines.add(machine);
        }
    }

    @Override
    public List<Machine> getMachines() {
        return this.machines;
    }

    @Override
    public String report() {
        return null;
    }
}

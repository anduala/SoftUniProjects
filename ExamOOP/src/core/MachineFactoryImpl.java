package core;

import core.interfaces.MachineFactory;
import entities.FighterImpl;
import entities.TankImpl;
import entities.interfaces.Fighter;
import entities.interfaces.Machine;
import entities.interfaces.Tank;

import java.util.ArrayList;
import java.util.List;

public class MachineFactoryImpl implements MachineFactory {
    private List<Machine> machines;

    public MachineFactoryImpl() {
        this.machines = new ArrayList<>();
    }

    @Override
    public Tank createTank(String name, double attackPoints, double defensePoints) {
        Tank tank = new TankImpl(name,attackPoints,defensePoints);
        machines.add(tank);
        return tank;
    }

    @Override
    public Fighter createFighter(String name, double attackPoints, double defensePoints) {
        Fighter fighter = new FighterImpl(name,attackPoints,defensePoints);
        machines.add(fighter);
        return fighter;
    }
}

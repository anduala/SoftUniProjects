package core;

import common.OutputMessages;
import core.interfaces.MachineFactory;
import core.interfaces.PilotFactory;
import core.interfaces.MachinesManager;

import entities.FighterImpl;
import entities.PilotImpl;
import entities.TankImpl;
import entities.interfaces.Machine;
import entities.interfaces.Pilot;

import java.util.Map;

public class MachinesManagerImpl implements MachinesManager {
    private PilotFactory pilotFactory;
    private MachineFactory machineFactory;
    private Map<String,Pilot> pilotsMap;
    private Map<String,Machine> machinesMap;


    public MachinesManagerImpl(PilotFactory pilotFactory, MachineFactory machineFactory, Map<String, Pilot> pilots, Map<String, Machine> machines) {
     this.pilotFactory = pilotFactory;
     this.machineFactory = machineFactory;
     this.pilotsMap = pilots;
     this.machinesMap = machines;
    }


    @Override
    public String hirePilot(String name) {
        Pilot pilot = new PilotImpl(name);
        this.pilotsMap.put(name,pilot);
        return String.format("Pilot %s hired",name);
    }

    @Override
    public String manufactureTank(String name, double attackPoints, double defensePoints) {
        TankImpl tank = new TankImpl(name,attackPoints,defensePoints);
        if (this.machinesMap.containsKey(name)){
            return String.format(OutputMessages.machineExists,name);
        } else {
            this.machinesMap.put(name,tank);
            return String.format(OutputMessages.tankManufactured,name,attackPoints,defensePoints);

        }
    }

    @Override
    public String manufactureFighter(String name, double attackPoints, double defensePoints) {
        FighterImpl fighter = new FighterImpl(name,attackPoints,defensePoints);
        if (this.machinesMap.containsKey(name)){
            return String.format(OutputMessages.machineExists,name);
        } else {
            this.machinesMap.put(fighter.getName(),fighter);
            return String.format(OutputMessages.fighterManufactured,name,attackPoints,defensePoints);
        }
    }

    @Override
    public String engageMachine(String selectedPilotName, String selectedMachineName) {
        if (!this.pilotsMap.containsKey(selectedPilotName)){
            return String.format(OutputMessages.pilotNotFound,selectedPilotName);
        } else if (!this.machinesMap.containsKey(selectedMachineName)){
            return String.format(OutputMessages.machineNotFound,selectedMachineName);
        } else {
            this.machinesMap.get(selectedMachineName).setPilot(pilotsMap.get(selectedPilotName));
            this.pilotsMap.get(selectedPilotName).addMachine(this.machinesMap.get(selectedMachineName));
            return String.format(OutputMessages.machineEngaged,selectedPilotName,selectedMachineName);
        }
    }

    @Override
    public String attackMachines(String attackingMachineName, String defendingMachineName) {
        if (!machinesMap.containsKey(attackingMachineName)){
            return String.format(OutputMessages.machineNotFound,attackingMachineName);
        } else if (!machinesMap.containsKey(defendingMachineName)){
            return String.format(OutputMessages.machineNotFound,defendingMachineName);
        } else {
            double hp = this.machinesMap.get(defendingMachineName).getHealthPoints();
            double attack = this.machinesMap.get(attackingMachineName).getAttackPoints();
            double actualHP = hp-attack;
            this.machinesMap.get(defendingMachineName).setHealthPoints(actualHP);
            return String.format(OutputMessages.attackSuccessful,defendingMachineName,attackingMachineName,actualHP);
        }
    }

    @Override
    public String pilotReport(String pilotName) {
        return this.pilotsMap.get(pilotName).report();
    }

    @Override
    public String toggleFighterAggressiveMode(String fighterName) {
        return String.format(OutputMessages.fighterOperationSuccessful,fighterName);
    }

    @Override
    public String toggleTankDefenseMode(String tankName) {
        return String.format(OutputMessages.tankOperationSuccessful,tankName);
    }
}

package core;

import common.OutputMessages;
import core.interfaces.PilotFactory;
import entities.PilotImpl;
import entities.interfaces.Pilot;

import java.util.ArrayList;
import java.util.List;

public class PilotFactoryImpl implements PilotFactory {
    private List<Pilot> pilots;

    public PilotFactoryImpl() {
        this.pilots = new ArrayList<>();
    }

    @Override
    public Pilot createPilot(String name) {
        Pilot pilot = new PilotImpl(name);
        this.pilots.add(pilot);
        return pilot;
    }
}

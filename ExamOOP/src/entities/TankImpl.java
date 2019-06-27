package entities;

import entities.interfaces.Tank;

public class TankImpl extends BaseMachine implements Tank {
    private String name;
    private double attackPoints;
    private double defensePoints;
    private boolean toggle;

    public TankImpl(String name, double attackPoints, double defensePoints) {
        super(name, attackPoints, defensePoints, 100);
    }


    @Override
    public boolean getDefenseMode() {
        return false;
    }

    @Override
    public void toggleDefenseMode() {
        if (toggle){
            this.attackPoints = this.attackPoints-attackPointsModifier();
            this.defensePoints = this.defensePoints+deffensePointsModifier();
            toggle=false;
        } else {
            this.attackPoints = this.attackPoints+attackPointsModifier();
            this.defensePoints = this.defensePoints-deffensePointsModifier();
            toggle=true;
        }
    }

    public boolean defenseMode(){
        return true;
    }

    public double attackPointsModifier(){
        double attackPointsModifier = 40.0;
        return attackPointsModifier;
    }

    public double deffensePointsModifier(){
        double deffensePointsModifier = 30.0;
        return deffensePointsModifier;
    }
}

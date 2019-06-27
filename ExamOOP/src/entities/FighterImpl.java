package entities;

import entities.interfaces.Fighter;

public class FighterImpl extends BaseMachine implements Fighter {
    private String name;
    private double attackPoints;
    private double defensePoints;
    private boolean toggle = true;

    public FighterImpl(String name, double attackPoints, double defensePoints) {
        super(name, attackPoints, defensePoints, 200);
    }

    @Override
    public boolean getAggressiveMode() {
        return false;
    }

    @Override
    public void toggleAggressiveMode() {
        if (toggle){
            this.attackPoints = this.attackPoints+attackPointsModifier();
            this.defensePoints = this.defensePoints-deffensePointsModifier();
            toggle=false;
        } else {
            this.attackPoints = this.attackPoints-attackPointsModifier();
            this.defensePoints = this.defensePoints+deffensePointsModifier();
            toggle=true;
        }

    }

    public boolean aggresiveMode(){
        return true;
    }

    public double attackPointsModifier(){
        double attackPointsModifier = 50.0;
        return attackPointsModifier;
    }

    public double deffensePointsModifier(){
        double deffensePointsModifier = 25.0;
        return deffensePointsModifier;
    }
}

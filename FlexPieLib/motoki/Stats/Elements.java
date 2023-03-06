package com.test.gamelevelfirst.Stats;

import androidx.annotation.NonNull;

import java.util.Arrays;

import org.apache.commons.math3.linear.ArrayRealVector;

public class Elements extends Stats {
    static Elements calc_space = new Elements(0,0,0,0);

    private double fire;
    private double water;
    private double ice;
    private double thunder;

    Elements(double fire, double water, double ice, double thunder){
        this.stats_type = "Elements";
        this.fire = fire;
        this.water = water;
        this.ice = ice;
        this.thunder = thunder;
    }

    public Elements(double[] element_values){
        this.stats_type = "Elements";
        this.fire = element_values[0];
        this.water = element_values[1];
        this.ice = element_values[2];
        this.thunder = element_values[3];
    }

    private Elements getCalcSpace(){
        return calc_space;
    }

    double[] getParamsArray(){
        double[] params = new double[4];
        params[0] = fire;
        params[1] = water;
        params[2] = ice;
        params[3] = thunder;
        return params;
    }

    private void setParams(double[] element_values){
        this.fire = element_values[0];
        this.water = element_values[1];
        this.ice = element_values[2];
        this.thunder = element_values[3];
    }

    @Override
    public void makeEqual(Stats target){
        if(this.canMakeEqual(target)) {
            ((Elements) target).setParams(this.getParamsArray());
        }
    }

    @Override
    public Elements getTranscription() {
        Elements transcripted = new Elements(this.getParamsArray());
        return transcripted;
    }

    @Override
    public Stats sum(Stats target) {
        if(this.getStatsType().equals(target.getStatsType())){
            double[] additional_array = this.getParamsArray();
            double[] org_array = ((Elements)target).getParamsArray();
            ArrayRealVector to_add = new ArrayRealVector(additional_array);
            ArrayRealVector org = new ArrayRealVector(org_array);
            calc_space.setParams(org.add(to_add).toArray());
            target.setFinalCalclationSuccess(true);
            return calc_space;
        }
        else {
            target.setFinalCalclationSuccess(false);
            return target;
        }
    }

    static public Elements getInstance(double fire, double water, double ice, double thunder){
        return new Elements(fire, water, ice, thunder);
    }

    @Override
    public Stats product(Stats target) {
        if(this.getStatsType().equals(target.getStatsType())){
            double[] additional_array = this.getParamsArray();
            double[] org_array = ((Elements)target).getParamsArray();
            ArrayRealVector to_mul = new ArrayRealVector(additional_array);
            ArrayRealVector org = new ArrayRealVector(org_array);
            calc_space.setParams(org.ebeMultiply(to_mul).toArray());
            target.setFinalCalclationSuccess(true);
            return calc_space;
        }
        else {
            target.setFinalCalclationSuccess(false);
            return target;
        }
    }

    @Override
    public Elements round() {
        double[] rounded = Arrays.stream(this.getParamsArray()).map(Math::round).toArray();
        this.setParams(rounded);
        return this;
    }



    @Override
    public String toString() {
        return "Elements{" +
                "fire=" + fire +
                ", water=" + water +
                ", ice=" + ice +
                ", thunder=" + thunder +
                '}';
    }
}

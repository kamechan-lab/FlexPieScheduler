package com.test.gamelevelfirst.Stats;

import androidx.annotation.NonNull;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

import java.util.Arrays;
import java.util.Collections;

public class BaseAttributes extends Stats {
    static BaseAttributes calc_space = new BaseAttributes(new double[]{0,0,0,0,0});

    double vitality;
    double stamina;
    double strength;
    double dexterity;
    double intelligence;


    public BaseAttributes(double[] params){
        this.stats_type = "BaseAttributes";
        this.setParams(params);
    }

    BaseAttributes setParams(double[] params){
        this.vitality = params[0];
        this.stamina = params[1];
        this.strength = params[2];
        this.dexterity = params[3];
        this.intelligence = params[4];
        return this;
    }

    double[] getParamsArray(){
        double[] params = new double[5];
        params[0] = this.vitality;
        params[1] = this.stamina;
        params[2] = this.strength;
        params[3] = this.dexterity;
        params[4] = this.intelligence;
        return  params;
    }

    @Override
    public void makeEqual(Stats target){
        if(this.canMakeEqual(target)) {
            ((BaseAttributes) target).setParams(this.getParamsArray());
        }
    }

    @Override
    public BaseAttributes getTranscription() {
        BaseAttributes transcripted = new BaseAttributes(this.getParamsArray());
        return transcripted;
    }

    @Override
    public Stats sum(Stats target) {
        if(this.getStatsType().equals(target.getStatsType())){
            ArrayRealVector additional_array = new ArrayRealVector(this.getParamsArray());
            ArrayRealVector org_array = new ArrayRealVector(((BaseAttributes) target).getParamsArray());
            ArrayRealVector added_array = additional_array.add(org_array);
            target.setFinalCalclationSuccess(true);
            return calc_space.setParams(added_array.toArray());
        }
        else {
            target.setFinalCalclationSuccess(false);
            return target;
        }
    }

    @Override
    public Stats product(Stats target) {
        if(this.getStatsType().equals(target.getStatsType())){
            ArrayRealVector multiply_array = new ArrayRealVector(this.getParamsArray());
            ArrayRealVector org_array = new ArrayRealVector(((BaseAttributes) target).getParamsArray());
            ArrayRealVector multiplied_array = multiply_array.ebeMultiply(org_array);
            target.setFinalCalclationSuccess(true);
            return calc_space.setParams(multiplied_array.toArray());
        }
        else {
            target.setFinalCalclationSuccess(false);
            return target;
        }
    }

    @Override
    public BaseAttributes round() {
        double[] rounded = Arrays.stream(this.getParamsArray()).map(Math::round).toArray();
        this.setParams(rounded);
        return this;
    }

//    @NonNull
//    @Override
//    public String toString() {
//        return "StatsType : " + this.stats_type + ", " + "vitality : " + this.vitality + ", "+"stamina : " + this.stamina + ", " + "strength : "
//                + this.strength + ", " + "dexterity : " + this.dexterity + ", " + "intelligence : " + this.intelligence;
//    }


    @Override
    public String toString() {
        return "BaseAttributes{" +
                "vitality=" + vitality +
                ", stamina=" + stamina +
                ", strength=" + strength +
                ", dexterity=" + dexterity +
                ", intelligence=" + intelligence +
                '}';
    }
}

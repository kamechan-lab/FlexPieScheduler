package com.test.gamelevelfirst.Stats;

import androidx.annotation.NonNull;

import com.test.gamelevelfirst.calculation.ArithmericOperationable;

public abstract class Stats implements ArithmericOperationable<Stats> {
    String stats_type;
    private boolean final_calclation_success;

    String getStatsType(){
        return this.stats_type;
    }

    Boolean compareStatsType(Stats target){
        return this.getStatsType().equals(target.getStatsType());
    }

    public abstract Stats getTranscription();

    public abstract Stats round();

    public void setFinalCalclationSuccess(boolean final_calclation_success) {
        this.final_calclation_success = final_calclation_success;
    }

    public boolean isFinalCalclationSucceeded(){
        return this.final_calclation_success;
    }

    protected boolean canMakeEqual(Stats target){
        if(target.getStatsType().equals(this.getStatsType())){
            return true;
        }
        return false;
    }

    abstract public void makeEqual(Stats target);

    @NonNull
    @Override
    public String toString() {
        String statsType = "StatsType : " + this.stats_type;
        return statsType;
    }

}

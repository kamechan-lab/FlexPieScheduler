package com.test.gamelevelfirst.Stats;

import androidx.annotation.NonNull;

import com.test.gamelevelfirst.IOwnedStatsContainer;
import com.test.gamelevelfirst.Stats.Stats;

public class StatsOwner implements IOwnedStatsContainer<Stats> {
    String owner;
    Stats stats;

    public StatsOwner(String owner){
        this.owner = owner;
    }

    @Override
    public String getOwner() {
        return this.owner;
    }

    @Override
    public Stats getStats() {
        return this.stats;
    }

    @Override
    public void setStats(Stats stats) {
        this.stats = stats;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString() + "owner : " + this.getOwner();
    }
}

package com.test.gamelevelfirst.Stats;

import androidx.annotation.NonNull;

import com.test.gamelevelfirst.IOwnedStatsContainer;

import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class StatsListOwner implements IOwnedStatsContainer, Iterator<Stats>, Iterable<Stats> {
    List<Stats> statsList;
    String owner;

    private int list_index = 0;

    public StatsListOwner(String owner){
        this.owner = owner;
    }

    public List<Stats> getStatsList(){
        return this.statsList;
    }


    public StatsListOwner setStatsList(List<Stats> statsList) {
        this.statsList = statsList;
        return this;
    }

    @Override
    public String getOwner() {
        return this.owner;
    }

    @Override
    public Stats getStats() {
        return this.statsList.get(list_index);
    }

    @Override
    public void setStats(Stats stats) {
        this.statsList.set(list_index, stats);
    }

    @NonNull
    @Override
    public Iterator<Stats> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        if(this.list_index + 1 > this.statsList.size()){
            this.list_index = 0 ;
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public Stats next() {
        Stats cur_stats = this.statsList.get(list_index);
        this.list_index ++ ;
        return cur_stats;
    }

    public static void main(String[] args) {
        Physics physics = new Physics(100);
        Elements elements = new Elements(new double[]{30, 30, 30, 30});
        ArrayList<Stats> arrayList = new ArrayList<>();
        arrayList.add(physics);
        arrayList.add(elements);

        StatsListOwner statsListOwner = new StatsListOwner("subject");
        statsListOwner.setStatsList(arrayList);
        while (statsListOwner.hasNext()){
            System.out.println(statsListOwner.getStats());
            statsListOwner.next();
        }

    }
}

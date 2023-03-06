package com.test.gamelevelfirst.Stats;

public class Attributes extends Stats{
    static Attributes calc_space = new Attributes(new BaseAttributes(new double[]{0, 0, 0, 0, 0}), new Elements(new double[]{0, 0, 0, 0}));
    BaseAttributes base_attrib;
    Elements affinity;

    public Attributes(BaseAttributes base_attrib, Elements affinity){
        this.stats_type = "attributes";
        this.base_attrib = base_attrib;
        this.affinity = affinity;
    }

    BaseAttributes getBaseAttrib(){
        return this.base_attrib;
    }

    Elements getAffinity(){
        return this.affinity;
    }

    private Attributes getCalcSpace(){
        return calc_space;
    }

    @Override
    public Attributes getTranscription() {
        BaseAttributes transcripted_baseattrib = this.getBaseAttrib().getTranscription();
        Elements transcripted_affinity = this.getAffinity().getTranscription();
        return new Attributes(transcripted_baseattrib, transcripted_affinity);
    }

    @Override
    public Attributes round() {
        this.getBaseAttrib().round();
        this.getAffinity().round();
        return this;
    }

    @Override
    public void makeEqual(Stats target) {
        if (canMakeEqual(target)) {
            this.getBaseAttrib().makeEqual(((Attributes) target).getBaseAttrib());
            this.getAffinity().makeEqual(((Attributes) target).getAffinity());
        }
    }

    @Override
    public Stats sum(Stats target) {
        if(this.getStatsType().equals(target.getStatsType())){
            BaseAttributes added_baseattrib = (BaseAttributes) this.getBaseAttrib().sum(((Attributes) target).getBaseAttrib());
            added_baseattrib.makeEqual(this.getCalcSpace().getBaseAttrib());
            Elements added_affinity = (Elements) this.getAffinity().sum(((Attributes) target).getAffinity());
            added_affinity.makeEqual(this.getCalcSpace().getAffinity());
            target.setFinalCalclationSuccess(true);
            return this.getCalcSpace();
        }
        else {
            target.setFinalCalclationSuccess(false);
            return target;
        }
    }

    @Override
    public Stats product(Stats target) {
        if(this.getStatsType().equals(target.getStatsType())){
            BaseAttributes multiplied_baseattrib = (BaseAttributes) this.getBaseAttrib().product(((Attributes) target).getBaseAttrib());
            multiplied_baseattrib.makeEqual(this.getCalcSpace().getBaseAttrib());
            Elements multiplied_affinity = (Elements) this.getAffinity().product(((Attributes) target).getAffinity());
            multiplied_affinity.makeEqual(this.getCalcSpace().getAffinity());
            target.setFinalCalclationSuccess(true);
            return this.getCalcSpace();
        }
        else {
            target.setFinalCalclationSuccess(false);
            return target;
        }
    }

    @Override
    public String toString() {
        return "Attributes{" +
                "base_attrib=" + base_attrib +
                ", affinity=" + affinity +
                '}';
    }

    public static void main(String[] args) {
        Attributes attributesA = new Attributes(new BaseAttributes(new double[]{8, 9, 10, 6, 5}), new Elements(new double[]{2, 2, 2, 2}));
        Attributes attributesB = new Attributes(new BaseAttributes(new double[]{10, 11, 6, 6, 6}), new Elements(new double[]{2, 2, 2, 2}));
        System.out.println(attributesA.sum(attributesB));

        System.out.println(attributesA.getCalcSpace());
        System.out.println(attributesA);
        System.out.println(attributesB);
    }
}

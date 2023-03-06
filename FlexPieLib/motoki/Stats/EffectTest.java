package com.test.gamelevelfirst.Stats;

import com.test.gamelevelfirst.CalcGuide;
import com.test.gamelevelfirst.CalcOwner;
import com.test.gamelevelfirst.Stats.Elements;
import com.test.gamelevelfirst.Stats.Physics;

import com.test.gamelevelfirst.calculation.ArithmericOperationable;
import com.test.gamelevelfirst.calculation.MulWrapper;
import com.test.gamelevelfirst.calculation.SumWrapper;

import org.apache.commons.math3.stat.descriptive.summary.Sum;

import java.util.ArrayList;

public class EffectTest extends SumWrapper<Physics> {
    public EffectTest(){
        super();
        this.setContent(Physics.getInstance(50));
        this.dir = "other";
    }

    public ArithmericOperationable getContent(){
        return this.content;
    }



    public static void main(String[] args) {
        Physics p100 = Physics.getInstance(100);
        Elements ele1 = Elements.getInstance(0,10,10,0);
        BaseAttributes bsAttr = new BaseAttributes(new double[]{10, 10, 8, 8, 5});
        Damage dmg = new Damage(new Physics(80), new Elements(new double[]{0, 0, 0, 30}));
        Attributes attrib = new Attributes(new BaseAttributes(new double[]{3, 2, 2, 2, 3}), new Elements(new double[]{1, 1, 1, 1}));
        ArrayList stats_list = new ArrayList();
        //stats_list.add(dmg);
        //stats_list.add(ele1);
        stats_list.add(bsAttr);
        stats_list.add(attrib);
        stats_list.add(p100);

        StatsListOwner statsListOwner = new StatsListOwner("subject");
        statsListOwner.setStatsList(stats_list);

        EffectTest effectA = new EffectTest();
        EffectB effectB = new EffectB();
        EffectC effectC = new EffectC();
        EleEffectA eleEffectA = new EleEffectA();
        EleEffectB eleEffectB = new EleEffectB();
        EleEffectC eleEffectC = new EleEffectC();
        BaseAttrA baseAttrA = new BaseAttrA();
        BaseAttrB baseAttrB = new BaseAttrB();
        BaseAttrC baseAttrC = new BaseAttrC();
        DamageA damageA = new DamageA();
        DamageB damageB = new DamageB();
        DamageC damageC = new DamageC();
        AttribA attribA = new AttribA();
        AttribB attribB = new AttribB();
        AttribC attribC = new AttribC();

        //Physics p150 = (Physics) effectA.process(p100);
        //Physics pp = p150;
        //effectB.process(p150);
        //System.out.println(p150.physics);
        //System.out.println(pp.physics);

        CalcOwner calcOwnerA = new CalcOwner();
        calcOwnerA.setOwner("object");
        ArrayList<CalcGuide> calcGuides = new ArrayList<>();
        calcGuides.add(attribB);
        calcGuides.add(attribC);
        calcGuides.add(effectC);
        calcGuides.add(eleEffectB);
        calcGuides.add(effectA);
        calcGuides.add(damageB);
        calcGuides.add(attribA);
        calcGuides.add(baseAttrA);
        calcGuides.add(eleEffectC);
        calcGuides.add(damageA);
        calcGuides.add(effectB);
        calcGuides.add(damageC);
        calcGuides.add(baseAttrC);
        calcGuides.add(baseAttrB);
        calcGuides.add(eleEffectA);
        calcOwnerA.setCalcGuides(calcGuides);

        StatsOwner statsOwnerA = new StatsOwner("subject");
        statsOwnerA.setStats(p100);


        //calcOwnerA.process(statsOwnerA);
        calcOwnerA.process(statsListOwner);

        //System.out.println(p100.calc_space);
        //statsListOwner.getStatsList().stream().forEach(it->System.out.println(it));
        calcOwnerA.calcGuides.stream().forEach(System.out::println);
        System.out.println("\n");
        statsListOwner.getStatsList().stream().forEach(System.out::println);
        System.out.println("\n");
        calcOwnerA.stored_calcGuides.stream().forEach(System.out::println);
        System.out.println("\n");
        System.out.println(p100);
        System.out.println(ele1);
        System.out.println(bsAttr);
        System.out.println(dmg);
        System.out.println(attrib);
        System.out.println("\n");
        System.out.println(effectA.getContent());
        System.out.println(effectB.getContent());
        System.out.println(effectC.getContent());
        System.out.println(eleEffectB.getContent());
        System.out.println(eleEffectA.getContent());
        System.out.println(eleEffectC.getContent());
        System.out.println(baseAttrA.getContent());
        System.out.println(baseAttrB.getContent());
        System.out.println(baseAttrC.getContent());
        System.out.println(damageA.getContent());
        System.out.println(damageB.getContent());
        System.out.println(damageC.getContent());
        System.out.println(attribA.getContent());
        System.out.println(attribB.getContent());
        System.out.println(attribC.getContent());
    }
}

class EffectB extends MulWrapper{
    public EffectB(){
        super();
        this.setContent(Physics.getInstance(1.3));
        this.dir = "other";
    }

    public ArithmericOperationable getContent(){
        return this.content;
    }
}

class EffectC extends MulWrapper{
    public EffectC(){
        super();
        this.setContent(Physics.getInstance(1.1));
        this.dir = "other";
    }

    public ArithmericOperationable getContent(){
        return this.content;
    }
}

class EleEffectA extends SumWrapper{
    public EleEffectA(){
        super();
        this.setContent(Elements.getInstance(50,50,25,10));
        this.dir = "other";
    }

    public ArithmericOperationable getContent(){
        return this.content;
    }
}

class EleEffectB extends SumWrapper{
    public EleEffectB(){
        super();
        this.setContent(Elements.getInstance(20,40,20,50));
        this.dir = "other";
    }

    public ArithmericOperationable getContent(){
        return this.content;
    }
}

class EleEffectC extends MulWrapper{
    public EleEffectC(){
        super();
        this.setContent(Elements.getInstance(1.0,1.0,1.3,1.1));
        this.dir = "self";
    }

    public ArithmericOperationable getContent(){
        return this.content;
    }
}

class BaseAttrA extends SumWrapper{
    public BaseAttrA(){
        super();
        this.setContent(new BaseAttributes(new double[]{1,2,0,0,0}));
        this.dir = "other";
    }
    public ArithmericOperationable getContent(){
        return this.content;
    }
}

class BaseAttrB extends SumWrapper{
    public BaseAttrB(){
        super();
        this.setContent(new BaseAttributes(new double[]{0,0,2,2,1}));
        this.dir = "other";
    }
    public ArithmericOperationable getContent(){
        return this.content;
    }
}

class BaseAttrC extends MulWrapper{
    public BaseAttrC(){
        super();
        this.setContent(new BaseAttributes(new double[]{2, 2, 2, 2, 2}));
        this.dir = "self";
    }
    public ArithmericOperationable getContent(){
        return this.content;
    }
}

class DamageA extends SumWrapper{
    public DamageA(){
        super();
        this.dir = "other";
        this.setContent(new Damage(new Physics(10), new Elements(new double[]{20, 25, 20, 30})));
    }
    public ArithmericOperationable getContent(){
        return this.content;
    }
}

class DamageB extends MulWrapper{
    public DamageB(){
        super();
        this.dir = "other";
        this.setContent(new Damage(new Physics(1.5), new Elements(new double[]{1.5, 2, 1.1, 1.5})));
    }
    public ArithmericOperationable getContent(){
        return this.content;
    }
}

class DamageC extends SumWrapper{
    public DamageC(){
        super();
        this.dir = "self";
        this.setContent(new Damage(new Physics(5), new Elements(new double[]{25, 25, 15, 30})));
    }
    public ArithmericOperationable getContent(){
        return this.content;
    }
}

class AttribA extends SumWrapper{
    public AttribA(){
        super();
        this.dir = "other";
        this.setContent(new Attributes(new BaseAttributes(new double[]{10, 6, 3, 3, 8}), new Elements(new double[]{1, 1, 1, 1})));
    }
    public ArithmericOperationable getContent(){
        return this.content;
    }
}

class AttribB extends  SumWrapper{
    public AttribB(){
        super();
        this.dir = "other";
        this.setContent(new Attributes(new BaseAttributes(new double[]{9, 8, 2, 2, 8}), new Elements(new double[]{4, 4, 4, 4})));
    }
    public ArithmericOperationable getContent(){
        return this.content;
    }
}

class AttribC extends MulWrapper{
    public AttribC(){
        super();
        this.dir = "self";
        this.setContent(new Attributes(new BaseAttributes(new double[]{9, 8, 2, 2, 8}), new Elements(new double[]{1, 1, 1, 5})));
    }
    public ArithmericOperationable getContent(){
        return this.content;
    }
}
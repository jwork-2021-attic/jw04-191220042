package com.anish.calabashbros;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MonsterFactory {
    ArrayList<Integer> no=new ArrayList<>();
    ArrayList<Monster> army=new ArrayList<>();
    private int length;
    private World world;

    public void initFactory(int length,World world)
    {
        this.length=length*length;
        this.world=world;
        for(int i=0;i<this.length;i++){
            no.add(i+1);
        }
        Collections.shuffle(no);
    }

    public ArrayList<Monster> product() throws IOException {
        for (int i = 0; i < this.length; i++) {
            int number=no.get(i);
            int []theRGB=TestImage.returnRGB(number);
            Monster mon=new Monster(new Color(theRGB[0],theRGB[1],theRGB[2]),number,world);
            army.add(mon);
        }
        return army;
    }
}

package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import com.anish.calabashbros.BubbleSorter;
import com.anish.calabashbros.Monster;
import com.anish.calabashbros.MonsterFactory;
import com.anish.calabashbros.World;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    final int length=8;
    private World world;
    private Monster[][] bros;
    private Monster[] line;
    String[] sortSteps;
    private MonsterFactory monFac;



    public WorldScreen() throws IOException {
        world = new World();
        monFac = new MonsterFactory();
        monFac.initFactory(length,world);
        bros = new Monster[length][length];

        ArrayList<Monster> monarmy=monFac.product();
        for (int i=0;i<length*length;i++) {
            bros[i / length][i % length] = monarmy.get(i);
        }

        int basex=2,basey=2;
        for (int j = 0; j <length ; j++) {
            for (int k = 0; k <length ; k++) {
                world.put(bros[j][k],basex+j*2,basey+k*2);
            }
        }

        line=new Monster[length*length];
        for (int j = 0; j <length ; j++) {
            for (int k = 0; k <length ; k++) {
                line[j*length+k]=bros[j][k];
            }
        }
        BubbleSorter<Monster> b = new BubbleSorter<>();
        b.load(line);
        b.sort();

        sortSteps = this.parsePlan(b.getPlan());
    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(Monster[] bros, String step) {
        String[] couple = step.split("<->");
        getBroByRank(bros, Integer.parseInt(couple[0])).swap(getBroByRank(bros, Integer.parseInt(couple[1])));
    }

    private Monster getBroByRank(Monster[] bros, int rank) {
        for (Monster bro : bros) {
            if (bro.getRank() == rank) {
                return bro;
            }
        }
        return null;
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    int i = 0;

    @Override
    public Screen respondToUserInput(KeyEvent key) {

        if (i < this.sortSteps.length) {
            this.execute(line, sortSteps[i]);
            i++;
        }

        return this;
    }

}

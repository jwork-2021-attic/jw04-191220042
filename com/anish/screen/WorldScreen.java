package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.anish.calabashbros.BubbleSorter;
import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.Monster;
import com.anish.calabashbros.World;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private Monster[] bros;
    String[] sortSteps;

    public WorldScreen() {
        world = new World();

        bros = new Monster[7];

        bros[3] = new Monster(new Color(204, 0, 0), 1, world);
        bros[5] = new Monster(new Color(255, 165, 0), 2, world);
        bros[1] = new Monster(new Color(252, 233, 79), 3, world);
        bros[0] = new Monster(new Color(78, 154, 6), 4, world);
        bros[4] = new Monster(new Color(50, 175, 255), 5, world);
        bros[6] = new Monster(new Color(114, 159, 207), 6, world);
        bros[2] = new Monster(new Color(173, 127, 168), 7, world);

        world.put(bros[0], 10, 10);
        world.put(bros[1], 12, 10);
        world.put(bros[2], 14, 10);
        world.put(bros[3], 16, 10);
        world.put(bros[4], 18, 10);
        world.put(bros[5], 20, 10);
        world.put(bros[6], 22, 10);

        BubbleSorter<Monster> b = new BubbleSorter<>();
        b.load(bros);
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
            this.execute(bros, sortSteps[i]);
            i++;
        }

        return this;
    }

}

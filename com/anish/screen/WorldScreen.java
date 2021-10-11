package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.anish.calabashbros.BubbleSorter;
import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.Obstacle;
import com.anish.calabashbros.World;

import asciiPanel.AsciiPanel;
import maze.MazeGenerator;

public class WorldScreen implements Screen {

    private World world;
    private Calabash bros;
    private MazeGenerator mazeGenerator;
    private int myX,myY;
    private int maze[][];
    final int length=40;

    public WorldScreen() {
        world = new World();
        mazeGenerator=new MazeGenerator(length);
        mazeGenerator.generateMaze();

        bros = new Calabash(new Color(252, 233, 79), 1, world);
        world.put(bros,0,0);
        myX=0;myY=0;
        maze=mazeGenerator.getMaze();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j <length; j++) {
                if(maze[i][j]==0) {
                    Obstacle obs = new Obstacle(new Color(0, 233, 0), (char) 10, world);
                    world.put(obs,i,j);
                }
            }
        }
    }


    private void execute(Calabash bros,int xPos,int yPos) {
        System.out.println(xPos);
        System.out.println(yPos);
        bros.moveTo(myX,myY);
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
        if(key.getKeyCode()==KeyEvent.VK_RIGHT&&myX<length&&maze[myX+1][myY]==1) {
            Obstacle obs = new Obstacle(new Color(200, 0, 0), (char) 6, world);
            world.put(obs,myX,myY);
            myX++;
            execute(bros,myX,myY);
        }
        if(key.getKeyCode()==KeyEvent.VK_UP&&myY>0&&maze[myX][myY-1]==1) {
            Obstacle obs = new Obstacle(new Color(200, 0, 0), (char) 6, world);
            world.put(obs,myX,myY);
            myY--;
            execute(bros,myX,myY);
        }
        if(key.getKeyCode()==KeyEvent.VK_DOWN&&myY<length&&maze[myX][myY+1]==1) {
            Obstacle obs = new Obstacle(new Color(200, 0, 0), (char) 6, world);
            world.put(obs,myX,myY);
            myY++;
            execute(bros,myX,myY);
        }
        if(key.getKeyCode()==KeyEvent.VK_LEFT&&myX>0&&maze[myX-1][myY]==1) {
            Obstacle obs = new Obstacle(new Color(200, 0, 0), (char) 6, world);
            world.put(obs,myX,myY);
            myX--;
            execute(bros,myX,myY);
        }

        return this;
    }

}

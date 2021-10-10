package com.anish.calabashbros;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestImage {
    public static int[] returnRGB(int i) throws IOException {
        File file=new File("c256.png");
        BufferedImage image=null;
        image= ImageIO.read(file);
        int[] rgb=new int[3];
        int pixel=image.getRGB(36*(i%16),27*(i/16));

        rgb[0]=(pixel&0xff0000)>>16;
        rgb[1]=(pixel&0xff00)>>8;
        rgb[2]=(pixel&0xff);

        return rgb;
    }

}

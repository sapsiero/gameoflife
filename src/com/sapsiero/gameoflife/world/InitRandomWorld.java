/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sapsiero.gameoflife.world;

import static java.lang.Math.random;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author tim
 */
public class InitRandomWorld extends InitWorld {
        
    @Override
    public Rectangle[][] init(int h, int v) {
        Rectangle[][] newWorld = new Rectangle[h][v];
        int x = 0, y = 0;
        for (int i = 0; i < 2000; i++) {
            do {
                x = (int)(random() * (double)h);
                y = (int)(random() * (double)v);
            } while (newWorld[x][y] != null);
            newWorld[x][y] = newRec(x, y);
        }
        return newWorld;
    }
}
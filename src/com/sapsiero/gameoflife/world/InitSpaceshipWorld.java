/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sapsiero.gameoflife.world;

import javafx.scene.shape.Rectangle;

/**
 *
 * @author tim
 */
public class InitSpaceshipWorld extends InitWorld {
    
    @Override
    public Rectangle[][] init(int h, int v){
        Rectangle[][] newWorld = new Rectangle[h][v];

        newWorld[0][5] = newRec(0, 5);
        newWorld[1][5] = newRec(1, 5);
        newWorld[0][6] = newRec(0, 6);
        newWorld[1][6] = newRec(1, 6);

        newWorld[6][5] = newRec(6, 5);
        newWorld[7][5] = newRec(7, 5);
        newWorld[8][6] = newRec(8, 6);
        newWorld[9][6] = newRec(9, 6);
        newWorld[8][4] = newRec(8, 4);
        newWorld[9][4] = newRec(9, 4);
        newWorld[9][3] = newRec(9, 3);
        newWorld[9][7] = newRec(9, 7);
        newWorld[10][8] = newRec(10, 8);
        newWorld[10][2] = newRec(10, 2);
        newWorld[11][5] = newRec(11, 5);
        newWorld[12][8] = newRec(12, 8);
        newWorld[12][2] = newRec(12, 2);
        newWorld[12][7] = newRec(12, 7);
        newWorld[12][3] = newRec(12, 3);

        newWorld[15][3] = newRec(15, 3);
        newWorld[15][4] = newRec(15, 4);
        newWorld[15][5] = newRec(15, 5);
        newWorld[15][6] = newRec(15, 6);
        newWorld[16][3] = newRec(16, 3);
        newWorld[16][6] = newRec(16, 6);
        newWorld[16][7] = newRec(16, 7);
        newWorld[17][4] = newRec(17, 4);
        newWorld[17][6] = newRec(17, 6);
        newWorld[17][7] = newRec(17, 7);

        newWorld[19][5] = newRec(19, 5);
        newWorld[19][6] = newRec(19, 6);
        newWorld[20][6] = newRec(20, 6);

        newWorld[23][0] = newRec(23, 0);
        newWorld[23][1] = newRec(23, 1);
        newWorld[23][3] = newRec(23, 3);
        newWorld[23][5] = newRec(23, 5);
        newWorld[23][6] = newRec(23, 6);
        newWorld[24][0] = newRec(24, 0);
        newWorld[24][6] = newRec(24, 6);
        newWorld[25][1] = newRec(25, 1);
        newWorld[25][5] = newRec(25, 5);
        newWorld[26][2] = newRec(26, 2);
        newWorld[26][3] = newRec(26, 3);
        newWorld[26][4] = newRec(26, 4);

        newWorld[34][3] = newRec(34, 3);
        newWorld[34][4] = newRec(34, 4);
        newWorld[35][3] = newRec(35, 3);
        newWorld[35][4] = newRec(35, 4);

        return newWorld;
    }
    
}
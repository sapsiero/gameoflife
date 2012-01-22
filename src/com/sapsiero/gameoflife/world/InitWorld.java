/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sapsiero.gameoflife.world;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author tim
 */
public abstract class InitWorld {
    
    public static Rectangle newRec(int i, int j) {
        Rectangle rect = new Rectangle(10, 10, Color.WHITE);
        rect.setX(10*i);
        rect.setY(10*j);
        rect.setOpacity(0);
        return rect;
    }
    
    public abstract Rectangle[][] init(int h, int v);
}
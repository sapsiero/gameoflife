/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sapsiero.gameoflife;

import com.sapsiero.gameoflife.world.InitRandomWorld;
import com.sapsiero.gameoflife.world.InitSpaceshipWorld;
import com.sapsiero.gameoflife.world.InitWorld;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author tim
 */
public class GameOfLife extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private final int HFIELDS = 100;
    private final int VFIELDS = 60;
    
    private Group recGroup;
    private Rectangle[][] world;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        Group root = new Group();
        Rectangle rec = new Rectangle(HFIELDS * 10, VFIELDS * 10);
        rec.setFill(Color.DEEPSKYBLUE);
        root.getChildren().add(rec);
        Group vlines = new Group();
        for (int i = 1; i < VFIELDS; i++) {
            Line line = new Line(0, 10 * i, HFIELDS * 10,  10 * i);
            line.setStrokeWidth(0.1);
            vlines.getChildren().add(line);
        }
        root.getChildren().add(vlines);
        Group hlines = new Group();
        for (int i = 1; i < HFIELDS; i++) {
            Line line = new Line(10 * i, 0,  10 * i, VFIELDS * 10);
            line.setStrokeWidth(0.1);
            hlines.getChildren().add(line);
        }
        root.getChildren().add(hlines);
        recGroup = new Group();
        recGroup.setEffect(new BoxBlur(3, 3, 2));
        root.getChildren().add(recGroup);
        
        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane, HFIELDS * 10, VFIELDS * 10 + 50, Color.CORAL);
        pane.setCenter(root);
        
        HBox buttons = new HBox(10);
        buttons.setPrefHeight(50);
        buttons.setPadding(new Insets(10));
        pane.setBottom(buttons);
        
        Button start = new Button("Start");
        start.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent evt) {
                clearPane(new InitRandomWorld());
            }
        });
        Button spaceship = new Button("Spaceship");
        spaceship.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent evt) {
                clearPane(new InitSpaceshipWorld());       
            }
        });
        Button next = new Button("Next Cycle");
        next.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent evt) {
                Rectangle[][] newWorld = moveWorld();
                refreshPane(newWorld);
            }
        });
        next.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent evt) {
                Rectangle[][] newWorld = moveWorld();
                refreshPane(newWorld);
            }
        });
        buttons.getChildren().addAll(start, spaceship, next);
        
        //Scene scene = new Scene(root, HFIELDS * 10, VFIELDS * 10, Color.DARKSEAGREEN);
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }
    
    private Rectangle[][] moveWorld(){
        Rectangle[][] world2 = new Rectangle[HFIELDS][VFIELDS];
        for (int i = 0; i < HFIELDS; i++) {
            for (int j = 0; j < VFIELDS; j++) {
                int count = countNeighbors(i, j);
                if ((count == 2 || count == 3) && world[i][j] != null) {
                    world2[i][j] = world[i][j];
                } else if (count == 3) {
                    world2[i][j] = InitWorld.newRec(i, j);
                }
            }
        }
        return world2;
    }
    
    private int countRow(int j, int a, int ai){
        int count = 0;
        for (int b = -1; b < 2; b++) {
            if (b + j >= 0 && b + j < VFIELDS) {
                if ((a != 0 || b != 0) && world[ai][b+j] != null) {
                    count++;
                } 
            } else if (b + j < 0) {
                if ((a != 0 || b != 0) && world[ai][VFIELDS - 1] != null) {
                    count++;
                } 
            } else if (b + j >= VFIELDS) {
                if ((a != 0 || b != 0) && world[ai][0] != null) {
                    count++;
                } 
            }
        }
        return count;
    }
    
    private int countNeighbors(int i, int j){
        int count = 0;
        for (int a = -1; a < 2; a++) {
            if (a + i >= 0 && a + i < HFIELDS) {
                count += countRow(j, a, a+i);
            } else if (a + i >= HFIELDS) {
                count += countRow(j, a, 0);
            } else if (a + i < 0) {
                count += countRow(j, a, HFIELDS - 1);
            }
        }
        return count;
    }
    
    private void clearPane(InitWorld worldPane) {
        this.world  = new Rectangle[HFIELDS][VFIELDS];
        Timeline timeline = new Timeline();
        for (Node node : recGroup.getChildren()) {
            Rectangle rec = (Rectangle)node;
            timeline.getKeyFrames().add(
                new KeyFrame(new Duration(200), // set end position at 1s
                    new KeyValue(rec.opacityProperty(), 0)
                )
            );
        }
        timeline.setOnFinished(new InitWorldEventHandler(worldPane));
        timeline.play();
    }
    
    private void refreshPane(Rectangle[][] newWorld) {
        ArrayList<Rectangle> list = new ArrayList();
        
        Timeline timeline = new Timeline();
        for (int i = 0; i < HFIELDS; i++) {
            for (int j = 0; j < VFIELDS; j++) {
                if (world[i][j] != null && newWorld[i][j] == null) {
                    timeline.getKeyFrames().add(
                        new KeyFrame(new Duration(200), // set end position at 1s
                            new KeyValue(world[i][j].opacityProperty(), 0)
                        )
                    );
                    list.add(world[i][j]);
                } else if (world[i][j] == null && newWorld[i][j] != null) {
                    recGroup.getChildren().add(newWorld[i][j]);
                    timeline.getKeyFrames().addAll(
                        new KeyFrame(Duration.ZERO, // set end position at 1s
                            new KeyValue(newWorld[i][j].opacityProperty(), 0)
                        ),
                        new KeyFrame(new Duration(200), // set end position at 1s
                            new KeyValue(newWorld[i][j].opacityProperty(), 1)
                        )
                    );
                }
            }
        }
        
        world = newWorld;
        
        timeline.setOnFinished(new RemoveObsoleteElements(list));
        
        // play 0.2s of animation
        timeline.play();
    }
    
    private class RemoveObsoleteElements implements EventHandler<ActionEvent> {
        
        ArrayList<Rectangle> list;
        
        public RemoveObsoleteElements(ArrayList<Rectangle> list) {
            this.list = list;
        }
        
        @Override
        public void handle(ActionEvent evt) {
            Logger.getLogger(GameOfLife.class.getName()).log(Level.INFO, "Deleting {0} elements.", list.size());
            for (Rectangle rec : list){
                recGroup.getChildren().remove(rec);
            }
        }
    }
    
    private class InitWorldEventHandler implements EventHandler<ActionEvent> {
        
        private InitWorld worldPane;
        
        public InitWorldEventHandler(InitWorld worldPane) {
            this.worldPane = worldPane;
        }
        
        @Override
        public void handle(ActionEvent evt) {
            recGroup.getChildren().removeAll(recGroup.getChildren());
            refreshPane(worldPane.init(HFIELDS, VFIELDS));
        }
        
    }
}
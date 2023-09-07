// Player Class
// Aimed Completion Date: May 23, 2022

package com.example.polyproj;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Polygon;

public class Player extends Shape {
    public double interiorAngle = 60;
    public Player () {
    }

    public void setImage(String filename) {
        image = new Image("file:\\" + filename, 60, 60, false, false);
        hitBox.width = image.getWidth();
        hitBox.height = image.getHeight();
    }

    // Determines whether a shape and a player overlap - basic collision testing.
    public boolean overlaps (Shape shape) {
        return this.getHitBox().overlaps(shape.getHitBox());
    }
}

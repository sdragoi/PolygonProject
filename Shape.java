// Shape Class
// Aimed Completion Date: May 23, 2022

package com.example.polyproj;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Shape {
    public double interiorAngle;
    private int shapeType;
    public Vector velocity;
    public Vector position;
    public Image image;
    public HitBox hitBox;

    public Shape (int shapeType, int position) {
        velocity = new Vector(0,0);
        hitBox = new HitBox(0,0,0,0);
        this.position = new Vector(position, 0);
        getHitBox();
        switch(shapeType) {
            case 1:
                interiorAngle = 60;
                setImage("C:\\Users\\julma\\IdeaProjects\\demo\\PolyProj\\src\\tri.jpg");
                break;
            case 2:
                interiorAngle = 90;
                setImage("C:\\Users\\julma\\IdeaProjects\\demo\\PolyProj\\src\\square.png");
                break;
            case 3:
                interiorAngle = 108;
                setImage("C:\\Users\\julma\\IdeaProjects\\demo\\PolyProj\\src\\pentagon.jpg");
                break;
            case 4:
                interiorAngle = 120;
                setImage("C:\\Users\\julma\\IdeaProjects\\demo\\PolyProj\\src\\hexagonRe.png");
                break;
            case 5:
                interiorAngle = 128.57;
                setImage("C:\\Users\\julma\\IdeaProjects\\demo\\PolyProj\\src\\heptagon.jpg");
                break;
            case 6:
                interiorAngle = 135;
                setImage("C:\\Users\\julma\\IdeaProjects\\demo\\PolyProj\\src\\octogon.png");
                break;
            case 7:
                interiorAngle = 140;
                setImage("C:\\Users\\julma\\IdeaProjects\\demo\\PolyProj\\src\\nongon.png");
                break;
            case 8:
                interiorAngle = 144;
                setImage("C:\\Users\\julma\\IdeaProjects\\demo\\PolyProj\\src\\decagon.jpg");
                break;
        }
    }

    public Shape () {
        position = new Vector(0,0);
        velocity = new Vector(0,0);
        hitBox = new HitBox(0,0,0,0);
    }

    public void setPos(double x, double y) {
        position.set(x, y);
    }

    // Sets an image (jpg or png) for the shape
    public void setImage(String filename) {
        image = new Image("file:\\" + filename, 100, 100, false, false);
        hitBox.width = image.getWidth();
        hitBox.height = image.getHeight();
    }

    // Creates hitbox from the given position
    public HitBox getHitBox() {
        hitBox.x = position.x;
        hitBox.y = position.y;
        return hitBox;
    }
    
    // Renders Shape
    public void render (GraphicsContext context) {
        context.drawImage(image, position.x, position.y);
    }
}

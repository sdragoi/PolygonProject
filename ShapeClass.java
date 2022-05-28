package application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.Node;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//JM MS
//APCS
//Class for all shapes
//5/23/2022
public class ShapeClass extends ImageView{
	
    public double interiorAngle;
    public int shapeType;
    public String shapeName;
    public Vector velocity;
    public Vector position;
    public Image image;
    public HitBox hitBox;

    public ShapeClass (int shapeType, int positionx, int positiony) {
        velocity = new Vector(0,0);
        hitBox = new HitBox(0,0,0,0);
        this.position = new Vector(positionx, positiony);
        getHitBox();
        switch(shapeType) {
            case 1:
                interiorAngle = 60;
                setImage("//Users/stellaaldi/Desktop/PolygonProjectImages/tri.png");
                shapeName = "triangle";
                break;
            case 2:
                interiorAngle = 90;
                setImage("//Users/stellaaldi/Desktop/PolygonProjectImages/square.png");
                shapeName = "square";
                break;
            case 3:
                interiorAngle = 108;
                setImage("//Users/stellaaldi/Desktop/PolygonProjectImages/pentagon.png");
                shapeName = "pentagon";
                break;
            case 4:
                interiorAngle = 120;
                setImage("//Users/stellaaldi/Desktop/PolygonProjectImages/hexagon.png");
                shapeName = "hexagon";
                break;
            case 5:
                interiorAngle = 128.57;
                setImage("//Users/stellaaldi/Desktop/PolygonProjectImages/heptagon.png");
                shapeName = "heptagon";
                break;
            case 6:
                interiorAngle = 135;
                setImage("//Users/stellaaldi/Desktop/PolygonProjectImages/octogon.png");
                shapeName = "octogon";
                break;
            case 7:
                interiorAngle = 140;
                setImage("//Users/stellaaldi/Desktop/PolygonProjectImages/nonagon.png");
                shapeName = "nonagon";
                break;
            case 8:
                interiorAngle = 144;
                setImage("//Users/stellaaldi/Desktop/PolygonProjectImages/decagon.png");
                shapeName = "decagon";
                break;
        }
    }

    public ShapeClass (int x) {

        //position = new Vector(positionx,positiony);
        velocity = new Vector(0,0);
        hitBox = new HitBox(0,0,0,0);
        switch(((int) (Math.random()* 8 + 1))) {
        case 1:
            interiorAngle = 60;
            setImage(getImage("//Users/stellaaldi/Desktop/PolygonProjectImages/tri.png"));
            shapeType = 1;
            break;
        case 2:
            interiorAngle = 90;
            setImage(getImage("//Users/stellaaldi/Desktop/PolygonProjectImages/square.png"));
            shapeType = 2;
            break;
        case 3:
            interiorAngle = 108;
            setImage(getImage("//Users/stellaaldi/Desktop/PolygonProjectImages/pentagon.png"));
            shapeType = 3;
            break;
        case 4:
            interiorAngle = 120;
            setImage(getImage("//Users/stellaaldi/Desktop/PolygonProjectImages/hexagon.png"));
            shapeType = 4;
            break;
        case 5:
            interiorAngle = 128.57;
            setImage(getImage("//Users/stellaaldi/Desktop/PolygonProjectImages/heptagon.png"));
            shapeType = 5;
            break;
        case 6:
            interiorAngle = 135;
            getImage(("//Users/stellaaldi/Desktop/PolygonProjectImages/octogon.png"));
            shapeType = 6;
            break;
        case 7:
            interiorAngle = 140;
            setImage(getImage("//Users/stellaaldi/Desktop/PolygonProjectImages/nonagon.png"));
            shapeType = 7;
            break;
        case 8:
            interiorAngle = 144;
            setImage(getImage("//Users/stellaaldi/Desktop/PolygonProjectImages/decagon.png"));
            shapeType = 8;
            break;
    }
    }
    
    public ShapeClass(){}

    public void setPos(double x, double y) {
        position.set(x, y);
    }

    //Sets an image (jpg or png) for the shape
    /*public void setImage(String filename) {
        image = new Image("file://" + filename, 150, 180, false, false);
        hitBox.width = image.getWidth();
        hitBox.height = image.getHeight();
    }*/
    
    public Image getImage(String filename) {
        return new Image("file://" + filename, 150, 180, false, false);
    }

    //Creates hitbox from position
    public HitBox getHitBox() {
        hitBox.x = position.x;
        hitBox.y = position.y;
        return hitBox;
    }
    //Renders ShapeClass
    public void render (GraphicsContext context) {
        context.drawImage(image, this.getLayoutX(), this.getLayoutY());
    }

}

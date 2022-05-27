package application;
//JM MS
//Main runtime class
//APCS
//5/23/2022
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
//import javax.xml.datatype.Duration;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.*;

public class Main extends Application {
	
	double speed;
	double falling;
	AnimationTimer timer;
	List drop = new ArrayList();
	
    public static void main(String[] args) {
        launch();
        new GameFrame();
    }
    

    //method that moves the shapes down the screen and appropriately tests collision
    public double dropShapes(Player player, ArrayList<Shape> shapeRow, GraphicsContext context) {

        double intAng = 60;
        for (int i = 0; i < shapeRow.size(); i++) {
            Shape shape = shapeRow.get(i);
            if (player.overlaps(shape)) {
                if (shape.interiorAngle == 60) {
                    player.interiorAngle += shape.interiorAngle / 3;
                    intAng = player.interiorAngle;
                }
                if (shape.interiorAngle == 90) {
                    player.interiorAngle -= shape.interiorAngle / 3;
                    intAng = player.interiorAngle;
                }
                if (shape.interiorAngle == 108) {
                    player.interiorAngle += shape.interiorAngle / 3;
                    intAng = player.interiorAngle;
                }
                if (shape.interiorAngle == 120) {
                    player.interiorAngle -= shape.interiorAngle / 3;
                    intAng = player.interiorAngle;
                }
                if (shape.interiorAngle == 128.57) {
                    player.interiorAngle += shape.interiorAngle / 3;
                    intAng = player.interiorAngle;
                }
                if (shape.interiorAngle == 135) {
                    player.interiorAngle -= shape.interiorAngle / 3;
                    intAng = player.interiorAngle;
                }
                if (shape.interiorAngle == 140) {
                    player.interiorAngle += 0;
                    intAng = player.interiorAngle;
                }
                if (shape.interiorAngle == 144) {
                    player.interiorAngle -= shape.interiorAngle / 3;
                    intAng = player.interiorAngle;
                }
                shapeRow.clear();
            }
        }
        //sets speed for falling objects
        for (int i = 0; i < shapeRow.size(); i++) {
            shapeRow.get(i).position.add(0, 2.5);
        }
        
        return intAng;	
    }

    //Method to create an Arraylist with 5 shapes in it
    public ArrayList<Shape> shapeCreation () {
        ArrayList<Shape> shapeRow = new ArrayList<Shape>();
        int shapeCounter = 5;
        for (int i = 0; i < shapeCounter; i++) {
            Shape shape = new Shape((int)Math.ceil(Math.random() * 8), ((60 * (i)) + (i * 50)- 75), 0);
            shape.position.set(100);
            shapeRow.add(shape);
        }
        return shapeRow;
    }

    @Override
    public void start(Stage stage) throws Exception {
    	
    	//Setting up window for GUI
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);

        //Initializing game Icon and Title.
        Image icon = new Image("file://Users/stellaaldi/Desktop/PolygonProjectImages/circle.png");
        stage.getIcons().add(icon);
        stage.setTitle("Polygon Game");

        //Creating a canvas for the game to run on
        Canvas canvas = new Canvas(545,600);
        GraphicsContext context = canvas.getGraphicsContext2D();

        //Filling the page with white
        root.setCenter(canvas);
        context.setFill(Color.WHITE);
        context.fillRect(0,0,600,600);

        //Creating the player
        Player player = new Player(60);
        player.position.set(250,450);
        player.setImage("//Users/stellaaldi/Desktop/PolygonProjectImages/tri.png");
        player.render(context);
        
        
        
        speed = 1;
        falling = 500;
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(falling), event -> {
        	speed += falling / 3000;
        	drop.add(new Shape());
        	root.getChildren().add((Node)drop.get(drop.size()-1));
        
        }));
        
        timeline.setCycleCount(1000);
		timeline.play();
        
        

        //Creating an ArrayList to store and read the inputs of the player when the game starts
        ArrayList<String> inputList = new ArrayList<String>();
        scene.setOnKeyPressed(
                (KeyEvent event) -> {
                    String keyName = event.getCode().toString();
                    if (!inputList.contains(keyName)) {
                        inputList.add(keyName);
                    }
                }
        );
        scene.setOnKeyReleased(
                (KeyEvent event) -> {
                    String keyName = event.getCode().toString();
                    inputList.remove(keyName);
                }
        );

        //Creating a random ArrayList of Shapes falling from the sky
        /*ArrayList<Shape> shapeRow = shapeCreation();
        ArrayList<Shape> shapeRow1 = shapeCreation();
        ArrayList<Shape> shapeRow2 = shapeCreation();
        ArrayList<Shape> shapeRow3 = shapeCreation(); */

        //Controls player's speed and the falling of the shapes
        AnimationTimer gameLoop = new AnimationTimer() {
            public void handle(long nanoTime) {
                //declaring player speed
                double speedOfPlayer = 500;
                //What happens if the player goes left
                if (inputList.contains("LEFT")){
                    player.velocity.add(-speedOfPlayer,0);
                }
                //What happen if the player goes right
                if (inputList.contains("RIGHT")){
                    player.velocity.add(speedOfPlayer,0);
                }
                //sets speed for player per input press
                player.velocity.scalarMult(1/60.0);
                player.position.add(player.velocity);


                //clear canvas
                context.setFill(Color.WHITE);
                context.fillRect(0, 0, 600, 600);
                
                
                
            	//Initiates falling shapes
                /*double intAng = dropShapes(player, shapeRow, context);
                for (Shape shape : shapeRow) {
                        shape.render(context);
                }
                
                
             
                
              //rendering in the player
                player.render(context);
                
                if (intAng == 60) {
                	player.image = null;
                	player.setImage("//Users/stellaaldi/Desktop/PolygonProjectImages/tri.png");
                }
                if(intAng < 60) {
                	player.image = null;
                	player.position.set(250,300);
                    player.setImage("//Users/stellaaldi/Desktop/PolygonProjectImages/gameover.jpg");
                }
                else if(intAng < 90) {
                	player.image = null;
                	player.setImage("//Users/stellaaldi/Desktop/PolygonProjectImages/square.png");
                	intAng = 90;
                }
                else if(intAng < 108) {
                	player.image = null;
                	player.setImage("//Users/stellaaldi/Desktop/PolygonProjectImages/pentagon.png");
                	intAng = 108;
                }
                else if(intAng < 120) {
                	player.image = null;
                	player.setImage("//Users/stellaaldi/Desktop/PolygonProjectImages/hexagon.png");
                	intAng = 120;
                }
                else if(intAng < 128.57) {
                	player.image = null;
                	player.setImage("//Users/stellaaldi/Desktop/PolygonProjectImages/heptagon.png");
                	intAng = 128.57;
                }
                else if(intAng < 135) {
                	player.image = null;
                	player.setImage("//Users/stellaaldi/Desktop/PolygonProjectImages/octogon.png");
                	intAng = 135;
                }
                else if(intAng < 140) {
                	player.image = null;
                	player.setImage("//Users/stellaaldi/Desktop/PolygonProjectImages/nonagon.png");
                	intAng = 140;
                }
                else if(intAng < 144) {
                	player.image = null;
                	player.setImage("//Users/stellaaldi/Desktop/PolygonProjectImages/decagon.png");
                	intAng = 144;
                }
                else {
                	player.image = null;
                	player.setImage("//Users/stellaaldi/Desktop/PolygonProjectImages/circle.png");
                }
          
                player.changeAngle(intAng);
                
                */

            }
        };
        //beginning gameLoop
        gameLoop.start();
        
        

        //Starts Showing The GUI
        stage.show();
    }






}



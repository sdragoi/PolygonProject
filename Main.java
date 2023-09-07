// Main Class
// Date: May 23, 2022

package com.example.polyproj;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Main extends Application {
  public static void main(String[] args) {
      launch();
  }
  // Method moves the shapes down the screen and appropriately tests collision
  public void dropShapes(Player player, ArrayList<Shape> shapeRow) {

      for (int i = 0; i < shapeRow.size(); i++) {
          Shape shape = shapeRow.get(i);
	      
          if (player.overlaps(shape)) {
              if (shape.interiorAngle == 60) {
                  player.interiorAngle += shape.interiorAngle / 3;
                  System.out.println(player.interiorAngle);
              }
		  
              if (shape.interiorAngle == 90) {
                  player.interiorAngle -= shape.interiorAngle / 3;
                  System.out.println(player.interiorAngle);
              }
		  
              if (shape.interiorAngle == 108) {
                  player.interiorAngle += shape.interiorAngle / 3;
                  System.out.println(player.interiorAngle);
              }
		  
              if (shape.interiorAngle == 120) {
                  player.interiorAngle -= shape.interiorAngle / 3;
                  System.out.println(player.interiorAngle);
              }
		  
              if (shape.interiorAngle == 128.57) {
                  player.interiorAngle += shape.interiorAngle / 3;
                  System.out.println(player.interiorAngle);
              }
		  
              if (shape.interiorAngle == 135) {
                  player.interiorAngle -= shape.interiorAngle / 3;
                  System.out.println(player.interiorAngle);
              }
		  
              if (shape.interiorAngle == 140) {
                  player.interiorAngle += 0;
                  System.out.println(player.interiorAngle);
              }
		  
              if (shape.interiorAngle == 144) {
                  player.interiorAngle -= shape.interiorAngle / 3;
                  System.out.println(player.interiorAngle);
              }
		  
              shapeRow.clear();
          }
      }
	  
      // Sets speed for falling objects
      for (int i = 0; i < shapeRow.size(); i++) {
          shapeRow.get(i).position.add(0,2);
      }
  }
	
  // Method creates an ArrayList with five shapes in it
  public ArrayList<Shape> shapeCreation () {
      ArrayList<Shape> shapeRow = new ArrayList<Shape>();
      int shapeCounter = 5;
      for (int i = 0; i < shapeCounter; i++) {
          Shape shape = new Shape((int)Math.ceil(Math.random() * 8), 60*(i) + (i * 50));
          shape.position.set(50);
          shapeRow.add(shape);
      }
      return shapeRow;
  }

  @Override
  public void start(Stage stage) throws Exception {

      // Sets up window for GUI
      BorderPane root = new BorderPane();
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.setResizable(false);

      //Initializes game icon and title
      Image icon = new Image("file:\\C:\\Users\\julma\\IdeaProjects\\demo\\PolyProj\\src\\circle.png");
      stage.getIcons().add(icon);
      stage.setTitle("Polygon Game");

      // Creates a canvas for the game to run on
      Canvas canvas = new Canvas(545,600);
      GraphicsContext context = canvas.getGraphicsContext2D();

      // Fills the page with the color white
      root.setCenter(canvas);
      context.setFill(Color.WHITE);
      context.fillRect(0,0,600,600);

      // Creates the player
      Player player = new Player();
      player.position.set(250,500);
      player.setImage("C:\\Users\\julma\\IdeaProjects\\demo\\PolyProj\\src\\tri.jpg");
      player.render(context);

      // Creates an ArrayList to store and read the inputs of the player when the game starts
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

      // Creates a random ArrayList of shapes falling from the sky
      ArrayList<Shape> shapeRow = shapeCreation();

      // Controls the player's speed and the falling of the shapes
      AnimationTimer gameLoop = new AnimationTimer() {
          public void handle(long nanoTime) {
              // Declares player speed
              double speedOfPlayer = 500;
		  
              // What happens if the player moves left:
              if (inputList.contains("LEFT")){
                  player.velocity.add(-speedOfPlayer,0);
              }
		  
              // What happens if the player moves right:
              if (inputList.contains("RIGHT")){
                  player.velocity.add(speedOfPlayer,0);
              }
              // Sets the speed for the player per input press
              player.velocity.scalarMult(1/60.0);
              player.position.add(player.velocity);


              // Clears canvas
              context.setFill(Color.WHITE);
              context.fillRect(0, 0, 600, 600);

              // Initiates falling shapes
              dropShapes(player, shapeRow);
              for (Shape shape : shapeRow) {
                      shape.render(context);
              }
		  
              // Rendering in the player
              player.render(context);

          }
      };
	  
      // Beginning gameLoop
      gameLoop.start();

      // Starts showing the GUI
      stage.show();
  }
}

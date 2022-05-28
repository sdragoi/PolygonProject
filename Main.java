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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;



import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.*;

public class Main extends Application {
	
	
	public static void main(String[] args) {
		launch();
 
	}
	
	AnimationTimer timer;
	Pane root = new Pane();
	List<ShapeClass> drop = new ArrayList();
	double mouseX;
	Player cont;
	double speed;
	double falling;
	Label lblMissed;
	int missed;
	double intAng = 60;
 
	
 
	@Override
	public void start(Stage primaryStage) throws Exception {
 
		lblMissed = new Label("Missed: 0");
		lblMissed.setLayoutX(10);
		lblMissed.setLayoutY(10);
		missed = 0;
 
		speed = 0.5;
		falling = 500;
		
		Canvas canvas = new Canvas(545,600);
        GraphicsContext context = canvas.getGraphicsContext2D();
 
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(falling), event -> {
 
			speed += falling / 3000;
			drop.add(shape());
			root.getChildren().add(((Node)drop.get(drop.size() -1)));
        }));
 
		timeline.setCycleCount(1000);
		timeline.play();
 
 
 
		timer = new AnimationTimer() {
 
			@Override
			public void handle(long arg0) {
				cont.setLayoutX(mouseX);
				cont.setLayoutY(480);
				
				 
				 
				for(int i = 0; i < drop.size(); i++) {
					drop.get(i).render(context);
					((ShapeClass) drop.get(i)).setLayoutY(((ShapeClass) drop.get(i)).getLayoutY() + speed + ((ShapeClass) drop.get(i)).getLayoutY() / 150 );
					//if get dropped into square
					if((((ShapeClass) drop.get(i)).getLayoutX() > cont.getLayoutX() && ((ShapeClass) drop.get(i)).getLayoutX() < cont.getLayoutX() + 70) &&
							((ShapeClass) drop.get(i)).getLayoutY() >= 550	) {					
						if (drop.get(i).interiorAngle == 60) {
		                    cont.interiorAngle += drop.get(i).interiorAngle / 3;
		                    intAng = cont.interiorAngle;
		                }
		                if (drop.get(i).interiorAngle == 90) {
		                    cont.interiorAngle -= drop.get(i).interiorAngle / 3;
		                    intAng = cont.interiorAngle;
		                }
		                if (drop.get(i).interiorAngle == 108) {
		                    cont.interiorAngle += drop.get(i).interiorAngle / 3;
		                    intAng = cont.interiorAngle;
		                }
		                if (drop.get(i).interiorAngle == 120) {
		                    cont.interiorAngle -= drop.get(i).interiorAngle / 3;
		                    intAng = cont.interiorAngle;
		                }
		                if (drop.get(i).interiorAngle == 128.57) {
		                    cont.interiorAngle += drop.get(i).interiorAngle / 3;
		                    intAng = cont.interiorAngle;
		                }
		                if (drop.get(i).interiorAngle == 135) {
		                    cont.interiorAngle -= drop.get(i).interiorAngle / 3;
		                    intAng = cont.interiorAngle;
		                }
		                if (drop.get(i).interiorAngle == 140) {
		                    cont.interiorAngle += 0;
		                    intAng = cont.interiorAngle;
		                }
		                if (drop.get(i).interiorAngle == 144) {
		                    cont.interiorAngle -= drop.get(i).interiorAngle / 3;
		                    intAng = cont.interiorAngle;
		                }
		                //System.out.println("Interior angle= " + intAng + " . It was changed by" + drop.get(i).interiorAngle);
		                cont.changeAngle(intAng);
		                //intAng = cont.getnewAngle(intAng);
		                //cont.changeImage(intAng);
						root.getChildren().remove(((ShapeClass) drop.get(i)));
						drop.remove(i);
					}
		 
					//if missed remove
					else if(((ShapeClass) drop.get(i)).getLayoutY() >= 590) {
						root.getChildren().remove(((ShapeClass) drop.get(i)));
						drop.remove(i);
						missed += 1;
						lblMissed.setText("Missed: " + String.valueOf(missed));
					}
				}
 
			}
 
		};
		timer.start();	
 
		cont = new Player(intAng);

		
		//cont.render(context);
		
		
		
 
		root.getChildren().addAll(cont, lblMissed);
 
		Scene scene = new Scene(root, 700, 600);
 
		scene.setOnMouseMoved(e -> {
			mouseX = e.getX();
		});
		
 
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
 
	}
 
	public ShapeClass shape() {
		ShapeClass shape = new ShapeClass(1);
		shape.setLayoutX(rand(0, 400));
		shape.setLayoutY(1);
		shape.autosize();
		//shape.render(context);
		//shape.shapeType
		return shape;
	}
 
	public Rectangle rectangle() {
		Rectangle rectangle = new Rectangle();
		rectangle.setLayoutX(200);
		rectangle.setLayoutY(550);
		rectangle.setHeight(50);
		rectangle.setWidth(70);
		rectangle.setFill(Color.GREEN);
		return rectangle;
 
	}
	
	
 
	public int rand(int min, int max) {
		return (int)(Math.random() * max + min);
	}
	
	public void gameUpdate(GraphicsContext context){
 
		
	}
 
}
	
	/*double speed;
	double falling;
	AnimationTimer timer;
	List drop = new ArrayList();
	
    public static void main(String[] args) {
        launch();
        new GameFrame();
    }
    

    //method that moves the shapes down the screen and appropriately tests collision
    public double dropShapeClasss(Player player, ArrayList<ShapeClass> shapeRow, GraphicsContext context) {

        double intAng = 60;
        for (int i = 0; i < shapeRow.size(); i++) {
            ShapeClass shape = shapeRow.get(i);
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
    public ArrayList<ShapeClass> shapeCreation () {
        ArrayList<ShapeClass> shapeRow = new ArrayList<ShapeClass>();
        int shapeCounter = 5;
        for (int i = 0; i < shapeCounter; i++) {
            ShapeClass shape = new ShapeClass((int)Math.ceil(Math.random() * 8), ((60 * (i)) + (i * 50)- 75), 0);
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
        
        
        /*
        speed = 1;
        falling = 500;
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(falling), event -> {
        	speed += falling / 3000;
        	drop.add(new ShapeClass());
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

        //Creating a random ArrayList of ShapeClasss falling from the sky
        ArrayList<ShapeClass> shapeRow = shapeCreation();
        ArrayList<ShapeClass> shapeRow1 = shapeCreation();
        ArrayList<ShapeClass> shapeRow2 = shapeCreation();
        ArrayList<ShapeClass> shapeRow3 = shapeCreation(); 

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
                double intAng = dropShapeClasss(player, shapeRow, context);
                for (ShapeClass shape : shapeRow) {
                        shape.render(context);
                }
                
                
             
                
              //rendering in the player
                player.render(context);
                
                if (intAng == 60) {
                	
                	player.setImage("//Users/stellaaldi/Desktop/PolygonProjectImages/tri.png");
                }
                if(intAng < 60) {
                	
                	player.position.set(250,300);
                    player.setImage("//Users/stellaaldi/Desktop/PolygonProjectImages/gameover.jpg");
                }
                else if(intAng < 90) {
                	
                	player.setImage("//Users/stellaaldi/Desktop/PolygonProjectImages/square.png");
                	intAng = 90;
                }
                else if(intAng < 108) {
                	
                	player.setImage("//Users/stellaaldi/Desktop/PolygonProjectImages/pentagon.png");
                	intAng = 108;
                }
                else if(intAng < 120) {
                	
                	player.setImage("//Users/stellaaldi/Desktop/PolygonProjectImages/hexagon.png");
                	intAng = 120;
                }
                else if(intAng < 128.57) {
                	
                	player.setImage("//Users/stellaaldi/Desktop/PolygonProjectImages/heptagon.png");
                	intAng = 128.57;
                }
                else if(intAng < 135) {
                	
                	player.setImage("//Users/stellaaldi/Desktop/PolygonProjectImages/octogon.png");
                	intAng = 135;
                }
                else if(intAng < 140) {
                	
                	player.setImage("//Users/stellaaldi/Desktop/PolygonProjectImages/nonagon.png");
                	intAng = 140;
                }
                else if(intAng < 144) {
                	
                	player.setImage("//Users/stellaaldi/Desktop/PolygonProjectImages/decagon.png");
                	intAng = 144;
                }
                else {
                	
                	player.setImage("//Users/stellaaldi/Desktop/PolygonProjectImages/circle.png");
                }
          
                player.changeAngle(intAng);
                
                

            }
        };
        //beginning gameLoop
        gameLoop.start();
        
        

        //Starts Showing The GUI
        stage.show();
    }




*/





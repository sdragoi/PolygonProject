//Main Runtime Class
//5/23/2022

package application;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
	Label lblHit;
	Label lblIAM;
	Label gameOver;
	Label startMessage;
	Label title;
	int hit;
	int missed;
	int iam;
	double intAng = 60;
 
	
 
	@Override
	public void start(Stage primaryStage) throws Exception {
 
		lblHit = new Label("Polygons Eaten: 0");
		lblHit.setLayoutX(10);
		lblHit.setLayoutY(30);
		 hit = 0;
		
		lblMissed = new Label("Missed: 0");
		lblMissed.setLayoutX(10);
		lblMissed.setLayoutY(10);
		missed = 0;
		
		lblIAM = new Label("Interior Angle Measure: 60");
		lblIAM.setLayoutX(10);
		lblIAM.setLayoutY(50);
		iam = 60;
		
		gameOver = new Label("");
		gameOver.setLayoutX(90);
		gameOver.setLayoutY(230);
		gameOver.setFont(Font.font("Chalkduster", FontWeight.BOLD, FontPosture.REGULAR, 75));
		
		startMessage = new Label("");
		startMessage.setLayoutX(50);
		startMessage.setLayoutY(230);
		startMessage.setFont(Font.font("Chalkduster", FontWeight.BOLD, FontPosture.REGULAR, 15));
		
		
		
		
 
		speed = 0.25;
		falling = 500;
		
		Canvas canvas = new Canvas(545,600);
        GraphicsContext context = canvas.getGraphicsContext2D();
 
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(falling), event -> {
 
			//speed += falling / 3000;
			drop.add(shape());
			root.getChildren().add(((Node)drop.get(drop.size() -1)));
        }));
		
		root.getChildren().addAll(startMessage);
		startMessage.setText("Welcome to the Polygon Game! In this game, you will need to \n"
				+ "eat polygons in order to increase your interior angle measure (IAM) and \n"
				+ "eventually become a circle! Avoid the even-sided polygons, as they \n"
				+ "will decrease your IAM, but try to eat the prime-sided ones-- \n"
				+ "they will help you become a circle! Good luck! "); 
		
 
		timeline.setCycleCount(1000);
		timeline.play();
 
		
 
		timer = new AnimationTimer() {
 
			@Override
			public void handle(long arg0) {
				cont.setLayoutX(mouseX);
				cont.setLayoutY(480);
				
				Properties prop = new Properties();
		    	try {
		    		FileInputStream input = new FileInputStream("resources/config.properties");
		    		prop.load(input);
		    	} catch (FileNotFoundException e) {
		    		// TODO Auto-generated catch block
		    		e.printStackTrace();
		    	} catch (IOException e) {
		    		// TODO Auto-generated catch block
		    		e.printStackTrace();
		    	}
				 
				for(int i = 0; i < drop.size(); i++) {
					//drop.get(i).render(context);
					((ShapeClass) drop.get(i)).setLayoutY(((ShapeClass) drop.get(i)).getLayoutY() + speed + ((ShapeClass) drop.get(i)).getLayoutY() / 150 );
					//if get dropped into square
					if((((ShapeClass) drop.get(i)).getLayoutX() > cont.getLayoutX() && ((ShapeClass) drop.get(i)).getLayoutX() < cont.getLayoutX() + 70) &&
							((ShapeClass) drop.get(i)).getLayoutY() >= 480	) {					
						if (drop.get(i).interiorAngle == 60) {
		                    cont.interiorAngle += drop.get(i).interiorAngle / 3;
		                    intAng = cont.interiorAngle;
		                }
		                if (drop.get(i).interiorAngle == 90) {
		                    cont.interiorAngle -= drop.get(i).interiorAngle / 5;
		                    intAng = cont.interiorAngle;
		                }
		                if (drop.get(i).interiorAngle == 108) {
		                    cont.interiorAngle += drop.get(i).interiorAngle / 3;
		                    intAng = cont.interiorAngle;
		                }
		                if (drop.get(i).interiorAngle == 120) {
		                    cont.interiorAngle -= drop.get(i).interiorAngle / 5;
		                    intAng = cont.interiorAngle;
		                }
		                if (drop.get(i).interiorAngle == 128.57) {
		                    cont.interiorAngle += drop.get(i).interiorAngle / 3;
		                    intAng = cont.interiorAngle;
		                }
		                if (drop.get(i).interiorAngle == 135) {
		                    cont.interiorAngle -= drop.get(i).interiorAngle / 5;
		                    intAng = cont.interiorAngle;
		                }
		                if (drop.get(i).interiorAngle == 140) {
		                    cont.interiorAngle += 0;
		                    intAng = cont.interiorAngle;
		                }
		                if (drop.get(i).interiorAngle == 144) {
		                    cont.interiorAngle -= drop.get(i).interiorAngle / 5;
		                    intAng = cont.interiorAngle;
		                }
		                //System.out.println("Interior angle= " + intAng + " . It was changed by" + drop.get(i).interiorAngle);
		                intAng = cont.changeAngle(intAng);
		                intAng = cont.getnewAngle(intAng);
		                cont.setImage(cont.changeImage(intAng));
		                hit += 1;
		                root.getChildren().remove(startMessage);
						lblHit.setText("Polygons Eaten: " + String.valueOf(hit));
						lblIAM.setText("Interior Angle Measure: " + intAng);
						root.getChildren().remove(((ShapeClass) drop.get(i)));
						drop.remove(i);
						
						if(intAng == 0){
							timer.stop();
							timeline.stop();
							root.getChildren().removeAll(drop);
							root.getChildren().addAll(gameOver);
							gameOver.setText("GAME OVER");
							
						}
						else if(intAng > 180) {
							timer.stop();
							timeline.stop();
							root.getChildren().removeAll(drop);
							root.getChildren().addAll(gameOver);
							gameOver.setText("CIRCLE STATUS REACHED!");
							
						}
					}
		 
					//if missed remove
					else if(((ShapeClass) drop.get(i)).getLayoutY() >= 590) {
						root.getChildren().remove(startMessage);
						root.getChildren().remove(((ShapeClass) drop.get(i)));
						drop.remove(i);
						missed += 1;
						lblMissed.setText("Missed: " + String.valueOf(missed));
						
						
					}
				}
 
			}
 
			
		};
		timer.start();	
 
		cont = new Player(60);

		
		//cont.render(context);
		
		
		
 
		root.getChildren().addAll(cont, lblMissed, lblHit, lblIAM);
 
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

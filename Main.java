ackage application;
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
				
				 
				 
				for(int i = 0; i < drop.size(); i++) {
					drop.get(i).render(context);
					((ShapeClass) drop.get(i)).setLayoutY(((ShapeClass) drop.get(i)).getLayoutY() + speed + ((ShapeClass) drop.get(i)).getLayoutY() / 150 );
					//if get dropped into square
					if((((ShapeClass) drop.get(i)).getLayoutX() > cont.getLayoutX() && ((ShapeClass) drop.get(i)).getLayoutX() < cont.getLayoutX() + 70) &&
							((ShapeClass) drop.get(i)).getLayoutY() >= 550	) {
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
 
		cont = new Player(60);
		//cont.render(context);
		
		
		
 
		root.getChildren().addAll(cont, lblMissed);
 
		Scene scene = new Scene(root, 400, 600);
 
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

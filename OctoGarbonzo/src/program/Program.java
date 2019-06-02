package program;

import javafx.application.Application;

import javafx.event.EventHandler;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;

import terrain.Grid;
import terrain.GridReader;

public class Program extends Application{
	
	private static final double PANE_WIDTH = 1024; // Width of the window
	private static final double PANE_HEIGHT = 720; // Height of the window
	private static double tileSize = 50;
	
	private static Grid g;// = new Grid(new Dimension2D(5, 5), tileSize); // Create a hexagonal Grid
	
	private static Canvas c; // Create the Canvas
	
	Timer t = new Timer(); // Create a new Timer to count track the frames

	public static void main(String[] args){
		launch(args);
	}
	
	/* Start the Program */
	public void start(Stage stage) throws Exception {
		
		g = GridReader.readGrid("res/lvl1.txt");
		
		g.center(0, 0, PANE_WIDTH, PANE_HEIGHT);
		
		c = new Canvas(PANE_WIDTH, PANE_HEIGHT);
		
		Pane root = new Pane();
		root.getChildren().add(c);
		root.setStyle("-fx-background-color: #303030");
		
		c.setOnScroll(new EventHandler<ScrollEvent>(){
			@Override
			public void handle(ScrollEvent event) {
				double t = event.getDeltaY() / 10;
				if(tileSize > 0){
					if(tileSize - t > 0){
						tileSize = tileSize - t;
					}
					else{
						tileSize = 0.1;
					}
				}
				
				g.setTileSize(tileSize);
			}
		});
		c.setOnKeyPressed(new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.UP){
					System.out.println("p");
					g.shift(0, 1000);
				}
			}
			
		});
		
		
		Scene scene = new Scene(root, c.getWidth(), c.getHeight());
		stage.setScene(scene);
		
		stage.show();
		
		t.addKeyFrame(ae -> {tick();});
		t.start();
		
	}
	
	/* The actions to do each frame, such as updating the screen */
	private static void tick(){ 
		g.tick();
		draw(c.getGraphicsContext2D());
	}
	
	/* Updates the screen by redrawing all objects */
	public static void draw(GraphicsContext gc){ 
		gc.clearRect(0, 0, PANE_WIDTH, PANE_HEIGHT); // Clear the window
		g.draw(gc); // Draw the Grid
	}

}

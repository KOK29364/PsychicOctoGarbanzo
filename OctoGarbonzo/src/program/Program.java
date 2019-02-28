package program;

import javafx.application.Application;

import javafx.event.EventHandler;

import javafx.geometry.Dimension2D;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;
import terrain.Grid;

public class Program extends Application{
	
	private static final double PANE_WIDTH = 1024; // Width of the window
	private static final double PANE_HEIGHT = 720; // Height of the window
	private static double tileSize = 50;
	
	private static Grid g = new Grid(new Dimension2D(4, 4), tileSize); // Create a hexagonal Grid
	
	private static Canvas c; // Create the Canvas
	
	Timer t = new Timer(); // Create a new Timer to count track the frames

	public static void main(String[] args){
		launch(args);
	}
	
	/* Start the Program */
	public void start(Stage stage) throws Exception {
		
//		stage.setFullScreen(true);
//		stage.setMaximized(true);
		
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
		gc.clearRect(0, 0, PANE_WIDTH, PANE_HEIGHT);
		g.draw(gc);
	}

}

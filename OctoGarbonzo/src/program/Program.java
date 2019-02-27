package program;

import javafx.application.Application;

import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;
import terrain.Grid;

public class Program extends Application{
	
	private static final double PANE_WIDTH = 1024; // Width of the window
	private static final double PANE_HEIGHT = 720; // Height of the window
	
	private static Grid g = new Grid(new Dimension2D(5, 5), 50); // Create a hexagonal Grid
	
	private static Canvas c; // Create the Canvas
	
	Timer t = new Timer(); // Create a new Timer to count track the frames

	public static void main(String[] args){
		launch(args); // Launch the Program
	}
	
	/* Start the Program */
	public void start(Stage stage) throws Exception {
		
//		stage.setFullScreen(true);
//		stage.setMaximized(true);
		
		System.out.println("y");
		
		g.center(0, 0, PANE_WIDTH, PANE_HEIGHT);
		
		c = new Canvas(PANE_WIDTH, PANE_HEIGHT);
		
		Pane root = new Pane();
		root.getChildren().add(c);
		root.setStyle("-fx-background-color: #303030");
		
		Scene scene = new Scene(root, c.getWidth(), c.getHeight());
		stage.setScene(scene);
		
		stage.show();
		
		t.addKeyFrame(ae -> {tick();});
		t.start();
		
	}
	/* _ _ _ _ _ _ _ _ */
	
	/* The actions to do each frame, such as updating the screen */
	private static void tick(){ 
		g.tick();
		draw(c.getGraphicsContext2D());
	}
	/* _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ */
	
	/* Updates the screen by redrawing all objects */
	public static void draw(GraphicsContext gc){ 
		g.draw(gc);
	}
	/* _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ */

}

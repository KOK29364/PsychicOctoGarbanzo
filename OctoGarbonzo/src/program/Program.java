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
	
	private final double PANE_WIDTH = 1024;
	private final double PANE_HEIGHT = 720;
	
	Grid g = new Grid(new Dimension2D(7, 7), 50);

	public static void main(String[] args){
		launch(args);
	}
	
	public void start(Stage stage) throws Exception {
		
//		stage.setFullScreen(true);
//		stage.setMaximized(true);
		
//		this.g.setTileOrientation(Tile.FLAT);
		
		Canvas c = new Canvas(this.PANE_WIDTH, this.PANE_HEIGHT);
		
		Pane root = new Pane();
		root.getChildren().add(c);
		root.setStyle("-fx-background-color: #303030");
		
		Scene scene = new Scene(root, c.getWidth(), c.getHeight());
		stage.setScene(scene);
		
		stage.show();
		
		this.draw(c.getGraphicsContext2D());
		
	}
	
	public void draw(GraphicsContext gc){
		g.draw(gc);
	}

}

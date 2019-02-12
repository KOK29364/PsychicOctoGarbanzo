package terrain;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Tile{

	public static final boolean FLAT = false;
	public static final boolean POINTY = true;
	
	
	private Point2D coord;
	private double size;
	
	public Tile(Point2D coord, double size){
		
		this.coord = coord;
		this.size = size;
		
	}
	
	public void draw(GraphicsContext gc, boolean orientation){
		
		double[][] doublePoints = new double[2][6];
		
		double add = (orientation)? 0:Math.PI/2;
		
		for(int i = 0; i < 6; i ++){
			doublePoints[0][i] = this.coord.getX() + (this.size * Math.cos(Math.PI / 180 * (60 * i - 30) + add));
			doublePoints[1][i] = this.coord.getY() + (this.size * Math.sin(Math.PI / 180 * (60 * i - 30) + add));
		}
		
		gc.setStroke(Color.ALICEBLUE);
		gc.strokePolygon(doublePoints[0], doublePoints[1], 6);
		
	}

	public Point2D getCoordinates() {
		return this.coord;
	}
	
}

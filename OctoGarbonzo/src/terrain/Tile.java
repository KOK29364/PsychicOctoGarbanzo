package terrain;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Tile{

	public static final boolean FLAT = false;
	public static final boolean POINTY = true; // Booleans to represent the two orientations a hexagonal Tile can have: flat or pointy top
	
	
	private Point2D location; // The coordinates of the Tile
	private double size; // The size of one Tile, represented as the distance from its center to any corner
	private boolean orientation; // The orientation of the Tile, flat or pointy top
	
	/* Initialize the Tile with a given location, size and orientation */
	public Tile(Point2D location, double size, boolean orientation){ 
		
		this.location = location;
		this.size = size;
		this.orientation = orientation;
		
	}
	/* _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ */
	
	/* Draw the hexagonal Tile using formulas for regular hexagons */
	public void draw(GraphicsContext gc){ 
		
		double[][] doublePoints = new double[2][6];
		
		double add = (this.orientation)? 0:Math.PI/2; 
		
		for(int i = 0; i < 6; i ++){
			doublePoints[0][i] = this.location.getX() + (this.size * Math.cos(Math.PI / 180 * (60 * i - 30) + add));
			doublePoints[1][i] = this.location.getY() + (this.size * Math.sin(Math.PI / 180 * (60 * i - 30) + add));
		}
		
		gc.setStroke(new Color(0.188235, 0.188235, 0.188235, 1));
		gc.setFill(new Color(0.75, 0.75, 0.75, 0.5));
		gc.fillPolygon(doublePoints[0], doublePoints[1], 6);
		gc.strokePolygon(doublePoints[0], doublePoints[1], 6); 
		
	}
	/* _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ */
	
	/* Returns the orientation of the Tile */
	public boolean getOrientation(){ 
		return this.orientation;
	}
	/* _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ */
	
	/* Returns the rectangular dimensions of the Tile */
	public Dimension2D getDimensions(){ 
		
		return new Dimension2D((this.orientation)? Math.sqrt(3) * this.size:2 * this.size, (this.orientation)? 2 * this.size:Math.sqrt(3) * this.size);
		
	}
	/* _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ */
	
	/* Returns the size of the Tile, represented as the distance from its center to any corner */
	public double getSize(){ 
		return this.size;
	}
	/* _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ */
	
	/* Returns the coordinates of the Tile */
	public Point2D getLocation() { 
		return this.location;
	}
	/* _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ */
	
	/* Changes the orientation of the Tile to the one given */
	public void changeOrientation(boolean orientation){ 
		this.orientation = orientation;
	}
	/* _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ */
	
	/* Shifts the coordinates of the Tile by the provided values */
	public void shift(double x, double y){ 
		this.location = new Point2D(this.location.getX() + x, this.location.getY() + y);
	}
	/* _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ */
	
	/* Returns a String representation of the Tile */
	public String toString(){
		return "Tile [x location: " + this.location.getX() + ", y location: " + this.location.getY() + ", size: " + this.size + "]";
	}
	/* _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _*/
	
}

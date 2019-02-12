package terrain;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class Grid {

	private Point2D location;
	private Tile[][] tiles;
	private double tileSize;
	private Dimension2D tileDimensions;
	private boolean tileOrientation;
	
	public Grid(Dimension2D dimensions, double tileSize){
		
		this.tileOrientation = Tile.POINTY;
		
		this.tileSize = tileSize;
		this.tiles = new Tile[(int) dimensions.getWidth()][(int) dimensions.getHeight()];
		
		double w = (this.tileOrientation)? Math.sqrt(3) * this.tileSize:2 * this.tileSize;
		double h = (this.tileOrientation)? 2 * this.tileSize:Math.sqrt(3) * this.tileSize;
		
		this.tileDimensions = new Dimension2D(w, h);
		
		this.location = new Point2D(w, h);
		
		for(int x = 0; x < tiles.length; x ++){
			for(int y = 0; y < tiles[x].length; y ++){
					tiles[x][y] = new Tile(new Point2D((w + (x * ((this.tileOrientation == Tile.POINTY)? 2:1.5) * w) + ((this.tileOrientation == Tile.POINTY)? (w * (y % 2)):0)) / 2, (h + (y * ((this.tileOrientation  == Tile.POINTY)? 1.5:2) * h) + ((this.tileOrientation == Tile.POINTY)? 0:h * (x % 2))) / 2), this.tileSize);
			}
		}
		
	}
	
	public Grid(Point2D location, Dimension2D dimensions, double tileSize){
		
		this.tileOrientation = Tile.POINTY;
		
		this.tileSize = tileSize;
		this.tiles = new Tile[(int) dimensions.getWidth()][(int) dimensions.getHeight()];
		
		double w = (this.tileOrientation)? Math.sqrt(3) * this.tileSize:2 * this.tileSize;
		double h = (this.tileOrientation)? 2 * this.tileSize:Math.sqrt(3) * this.tileSize;
		
		this.tileDimensions = new Dimension2D(w, h);
		
		this.location = new Point2D(w + location.getX(), h + location.getY());
		
		for(int x = 0; x < tiles.length; x ++){
			for(int y = 0; y < tiles[x].length; y ++){
				tiles[x][y] = new Tile(new Point2D((w + (x * ((this.tileOrientation)? 2:1.5) * w) + ((this.tileOrientation)? (w * (y % 2)):0)) / 2, (h + (y * ((this.tileOrientation)? 1.5:2) * h) + ((this.tileOrientation)? 0:h * (x % 2))) / 2), this.tileSize);

			}
		}
		
	}
	
	public String toString(){
		return "Grid [width = " + this.getSize().getWidth() + ", height = " +  this.getSize().getWidth() + ", tile orientation = " + ((this.tileOrientation == Tile.POINTY)? "Pointy":"Flat") + "]";
	}
	
	public void setTileOrientation(boolean tileOrientation){
		this.tileOrientation = tileOrientation;
	}
	
	public double getTileSize(){
		return this.tileSize;
	}
	
	public Dimension2D getTileDimensions(){
		return this.tileDimensions;
	}
	
	public Point2D getLocation(){
		return this.location;
	}
	
	public Dimension2D getSize(){
		double w = this.tileDimensions.getWidth();
		double h = this.tileDimensions.getHeight();
		return new Dimension2D((this.tiles.length * w) + ((this.tileOrientation)? w:0), (this.tiles[0].length * h) + ((this.tileOrientation)? 0:h));
	}
	
	public void draw(GraphicsContext gc){
		for(int x = 0; x < this.tiles.length; x ++){
			for(int y = 0; y < this.tiles[x].length; y ++){
				if(this.tiles[x][y] != null)
					this.tiles[x][y].draw(gc, this.tileOrientation);
			}
		}
	}
	
}

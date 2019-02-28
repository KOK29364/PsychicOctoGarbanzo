package terrain;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

import javafx.scene.canvas.GraphicsContext;

public class Grid {

	private Point2D location; // The coordinates of the top left corner of the Grid on a window
	private Tile[][] tiles; // The array representation of the Tiles forming the Grid
	private Rectangle2D centeredIn;
	
	/* Initialize a new Grid with a specified number of Tiles and size for the Tiles */
	public Grid(Dimension2D dimensions, double tileSize){
	
		this.tiles = new Tile[(int) dimensions.getWidth()][(int) dimensions.getHeight()];
		
		double w = Math.sqrt(3) * tileSize;
		double h = 2 * tileSize;
		
		this.location = new Point2D(0, 0);
		
		for(int x = 0; x < tiles.length; x ++){
			for(int y = 0; y < tiles[x].length; y ++){
					tiles[x][y] = new Tile(new Point2D((w + (x * 2 * w) + ((w * (y % 2)))) / 2, (h + (y * (1.5) * h)) / 2), tileSize, Tile.POINTY);
			}
		}
		
	}
	
	/* Returns a String representation of the Grid */
	public String toString(){ 
		return "Grid [width = " + this.getSize().getWidth() + ", height = " +  this.getSize().getWidth() + "]";
	}
	
	/* Change the orientation of the Tiles to the one given */
	public void setTileOrientation(boolean tileOrientation){ 
		
		double tileSize = tiles[0][0].getSize();
		
		double w = (tileOrientation)? Math.sqrt(3) * tileSize:2 * tileSize;
		double h = (tileOrientation)? 2 * tileSize:Math.sqrt(3) * tileSize;
		
		for(int x = 0; x < tiles.length; x ++){
			for(int y = 0; y < tiles[x].length; y ++){
				tiles[x][y].changeOrientation(tileOrientation);
				
				tiles[x][y].shift(((w + (x * ((tileOrientation)? 2:1.5) * w) + ((tileOrientation)? (w * (y % 2)):0)) / 2) - tiles[x][y].getLocation().getX(),
									(h + (y * ((tileOrientation)? 1.5:2) * h) + ((tileOrientation)? 0:h * (x % 2))) / 2 - tiles[x][y].getLocation().getY());
			}
		}
		
	}
	
	/* Set the size of all the Tiles in the Grid */
	public void setTileSize(double tileSize){
		double w = (tiles[0][0].getOrientation())? Math.sqrt(3) * tileSize:2 * tileSize;
		double h = (tiles[0][0].getOrientation())? 2 * tileSize:Math.sqrt(3) * tileSize;
		
		for(int x = 0; x < tiles.length; x ++){
			for(int y = 0; y < tiles[x].length; y ++){
				tiles[x][y].setSize(tileSize);
				tiles[x][y].setLocation(new Point2D(((w + (x * 2 * w) + ((w * (y % 2)))) / 2), ((h + (y * (1.5) * h)) / 2)));
			}
		}
	}
	
	/* Returns the current location of the Grid */
	public Point2D getLocation(){ 
		return this.location;
	}
	
	/* Returns the width and height of the entire Grid */
	public Dimension2D getSize(){ 
		
		double w = this.tiles[0][0].getDimensions().getWidth();
		double h = this.tiles[0][0].getDimensions().getHeight();
		
		return new Dimension2D((this.tiles.length * w) + ((this.tiles[0][0].getOrientation())? w / 2:0),
							(this.tiles[0].length * h) + ((this.tiles[0][0].getOrientation())? 0:h / 2));
	
	}
	
	/* Moves the Grid to the defined Point */
	public void setLocation(Point2D loc){ 
		
		double deltaX = loc.getX();
		double deltaY = loc.getY();
		
		this.location = loc; 
		
		for(int x = 0; x < this.tiles.length; x ++){
			for(int y = 0; y < this.tiles[x].length; y ++){
				this.tiles[x][y].shift(deltaX, deltaY);
			}
		}
		
	}
	
	/* Moves the whole Grid the the center of the rectangle defined	*/
	public void center(double x, double y, double width, double height){
		this.centeredIn = new Rectangle2D(x, y, width, height);
		this.setLocation(new Point2D((width - this.getSize().getWidth()) / 2 + x, (height - this.getSize().getHeight()) / 2 + y));	
	}

	/* Draws all the Tiles the Grid contains */
	public void draw(GraphicsContext gc){ 
		
		for(int x = 0; x < this.tiles.length; x ++){
			for(int y = 0; y < this.tiles[x].length; y ++){
				if(this.tiles[x][y] != null) 
					this.tiles[x][y].draw(gc);
			}
		}	
	}
	
	/* Checks if the Grid is still centered inside the predefined centeredIn Rectangle */
	public boolean isCentered(){	
		if(this.centeredIn != null){
			return !((this.centeredIn.getWidth() - this.getSize().getWidth()) / 2 == this.location.getX() && (this.centeredIn.getHeight() - this.getSize().getHeight()) / 2 == this.location.getY());
		}
		return false;
	}
	
	public void tick(){
		if(this.centeredIn != null && this.isCentered()){
			this.center(this.centeredIn.getMinX(), this.centeredIn.getMinY(), this.centeredIn.getMaxX(), this.centeredIn.getMaxY());
		}
		System.out.println(this.isCentered());
		System.out.println(this.getSize());
	}
	
}

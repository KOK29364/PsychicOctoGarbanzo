package terrain;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.shape.MeshView;

public class Grid {

	private Point3D location; // The coordinates of the top left corner of the Grid on a window
	private Group tiles; // The array representation of the Tiles forming the Grid
	// private Rectangle2D centeredIn;
	
	/* Initialize a new Grid with a specified number of Tiles and size for the Tiles */
	public Grid(int row, int column, float tileSize){
		
		double w = Math.sqrt(3) * tileSize;
		double h = 2 * tileSize;
		
		this.location = new Point3D(0, 0, 0);
		
		MeshView stMesh = new MeshView(Tile.createHexagonMesh(tileSize, 3));
		
		this.tiles = new Group();
		
		for(int x = 0; x < row; x ++){
			for(int y = 0; y < column; y ++){
					Tile toAdd = new Tile(new Point3D((w + (x * 2 * w) + ((w * (y % 2)))) / 2, 0, (h + (y * (1.5) * h)) / 2), tileSize, 3);
					this.tiles.getChildren().add(toAdd.getMesh());
			}
		}
		
	}
	
	public Group getTiles(){
		return this.tiles;
	}

	/* Initialize a new Grid with the specified Tiles 
	public Grid(boolean[][] tiles){
		this.tiles = new Tile[tiles[0].length][tiles.length];
		this.location = new Point2D(0, 0);
		
		System.out.println(tiles.length + " " + tiles[0].length);
		
		double w = Math.sqrt(3) * 50;
		double h = 2 * 50;
		
		for(int y = 0; y < tiles.length; y ++){
			System.out.println();
			for(int x = 0; x < tiles[y].length; x ++){
				if(tiles[y][x]){
					this.tiles[x][y] = new Tile(new Point2D((w + (x * 2 * w) + ((w * (y % 2)))) / 2, (h + (y * (1.5) * h)) / 2), 50, Tile.POINTY);
				}
				else
					this.tiles[x][y] = null;
			}
		}
	}
	
	/* Returns a String representation of the Grid 
	public String toString(){ 
		return "Grid [width = " + this.getSize().getWidth() + ", height = " +  this.getSize().getWidth() + "]";
	}
	
	/* Returns the current location of the Grid */
	public Point3D getLocation(){ 
		return this.location;
	}
	
	/* Returns the width and height of the entire Grid 
	public Dimension2D getSize(){ 

		int x = 0, y = 0;
		while(this.tiles[x][y] == null){
			if(x == this.tiles[0].length){
				x = 0;
				y ++;
			}
			else{
				x ++;
			}		
		}
		
		double w = this.tiles[x][y].getDimensions().getWidth();
		double h = this.tiles[x][y].getDimensions().getHeight();
	
		return new Dimension2D((this.tiles.length * w) + ((this.tiles[x][y].getOrientation())? w / 2:0),
							(this.tiles[0].length / 2.0 * 1.75 * h) + ((this.tiles[x][y].getOrientation())? -(tiles[0].length - 2) * h / 8:h / 2));
	
	}
	
	/* Returns the width and height of the entire Grid 
	public Dimension2D getSize(double tileWidth, double tileHeight, boolean tileOrientation){ 
		
		double w = tileWidth;
		double h = tileHeight;
		
		return new Dimension2D((this.tiles.length * w) + ((tileOrientation)? w / 2:0),
							(this.tiles[0].length / 2.0 * 1.75 * h) + ((tileOrientation)? -(tiles[0].length - 2) * h / 8:h / 2));
	
	}
	
	/* Moves the Grid to the defined Point 
	public void setLocation(Point2D loc){ 
		
		double deltaX = loc.getX();
		double deltaY = loc.getY();
		
		this.location = loc; 
		
		for(int x = 0; x < this.tiles.length; x ++){
			for(int y = 0; y < this.tiles[x].length; y ++){
				if(this.tiles[x][y] != null)
					this.tiles[x][y].shift(deltaX, deltaY);
			}
		}
		
	}
	
	/* Changes the location of the Grid by the specified amount 
	public void shift(double deltaX, double deltaY){
		
		this.setLocation(new Point2D(this.getLocation().getX() + deltaX, this.getLocation().getY() + deltaY));
		this.centeredIn = null;
		
	}
	
	/* Moves the whole Grid the the center of the rectangle defined	
	public void center(double x, double y, double width, double height){
		this.centeredIn = new Rectangle2D(x, y, width, height);
		this.setLocation(new Point2D((width - this.getSize().getWidth()) / 2 + x, (height - this.getSize().getHeight()) / 2 + y));	
	}

	/* Draws all the Tiles the Grid contains 
	public void draw(GraphicsContext gc){ 
		
		for(int x = 0; x < this.tiles.length; x ++){
			for(int y = 0; y < this.tiles[x].length; y ++){
				if(this.tiles[x][y] != null) 
					this.tiles[x][y].draw(gc);
			}
		}	
	}
	
	/* Checks if the Grid is still centered inside the predefined centeredIn Rectangle 
	public boolean isCentered(){	
		if(this.centeredIn != null){
			return !((this.centeredIn.getWidth() - this.getSize().getWidth()) / 2 == this.location.getX() && (this.centeredIn.getHeight() - this.getSize().getHeight()) / 2 == this.location.getY());
		}
		return false;
	}
	
	/* Actions to be performed each frame 
	public void tick(){
		if(this.centeredIn != null && this.isCentered()){
			this.center(this.centeredIn.getMinX(), this.centeredIn.getMinY(), this.centeredIn.getMaxX(), this.centeredIn.getMaxY());
		}
	}
	
	/* */
}

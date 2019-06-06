package terrain;


import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

public class Tile {

	public static final boolean FLAT = false;
	public static final boolean POINTY = true; // Booleans to represent the two orientations a hexagonal Tile can have: flat or pointy top
	
	
	private Point3D location; // The coordinates of the Tile
	private float size; // The size of one Tile, represented as the distance from its center to any corner
	private float height; // The height of the Tile, from its base to top
	private MeshView mesh; // The 3D mesh of the Tile
	
	/* Initialize the Tile with a given location, size and height */
	public Tile(Point3D location, float size, float height){ 
		
		this.location = location;
		this.size = size;
		this.height = height;
		
		this.mesh = new MeshView(Tile.createHexagonMesh(this.size, this.height));
		this.mesh.setMaterial(new PhongMaterial(Color.BLACK));
		this.mesh.setTranslateX(this.location.getX());
		this.mesh.setTranslateY(this.location.getY());
		this.mesh.setTranslateZ(this.location.getZ());
		
	}
	
	/* Initialize the Tile with a given location, size, height and mesh */
	public Tile(Point3D location, float size, float height, MeshView mesh){
		
		this.location = location;
		this.size = size;
		this.height = height;
		
		this.mesh = mesh;
		this.mesh.setTranslateX(this.location.getX());
		this.mesh.setTranslateY(this.location.getY());
		this.mesh.setTranslateZ(this.location.getZ());
		
	}

	/* Initialize a Tile with no values set */
	public Tile(){
		
	}
	
	/* Create a hexagon-shaped TriangleMesh */
	public static TriangleMesh createHexagonMesh(float size, float height){
		TriangleMesh ret = new TriangleMesh();
		ret.getPoints().addAll(
	 			size * (float) Math.cos(0),						0,			size * (float) Math.sin(0),
	 			size * (float) Math.cos(Math.PI / 3),			0, 			size * (float) Math.sin(Math.PI / 3),
	 			size * (float) Math.cos(Math.PI / 3 * 2),		0, 			size * (float) Math.sin(Math.PI / 3 * 2),
	 			size * (float) Math.cos(Math.PI),				0, 			size * (float) Math.sin(Math.PI),
	 			size * (float) Math.cos(Math.PI / 3 * 4),		0, 			size * (float) Math.sin(Math.PI / 3 * 4),
	 			size * (float) Math.cos(Math.PI / 3 * 5),		0,			size * (float) Math.sin(Math.PI / 3 * 5),
	 			size * (float) Math.cos(0), 					height, 	size * (float) Math.sin(0),
	 			size * (float) Math.cos(Math.PI / 3),			height, 	size * (float) Math.sin(Math.PI / 3),
	 			size * (float) Math.cos(Math.PI / 3 * 2),		height, 	size * (float) Math.sin(Math.PI / 3 * 2),
	 			size * (float) Math.cos(Math.PI), 				height, 	size * (float) Math.sin(Math.PI),
	 			size * (float) Math.cos(Math.PI / 3 * 4),		height, 	size * (float) Math.sin(Math.PI / 3 * 4),
	 			size * (float) Math.cos(Math.PI / 3 * 5), 		height, 	size * (float) Math.sin(Math.PI / 3 * 5)
	 	);
		ret.getFaces().addAll(
	 			// Top Faces
	 			4,0,	2,0,	3,0,
	 			4,0,	1,0,	2,0,
	 			5,0,	1,0,	4,0,
	 			5,0,	0,0,	1,0,
	 			// Side 1
	 			4,0,	9,0,	10,0,
	 			3,0,	9,0,	4,0,
	 			// Side 2
	 			3,0,	8,0,	9,0,
	 			2,0,	8,0,	3,0,
	 			// Side 3
	 			2,0,	7,0,	8,0,
	 			1,0,	7,0,	2,0,
	 			// Side 4
	 			1,0,	6,0,	7,0,
	 			0,0,	6,0,	1,0,
	 			// Side 5
	 			0,0,	11,0,	6,0,
	 			5,0,	11,0,	0,0,
	 			// Side 6
	 			5,0,	10,0,	11,0,
	 			4,0,	10,0,	5,0,
	 			// Bottom Faces
	 			10,0,	9,0,	8,0,
	 			10,0,	8,0,	7,0,
	 			11,0,	10,0,	7,0,
	 			11,0,	7,0,	6,0
	 	);
		
		return ret;
	}
	
	public MeshView getMesh(){
		return this.mesh;
	}
	
	/* Returns the size of the Tile, represented as the distance from its center to any corner */
	public double getSize(){ 
		return this.size;
	}
	
	/* Changes the size of the Tile to the one provided */
	public void setSize(float size){
		this.size = size;
	}
	
	/* Returns the coordinates of the Tile */
	public Point3D getLocation() { 
		return this.location;
	}
	
	/* Sets the coordinates of the Tile to the one provided */
	public void setLocation(Point3D location){
		this.location = location;
	}
	
	/* Shifts the coordinates of the Tile by the provided values */
	public void shift(double x, double y, double z){ 
		this.location = new Point3D(this.location.getX() + x, this.location.getY() + y, this.location.getZ() + z);
	}
	
	/* Returns a String representation of the Tile */
	public String toString(){
		return "Tile [x location: " + this.location.getX() + ", y location: " + this.location.getY() + ", z location: " + this.location.getZ() + ", size: " + this.size + "]";
	}
	
}

package program;

import javafx.application.Application;

import javafx.beans.property.SimpleDoubleProperty;

import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;

import javafx.scene.paint.Color;

import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import javafx.stage.Stage;

import terrain.Grid;

public class Program extends Application{
	
	// private static double tileSize = 50;
	
	private static Grid g;// = new Grid(new Dimension2D(5, 5), tileSize); // Create a hexagonal Grid
	
	Timer t = new Timer(); // Create a new Timer to count track the frames

	public static void main(String[] args){
		launch(args);
	}
	
	/* Start the Program */
	public void start(Stage stage) throws Exception {
		
		g = new Grid(10, 10, 1, 1.15);
		//Tile tile = new Tile(new Point3D(0, 0, 0), 3, 3);
		
		SimpleDoubleProperty angleX = new SimpleDoubleProperty();
		SimpleDoubleProperty angleY = new SimpleDoubleProperty();
		
		PerspectiveCamera camera = new PerspectiveCamera(true);
		
		Translate pivot = new Translate();
        Rotate yRotate = new Rotate(0, Rotate.Y_AXIS);
        Rotate xRotate = new Rotate(0, Rotate.X_AXIS);
        yRotate.angleProperty().bind(angleX);
        xRotate.angleProperty().bind(angleY);
        
        camera.getTransforms().addAll(
        		pivot,
        		yRotate,
        		xRotate,
                new Translate(0, 0, -50)
        );

        PointLight bl = new PointLight();
        bl.setLightOn(true);
        bl.setTranslateY(-50);
        
		Group root = new Group();

		root.getChildren().addAll(g.getTiles().getChildren());
		root.getChildren().stream()
        	.filter(node -> !(node instanceof Camera))
        	.forEach(node ->
                node.setOnMouseClicked(event -> {
                	pivot.setX(node.getTranslateX());
                    pivot.setY(node.getTranslateY());
                    pivot.setZ(node.getTranslateZ());
                })
        );

		root.getChildren().add(bl);
		root.getChildren().add(camera);
		
		pivot.setX(root.getChildren().get(0).getTranslateX());
		pivot.setY(root.getChildren().get(0).getTranslateY());
		pivot.setZ(root.getChildren().get(0).getTranslateZ());
		
		SubScene subScene = new SubScene(
				root,
				1024, 720,
				true,
				SceneAntialiasing.BALANCED
		);
		subScene.setCamera(camera);
		subScene.setFill(Color.BLACK);
		subScene.setOnScroll(event -> {
            angleX.set(angleX.doubleValue() + (event.getDeltaX() / 10));
            angleY.set(angleY.doubleValue() + (event.getDeltaY() / 10));
            if(angleY.doubleValue() < -45){
            	 angleY.set(-45);
            }
            if(angleY.doubleValue() > -15){
           	 angleY.set(-15);
           }
        });
		Group group = new Group();
        group.getChildren().add(subScene);
		
		Scene scene = new Scene(group, 1024, 720);
		stage.setScene(scene);
		
		stage.show();
		
		t.addKeyFrame(ae -> {tick();});
		t.start();
		
	}
	
	/* The actions to do each frame, such as updating the screen */
	private static void tick(){ 
		//g.tick();
	}

}

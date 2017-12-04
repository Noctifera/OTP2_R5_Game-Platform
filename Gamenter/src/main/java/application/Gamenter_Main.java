package application;

import java.io.File;

import controller_gamenter.Controller;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Gamenter_Main extends Application {

	Button pacman = new Button();

	Button createMap = new Button();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		File file = new File(getClass().getResource("/Pictures/pacman-g-game-maker-8-logo.png").getFile());
		primaryStage.getIcons().add(new Image(file.toURI().toString()));
		primaryStage.setTitle("GAMENTER");
		GridPane gp = new GridPane();
		
		
		File f = new File(getClass().getResource("/Pictures/pacMan-poster.jpg").getFile());
		Image image = new Image(f.toURI().toString());
		ImageView iv = new ImageView(image);
		iv.setPreserveRatio(true);
		iv.maxWidth(10);
		
		gp.add(iv, 0, 0);
		
		
		
		
		pacman.setText("Play");
		
		pacman.setMaxWidth(image.getWidth());
		pacman.setMinWidth(image.getWidth());
		pacman.setMinHeight(30);
		pacman.setMaxHeight(30);
		

		gp.add(pacman, 0, 1);
		
		
		
		createMap.setText("Create Map");
		createMap.setMaxWidth(image.getWidth());
		createMap.setMinWidth(image.getWidth());
		createMap.setMinHeight(30);
		createMap.setMaxHeight(30);

		gp.add(createMap, 0, 2);

		Scene scene = new Scene(gp);
		primaryStage.setScene(scene);
		primaryStage.show();

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});

		Controller controller = new Controller();

		controller.handle(pacman, createMap);
	}

}

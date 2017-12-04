package application;

import application_CreateMap.Main;
import application_PacMan.PacMan_gui;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Gamenter_Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Stage game = new Stage();
		Stage creator = new Stage();
		
		GridPane gp = new GridPane();
		Button button1 = new Button("Pac Man");
		
		button1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				PacMan_gui pg = new PacMan_gui();
				pg.init();
				pg.start(game);
	}
		});

		gp.add(button1, 0, 0);
		Button button2 = new Button("Map Creator");

		button2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main main = new Main();
				main.init();
				main.start(creator);
			}
		});

		gp.add(button2, 1, 0);

		Scene scene = new Scene(gp, 100, 100);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
	}

}

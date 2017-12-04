package controller_gamenter;

import application_CreateMap.Main;
import application_PacMan.PacMan_gui;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {

	
	
	
	
	
	
	
	
	
	public void handle(Button pacman,Button createMap) {
		Stage game = new Stage();
		Stage creator = new Stage();
		
		pacman.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				PacMan_gui pg = new PacMan_gui();
				pg.init();
				pg.start(game);
			}
		});
		
		createMap.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main main = new Main();
				main.init();
				main.start(creator);
			}
		});
		
	}
}

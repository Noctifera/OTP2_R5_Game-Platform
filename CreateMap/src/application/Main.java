package application;

import controller.Controller;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import modal.Draw;
import modal.Map;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	Controller con;
	Draw draw;
	Map map;

	private Scene scene;

	private final int x = 1280;
	private final int y = 720;
	private final int tileSize = 40;
	private String event;
	private String[] strings = {"Dot", "LargeDot", "Wall", "Empty" };

	public void init(){

		draw = new Draw(x,y,tileSize);
		con = new Controller(draw);
	}
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			root.getChildren().add(draw);

			Scene scene = new Scene(root,x,y);
			primaryStage.setScene(scene);
			primaryStage.show();


			handle();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void handle(){
		draw.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e){
				con.draw(e, event);
			}

		});

	}
	public static void main(String[] args) {
		launch(args);
	}
}

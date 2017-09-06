package application;

import java.io.File;

import controller.Controller;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import modal.Draw;
import modal.Map;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main extends Application {
	Controller con;
	Draw draw;
	Map map;

	Button save;
	Button getMap;
	TextField name;

	private final int x = 1280;
	private final int y = 720;
	private final int tileSize = 40;
	private String event;
	private String[] strings = { "Dot", "LargeDot", "Wall", "Empty", "PlayerSpawn", "GhostSpawn" };

	public void init() {
		map = new Map(x, y, tileSize, strings);
		draw = new Draw(x, y, tileSize);
		con = new Controller(draw, map, strings);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			root.getChildren().add(draw);

			Scene scene = new Scene(root, x + 260, y + 50);
			primaryStage.setScene(scene);
			primaryStage.show();
			root.setRight(rightVerticalBox());
			root.setBottom(bottomHorizontalBox());
			handle();
			con.beginning();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handle() {
		draw.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				con.draw(e, event);
			}

		});
		save.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				con.saveMap(name.getText());
			}
		});

	}

	public VBox rightVerticalBox(){
		GridPane gd = new GridPane();
		ChoiceBox<String> cb = new ChoiceBox<String>();
		cb.setItems(FXCollections.observableArrayList(strings));

		cb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> o, Object o1, Object o2) {
				for(int i = 0; i<strings.length; i++){
					if(o2.equals(strings[i])){
						event = strings[i];
					}
				}
			}});
		cb.setValue(strings[0]);
		gd.add(cb,0,0);

		ListView<String> files = new ListView<>();
		files.setItems(FXCollections.observableArrayList(con.readFiles()));
		files.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				con.getMap(newValue);
				System.out.println(newValue);
			}

		});

		gd.add(files,0,1);



		VBox vb = new VBox(gd);

		return vb;

	}

	public HBox bottomHorizontalBox() {
		GridPane gd = new GridPane();
		save = new Button("Save Map");
		gd.add(save, 0, 0);

		name = new TextField();
		gd.add(name, 1, 0);

		getMap = new Button("Get Map");
		gd.add(getMap, 0, 1);

		HBox hb = new HBox(gd);
		return hb;
	}

	public static void main(String[] args) {
		launch(args);
	}
}

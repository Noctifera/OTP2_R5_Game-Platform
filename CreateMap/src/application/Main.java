package application;


import controller.Controller;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import modal.Draw;
import modal.Map;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Main extends Application {
	Controller con;
	Draw draw;
	Map map;

	private final int x = 1280;
	private final int y = 720;
	private final int tileSize = 40;
	private String event;
	private String[] strings = { "Dot", "LargeDot", "Wall", "Empty" };

	public void init() {
		map = new Map();
		draw = new Draw(x, y, tileSize);
		con = new Controller(draw,map,strings);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			root.getChildren().add(draw);

			Scene scene = new Scene(root, x + 100, y);
			primaryStage.setScene(scene);
			primaryStage.show();
			root.setRight(rightVerticalBox());
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

	}
	public VBox rightVerticalBox(){
		GridPane gd = new GridPane();
		ChoiceBox<String> cb = new ChoiceBox<String>();
		cb.setItems(FXCollections.observableArrayList(strings[0],strings[1],strings[2],strings[3]));

		cb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> o, Object o1, Object o2) {
				if(o2.equals(strings[0])){
					event = strings[0];
				}else if(o2.equals(strings[1])){
					event = strings[1];
				}else if(o2.equals(strings[2])){
					event = strings[2];
				}else if(o2.equals(strings[3])){
					event = strings[3];
				}
			}});
		cb.setValue(strings[0]);
		gd.getChildren().add(cb);
		VBox vb = new VBox(gd);

		return vb;

	}
	public static void main(String[] args) {
		launch(args);
	}
}

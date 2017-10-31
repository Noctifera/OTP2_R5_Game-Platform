package application;

import controller.Controller;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import modal.Draw;
import modal.Map;
import modal.Readers;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main extends Application {
	private Controller con;
	private Draw draw;
	private Map map;
	private Readers readers;
	
	private Button save;
	private Button newM;
	private ListView<String> files = new ListView<>();
	private ChoiceBox<String> cb;
	private final int width = 720;
	private final int height = 480;
	private final int tileSize = 40;
	private String[] strings = { "Dot", "LargeDot", "Wall", "Empty", "PlayerSpawn", "GhostHouse" };
	
	public void init() {
		map = new Map(width, height, tileSize, strings);
		readers = new Readers(map);
		draw = new Draw(width, height, tileSize, map, strings);
		con = new Controller(this,readers,draw, map, strings, tileSize);
	}
	public void popUp() {
		Stage stage = new Stage();
		
		GridPane gp = new GridPane();
		gp.setHgap(10);
		gp.setVgap(10);
		
		String s = con.mapContains();
		Label check = new Label();
		check.setText(s);
		gp.add(check, 1, 1,2,1);
		
		 ToggleGroup group = new ToggleGroup();
		    RadioButton button1 = new RadioButton("private");
		    button1.setToggleGroup(group);
		    button1.setSelected(true);
		    RadioButton button2 = new RadioButton("public");
		    button2.setToggleGroup(group);
		    
		  gp.add(button1, 1, 2);
		  gp.add(button2, 2, 2);
		
		
		Label label = new Label();
		label.setText("Map Name: ");
		gp.add(label, 1, 3);
		
		
		TextField tx= new TextField();
		gp.add(tx, 2, 3);
		
		Button button = new Button("Save Map");
		gp.add(button, 2, 4);
				
		if(!s.equals("")) {
			button1.setDisable(true);
			button2.setDisable(true);
			tx.setDisable(true);
			button.setDisable(true);
		}
		Scene scene = new Scene(gp);
		stage.setScene(scene);
		stage.show();
	}
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			GridPane root = new GridPane();
			root.setHgap(10);
			root.setVgap(10);
			
			Scene scene = new Scene(root, width+260, height+20);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.setTitle("MapCreator");
			Image image = new Image("file:maxresdefault.jpg");
			primaryStage.getIcons().add(image);
			
			
			
			con.beginning();
			root.add(draw,1,1);
			root.add(rightVerticalBox(),2,1);
			con.handle(save, newM, cb, files);
			
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					System.exit(0);

				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private VBox rightVerticalBox() {
		GridPane gd = new GridPane();
		cb = new ChoiceBox<String>();
		cb.setItems(FXCollections.observableArrayList(strings));

		cb.setValue(strings[0]);
		gd.add(cb, 0, 0);

		files.setItems(FXCollections.observableArrayList(con.readFiles()));

		gd.add(files, 0, 1,2,1);
		
		newM = new Button("Clear Map");
		
		gd.add(newM, 1, 0);
		
		save = new Button("Map Ready to be Saved");
		gd.add(save, 0, 3);

		VBox vb = new VBox(gd);

		return vb;

	}
	
	

	public static void main(String[] args) {
		launch(args);
	}
}

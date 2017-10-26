package application;

import controller.Controller;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
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
import javafx.scene.control.TextField;
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
	private TextField name;
	private ListView<String> files = new ListView<>();
	private ChoiceBox<String> cb;
	private double w = Screen.getPrimary().getVisualBounds().getWidth();
	private double h = Screen.getPrimary().getVisualBounds().getHeight();
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
	public void popUp(String message) {
		Stage popup = new Stage();
		GridPane gp = new GridPane();
		
		
		System.out.println(message);
		System.out.println(message.length());
	
		Scene scene = new Scene(gp,300,100);
		System.out.println();
		
		if(message.length()*8 > scene.getWidth()) {
			
			GridPane grp = new GridPane();
			grp.setMinWidth(message.length());
			String m = message.substring(0, message.length()/2);
			String m1 = message.substring(message.length()/2,message.length());
			Label l1 = new Label(m);
			Label l2 = new Label(m1);
			
			grp.add(l1, 0, 0);
			grp.add(l2, 0, 1);
			gp.getChildren().add(grp);
			
			grp.setTranslateX((scene.getWidth()/2)-(grp.getMinWidth()/2));
			grp.setTranslateY((scene.getHeight()/2)-(grp.getMinHeight()/2));
			
			
			
		}else {
			Label label1 = new Label(message);
			gp.getChildren().add(label1);
			label1.setMinWidth(message.length());
			label1.setMinHeight(15);
			label1.setTranslateX((scene.getWidth()/2)-(label1.getMinWidth()/2));
			label1.setTranslateY((scene.getHeight()/2)-(label1.getMinHeight()/2));
		}
		
		
		popup.setScene(scene);
		popup.show();
		popup.setResizable(false);
	}
	@Override
	public void start(Stage primaryStage) {
		// System.out.println(w);
		// System.out.println(h);
		try {
			BorderPane root = new BorderPane();
			root.getChildren().add(draw);
			draw.setTranslateX((w / 2) - (width / 2));
			draw.setTranslateY((h / 2) - (height / 2));
			Scene scene = new Scene(root, w, h);
			primaryStage.setScene(scene);
			primaryStage.show();
			con.beginning();
			root.setRight(rightVerticalBox());
			root.setBottom(bottomHorizontalBox());
			con.handle(name, save, newM, cb, files);
			
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

	public VBox rightVerticalBox() {
		GridPane gd = new GridPane();
		cb = new ChoiceBox<String>();
		cb.setItems(FXCollections.observableArrayList(strings));

		cb.setValue(strings[0]);
		gd.add(cb, 0, 0);

		files.setItems(FXCollections.observableArrayList(con.readFiles()));

		gd.add(files, 0, 1);
		
		newM = new Button("Clear Map");
		
		gd.add(newM, 0, 2);

		VBox vb = new VBox(gd);

		return vb;

	}

	public HBox bottomHorizontalBox() {
		GridPane gd = new GridPane();
		save = new Button("Save Map");
		gd.add(save, 0, 0);

		name = new TextField();
		gd.add(name, 1, 0);

		HBox hb = new HBox(gd);
		return hb;
	}
	
	

	public static void main(String[] args) {
		launch(args);
	}
}

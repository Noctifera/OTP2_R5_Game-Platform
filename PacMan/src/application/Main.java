 package application;

import java.awt.Point;

import controller.*;
import modal.*;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.input.KeyEvent;

public class Main extends Application implements Main_IF {

	private Controller con;
	private Draw draw;
	private Map map;
	private DrawThread pms;
	private Player player;
	private MovementLogic ml;

	ListView<String> files = new ListView<>();
	private final int blSize = 40;
	private final Point gSize = new Point(720, 480);
	private BorderPane root;
	private String[] strings = {"Dot", "LargeDot", "Wall", "Empty","PlayerSpawn", "GhostHouse"  };
	private int ghostAmount = 4;
	private int life = 3;
	private Ghost[] ghlist = new Ghost[ghostAmount];
	private GhostThread[] ghtlist = new GhostThread[ghostAmount];

	private Scene scene;

	public void init() {
		map = new Map(strings);
		ml = new  MovementLogic(gSize, blSize,map,strings);
		player = new Player(ml,blSize,strings,life);
		draw = new Draw((int) gSize.getX(), (int) gSize.getY(), blSize, player,map,ghlist,strings);
		pms = new DrawThread(draw);
		con = new Controller(player,map);

	}

	public void start(Stage primaryStage) {
		root = new BorderPane();

		//root.setRight(rightVerticalBox());
		scene = new Scene(root, (int) gSize.getX(), (int) gSize.getY());

		primaryStage.setScene(scene);
		primaryStage.show();

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				// TODO Auto-generated method stub
				System.exit(0);

			}
		});

		con.start();
		CanvasSetuUp();
		handle();
		inisGhost();
		pms.start();




	}
	public void inisGhost(){
		for(int i = 0; i<ghtlist.length; i++){
			ghlist[i] = new Ghost(ml,gSize,blSize,player);
			ghtlist[i] = new GhostThread(ghlist[i],ml);

		}
		for (GhostThread ghost : ghtlist) {
			ghost.start();
		}
	}

	public void handle() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				// liikkuminen
				con.move(event);
			}
		});
	}
	public void CanvasSetuUp(){
		root.getChildren().add(draw);

	}
	public VBox rightVerticalBox(){
		GridPane gd = new GridPane();

		files.setItems(FXCollections.observableArrayList(con.readFiles()));
		files.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				con.getMap(newValue);
				//System.out.println(newValue);
			}

		});

		gd.add(files,0,1);
		VBox vb = new VBox(gd);

		return vb;
	}
	public static void main(String[] args) {
		launch(args);
	}

}

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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.input.KeyEvent;

public class PacMan_gui extends Application implements PacMan_gui_IF {

	private Controller con;
	private Draw draw;
	private Map map;
	private DrawThread pms;
	private Player player;
	private MovementLogic ml;
	private HighScore hs;

	ListView<String> files = new ListView<>();
	private final int tileSize = 40;
	private final Point gSize = new Point(720, 480);
	private BorderPane root;
	private String[] strings = {"Dot", "LargeDot", "Wall", "Empty","PlayerSpawn", "GhostHouse"  };
	private int ghostAmount = 4;
	private int life = 3;
	private Ghost[] ghlist = new Ghost[ghostAmount];
	private GhostThread[] ghtlist = new GhostThread[ghostAmount];

	private Scene scene;
	TextField lives;

	public void init() {
		hs = new HighScore();
		map = new Map(strings);
		ml = new  MovementLogic(gSize, tileSize,map,strings);
		player = new Player(ml,tileSize,strings,life);
		draw = new Draw((int) gSize.getX(), (int) gSize.getY(), tileSize, player,map,ghlist,strings,ghtlist);
		con = new Controller(player,map,hs,this);
		pms = new DrawThread(draw,player,con);
		

	}

	public void start(Stage primaryStage) {
		root = new BorderPane();
		
		scene = new Scene(root, (int) gSize.getX(), (int) gSize.getY()+200);

		primaryStage.setScene(scene);
		primaryStage.show();

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				// TODO Auto-generated method stub
				System.exit(0);

			}
		});
		//topHorizonatalBox();
		//rightVerticalBox();
		combine();
		con.start();
		inisGhost();
		handle();
		pms.start();




	}
	public void inisGhost(){
		for(int i = 0; i<ghtlist.length; i++){
			ghlist[i] = new Ghost(ml,gSize,tileSize,player);
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
	public void combine() {
		GridPane gd = new GridPane();
		gd.add(topHorizonatalBox(), 0, 0);
		gd.add(draw, 0, 1);
		root.getChildren().add(gd);
	}
	
	public HBox topHorizonatalBox() {
		GridPane gd = new GridPane();
		
		Text life = new Text();
		life.setText("Lives left: ");
		
		lives = new TextField();
		lives.setMaxWidth(20);
		lives.setEditable(false);
		lives.setMouseTransparent(true);
		lives.setFocusTraversable(false);
		
		gd.add(life,0,0);
		gd.add(lives, 1, 0);
		
		
		
		
		
		HBox hb= new HBox(gd);
		return hb;
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
	public void setScore() {
		
	}
	public void setLives(int livs) {
		lives.setText(""+livs);
	}
	public static void main(String[] args) {
		launch(args);
	}

}

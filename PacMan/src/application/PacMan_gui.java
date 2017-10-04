package application;

import javafx.scene.control.Label;
import java.awt.Point;
import java.util.ArrayList;

import controller.*;
import modal.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class PacMan_gui extends Application implements PacMan_gui_IF {

	private Controller con;
	private Draw draw;
	private Map map;
	private Player player;
	private MovementLogic ml;
	private HighScore hs;
	private Sounds sounds;
	private HighScorePost hsp;
	
	private FileOut fileOut;
	private FileIn fileIn;
	private int ghostAmount = 4;
	private Ghost[] ghlist = new Ghost[ghostAmount];
	private final int tileSize = 40;
	private final Point gSize = new Point(720, 480);
	private BorderPane root;
	private String[] ghosts = {"Blinky","Speedy","Bashful","Pokey"};
	private String[] strings = { "Dot", "LargeDot", "Wall", "Empty", "PlayerSpawn", "GhostHouse" };

	private int life = 3;

	private Scene scene;
	private Text lives;
	private Text scores;

	public void init() {
		sounds = new Sounds();
		hs = new HighScore();
		fileIn = new FileIn();
		fileOut = new FileOut();
		map = new Map(strings, gSize, tileSize);
		ml = new MovementLogic(gSize, tileSize, map);
		player = new Player(ml, hs, life, sounds);
		con = new Controller(player, map, hs, this, sounds);
		draw = new Draw((int) gSize.getX(), (int) gSize.getY(), tileSize, player, ghlist, map);
		hsp = new HighScorePost(player, hs);
		
		for (int i = 0; i < ghostAmount; i++) {
			ghlist[i] = new Ghost(ml, player, fileOut, fileIn,ghosts[i]);
		}

	}

	public void start(Stage primaryStage) {
		root = new BorderPane();

		scene = new Scene(root, (int) gSize.getX(), (int) gSize.getY() + 200);

		primaryStage.setScene(scene);
		primaryStage.show();

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				System.exit(0);

			}
		});
		con.start(scene,draw,ghlist,hsp);
		combine();
		
		
	}

	public HBox bottomDataPane() {
		con.gethighScore();
		BorderPane bottomPane = new BorderPane();

		GridPane gridCenter = new GridPane();
		gridCenter.setPadding(new Insets(0, 20, 0, 20));
		GridPane gridLeft = new GridPane();
		GridPane gridRight = new GridPane();

		bottomPane.setCenter(gridCenter);
		bottomPane.setLeft(gridLeft);
		bottomPane.setRight(gridRight);

		listLooper(gridLeft, con.presentScore(), "Score");
		listLooper(gridCenter, con.presentName(), "Name");
		listLooper(gridRight, con.presentDate(), "Date");

		HBox hbox = new HBox(bottomPane);
		return hbox;
	}

	public void listLooper(GridPane grid, ArrayList<String> list, String text) {

		// datarivien title: esim highscore, name, date
		Label title = new Label(text);
		grid.add(title, 0, 0);

		for (int i = 0; i < list.size(); i++) {
			String labelText = list.get(i);
			Label insertedText = new Label(labelText);

			grid.add(insertedText, 0, i + 1);
		}
	}

	public void combine() {
		GridPane gd = new GridPane();
		gd.add(topHorizonatalBox(), 0, 0);
		gd.add(draw, 0, 1);
		root.getChildren().add(gd);
		gd.add(bottomDataPane(), 0, 2);
	}

	public HBox topHorizonatalBox() {
		BorderPane bp = new BorderPane();
		bp.setMinWidth(gSize.getX());
		GridPane left = new GridPane();
		GridPane right = new GridPane();

		Text life = new Text();
		life.setText("Lives left: ");

		lives = new Text();
		con.setLives();
		lives.setMouseTransparent(true);
		lives.setFocusTraversable(false);

		Text score = new Text("Score: ");
		scores = new Text();

		left.add(life, 0, 0);
		left.add(lives, 1, 0);

		right.add(score, 0, 0);
		right.add(scores, 1, 0);
		con.setScore();

		bp.setLeft(left);
		bp.setRight(right);

		HBox hb = new HBox(bp);
		return hb;
	}

	public void setLives(int livs) {
		lives.setText("" + livs);
	}

	public void setScore(int score) {
		scores.setText("" + score);
	}

	public static void main(String[] args) {
		launch(args);
	}

}

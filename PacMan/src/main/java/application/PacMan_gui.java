package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import canvas.CanvasController;
import characters.Ghost;
import characters.Player;
import controller.*;
import hibernate.HighScores;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import map.Map;
import map.MovementLogic;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * Main Class
 * 
 * @author markus
 * @version 1.0
 *
 */
public class PacMan_gui extends Application implements PacMan_gui_IF {

	private Controller con;
	private CanvasController cc;

	/**
	 * the amount of ghosts in the game
	 */
	private int ghostAmount = 4;
	/**
	 * an object list of ghosts;
	 */
	private Ghost[] ghlist = new Ghost[ghostAmount];
	/**
	 * how big one tile is
	 */
	private final int tileSize = 40;
	/**
	 * the size of the game
	 */
	private final Point gSize = new Point(720, 480);
	private BorderPane root;
	/**
	 * name of all the ghosts
	 */
	private String[] ghosts = { "Blinky", "Speedy", "Bashful", "Pokey" };
	/**
	 * Strings found in the map
	 */
	private String[] strings = { "Dot", "LargeDot", "Wall", "Empty", "PlayerSpawn", "GhostHouse" };
	/**
	 * how many lives to start with
	 */
	private int life = 3;

	private Scene scene;
	private Text lives;
	private Text scores;

	public void init() {
		Map map = new Map(strings, gSize, tileSize);
		
		MovementLogic ml = new MovementLogic(gSize, tileSize, map);
		
		Player player = new Player(ml, life);
		
		
		con = new Controller(this,player,map);

		for (int i = 0; i < ghostAmount; i++) {
			ghlist[i] = new Ghost(ml, player, ghosts[i]);
		}
		
		cc = new CanvasController(gSize, tileSize,map,player, ghlist);

	}

	public void start(Stage primaryStage) {
		root = new BorderPane();

		scene = new Scene(root, (int) gSize.getX(), (int) gSize.getY() + 200);

		primaryStage.setScene(scene);
		primaryStage.setTitle("PacMan-Game");
		primaryStage.show();

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				System.exit(0);

			}
		});
		combine();
		con.start(cc);
		
		

	}

	public void bottomDataPane(List<HighScores> list) {
		System.out.println(list);
		BorderPane bottomPane = new BorderPane();
		
		GridPane gridLeft = new GridPane();
		
		GridPane gridCenter = new GridPane();
		gridCenter.setPadding(new Insets(0, 20, 0, 20));
		
		GridPane gridRight = new GridPane();
		
		
		bottomPane.setCenter(gridCenter);
		bottomPane.setLeft(gridLeft);
		bottomPane.setRight(gridRight);

		HBox hbox = new HBox(bottomPane);
	}

	private void listLooper(GridPane grid, ArrayList<String> list, String text) {

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
		
		gd.add(cc, 0, 1);
		root.getChildren().add(gd);
	}

	public HBox topHorizonatalBox() {
		BorderPane bp = new BorderPane();
		bp.setMinWidth(gSize.getX());
		GridPane left = new GridPane();
		GridPane right = new GridPane();

		Text life = new Text();
		life.setText("Lives left: ");

		lives = new Text();
		lives.setMouseTransparent(true);
		lives.setFocusTraversable(false);

		Text score = new Text("Score: ");
		scores = new Text();

		left.add(life, 0, 0);
		left.add(lives, 1, 0);

		right.add(score, 0, 0);
		right.add(scores, 1, 0);

		bp.setLeft(left);
		bp.setRight(right);

		HBox hb = new HBox(bp);
		return hb;
	}

	public Stage popUpGameOver() {

		Stage popup = new Stage();
		GridPane gp = new GridPane();
		Label label1 = new Label("GAME OVER!");
		TextField textfield = new TextField();
		Button button = new Button("Save");
		

		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				String input = textfield.getText();

				if (input.length() > 0) {
					con.setHighScore(input);
					System.exit(0);
				}
			}
		});

		gp.setAlignment(Pos.CENTER);

		gp.add(label1, 0, 0);
		label1.setPadding(new Insets(0, 0, 5, 25));
		gp.add(textfield, 0, 1);

		gp.add(button, 0, 2);

		Scene pop = new Scene(gp, 300, 200);

		popup.setScene(pop);

		return popup;

	}

	public void gameOver() {
		popUpGameOver().show();
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

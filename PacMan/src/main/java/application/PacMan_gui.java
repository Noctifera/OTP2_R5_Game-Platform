package application;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import canvas.DrawThread;
import canvas.Game;
import characters.Ghost;
import characters.Player;
import controller.*;
import hibernate.HighScores;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
	private Map map;
	private Player player;

	private int life = 3;
	/**
	 * the amount of ghosts in the game
	 */
	private int ghostAmount = 4;
	private ChoiceBox<String> lang;
	private final String[] languages = { "English", "Se Sotho sa Sotho", "Afrikaans", "Zulu", "IsiXhosa" };
	private final Locale[] locale = { new Locale("en", "RSA"), new Locale("st", "RSA"), new Locale("af", "RSA"), new Locale("zu", "RSA"), new Locale("xh", "RSA") };
	/**
	 * name of all the ghosts
	 */
	private String[] ghosts = { "Blinky", "Speedy", "Bashful", "Pokey" };
	/**
	 * Strings found in the map [0] = Dot,[1] = LargeDot,[2] = Wall
	 * 
	 */
	private String[] strings = { "Dot", "LargeDot", "Wall", "Empty", "PlayerSpawn", "GhostHouse", "Player", ghosts[0], ghosts[1], ghosts[2], ghosts[3] };

	private ArrayList<Game> tileList;
	private ArrayList<DrawThread> drawThreadList;

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
	 * how many lives to start with
	 */

	private String Game_Over = "Game_Over!";
	private String save = "Save";
	private Label label1;
	private Button button;
	private Text score;
	private Scene scene;
	private Text lifes;
	private Text livs;
	private Text scores;

	public void init() {
		map = new Map(strings, gSize, tileSize);

		MovementLogic ml = new MovementLogic(gSize, tileSize, map);

		player = new Player(ml, life);

		con = new Controller(this, player, map);

		for (int i = 0; i < ghostAmount; i++) {
			ghlist[i] = new Ghost(ml, player, ghosts[i]);
		}

		tileList = new ArrayList<>();

		for (int y = 0; y < (gSize.y / tileSize); y++) {

			for (int x = 0; x < (gSize.x / tileSize); x++) {

				Game dc = new Game(tileSize, map, new Point(x * tileSize, y * tileSize), strings);
				tileList.add(dc);
			}
		}
		drawThreadList = new ArrayList<>();

		for (Game dc : tileList) {
			DrawThread dt = new DrawThread(dc);
			drawThreadList.add(dt);
		}
		handle();
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
		con.start(drawThreadList, ghlist, player);

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
		gd.add(topHorizonatalBox(), 0, 0, gSize.x / tileSize, 1);

		for (int i = 0; i < tileList.size(); i++) {
			gd.add(tileList.get(i), tileList.get(i).getPoint().x / tileSize, tileList.get(i).getPoint().y / tileSize);
		}

		// locale valinta
		lang = new ChoiceBox<>();
		lang.setItems(FXCollections.observableArrayList(languages));
		lang.setValue(languages[0]);
		gd.add(lang, 0, (gSize.y / tileSize) + 1, 4, 1);

		root.getChildren().add(gd);

		lang.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Object>() {

			@Override
			public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
				// TODO Auto-generated method stub
				if ((int) newValue >= 0) {
					lang((int) newValue);
				}
			}
		});
		lang.setFocusTraversable(false);

	}

	public HBox topHorizonatalBox() {
		BorderPane bp = new BorderPane();
		bp.setMinWidth(gSize.getX());
		GridPane left = new GridPane();
		GridPane right = new GridPane();

		lifes = new Text();
		lifes.setText("Lives left: ");

		livs = new Text();
		livs.setMouseTransparent(true);
		livs.setFocusTraversable(false);

		score = new Text("Score: ");
		scores = new Text();

		left.add(lifes, 0, 0);
		left.add(livs, 1, 0);

		right.add(score, 0, 0);
		right.add(scores, 1, 0);

		bp.setLeft(left);
		bp.setRight(right);

		HBox hb = new HBox(bp);
		return hb;
	}

	public void lang(int currentIndex) {

		Locale current = locale[currentIndex];
		ResourceBundle rb = ResourceBundle.getBundle("Locales/MessagesBundle", current);
		Game_Over = rb.getString("Game_Over");
		save = rb.getString("Save");
		lifes.setText(rb.getString("Lives_Left"));
		score.setText(rb.getString("Score"));

	}

	public Stage popUpGameOver() {

		Stage popup = new Stage();
		GridPane gp = new GridPane();
		label1 = new Label(Game_Over);
		TextField textfield = new TextField();
		button = new Button(save);

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

	public void setLives(String livs1) {
		livs.setText(livs1);
	}

	public void setScore(String score) {
		scores.setText(score);
	}

	public static void main(String[] args) {
		launch(args);
	}

	
	private void handle() {
		tileList.get(0).setFocusTraversable(true);
		tileList.get(0).setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				//System.out.println(event.getCode());
				if(event.getCode().equals(KeyCode.ESCAPE)) {
					System.exit(0);
				}
				player.path(event.getCode());
			}
		});
	}
}

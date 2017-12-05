package application_PacMan;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.awt.Point;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import canvas_PacMan.DrawThread;
import canvas_PacMan.Game;
import characters_PacMan.Ghost;
import characters_PacMan.Player;
import controller_PacMan.*;
import hibernate_PacMan.HighScores;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import map_PacMan.Map;
import map_PacMan.MovementLogic;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
	private String[] strings = { "Dot", "LargeDot", "Wall", "Empty", "PlayerSpawn", "GhostHouse", "Pacman", "vulnerable", ghosts[0], ghosts[1], ghosts[2], ghosts[3] };

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
	private ListView<String> files;
	private RadioButton button1;
	private RadioButton button2;
	private Button button;
	private Button play;
	private Text score;
	private Scene scene;
	private Text lifes;
	private Text livs;
	private Text scores;
	private Text DatabaseScore;
	private Text DatabaseName;
	private Text DatabaseDate;
	private Game game;
	private DrawThread dt;
	private BorderPane bp = new BorderPane();

	public void init() {
		map = new Map(strings, gSize, tileSize);

		MovementLogic ml = new MovementLogic(gSize, tileSize, map);

		player = new Player(ml, life);

		for (int i = 0; i < ghostAmount; i++) {
			ghlist[i] = new Ghost(ml, player, ghosts[i]);
		}
		game = new Game(tileSize, map, gSize, strings);
		dt = new DrawThread(game);

		con = new Controller(this, player, map, ghlist);
	}

	public void start(Stage primaryStage) {
		root = new BorderPane();

		scene = new Scene(root, (int) gSize.getX() + 350, (int) gSize.getY() + 200);

		primaryStage.setScene(scene);
		primaryStage.setTitle("PacMan-Game");
		primaryStage.setResizable(false);
		primaryStage.show();

		con.start(dt);
		combine();
		handle();

	}

	public void combine() {
		BorderPane.setMargin(game, new Insets(10));
		bp.setTop(topHorizonatalBox());
		bp.setCenter(game);
		bp.setRight(rightVerticalBox());

		root.getChildren().add(bp);

	}

	public void bottomDataPane(List<HighScores> list) {
		System.out.println(list);
		
		GridPane gp = new GridPane();
		gp.setVgap(5);
		gp.setHgap(20);
		DatabaseScore = new Text("Score");
		DatabaseName = new Text("Name");
		DatabaseDate = new Text("Date");

		gp.add(DatabaseScore, 1, 0);
		gp.add(DatabaseName, 2, 0);
		gp.add(DatabaseDate, 3, 0);

		for (int i = 0; i < list.size(); i++) {

			Text score = new Text(list.get(i).getScore() + "");
			gp.add(score, 1, i + 1);

			Text name = new Text(list.get(i).getPlayername());
			gp.add(name, 2, i + 1);

			Text date = new Text(list.get(i).getSubmission_date());
			gp.add(date, 3, i + 1);

		}
		HBox hbox = new HBox(gp);
		bp.setBottom(hbox);
	}

	private ToggleGroup createToggleGroup() {
		ToggleGroup group = new ToggleGroup();
		RadioButton button1 = new RadioButton("private (File)");
		button1.setToggleGroup(group);
		button1.setId("private");
		button1.setSelected(true);
		RadioButton button2 = new RadioButton("public (Database)");
		button2.setToggleGroup(group);
		button2.setId("public");
		return group;
	}

	private VBox rightVerticalBox() {
		GridPane gd = new GridPane();

		// gd.setGridLinesVisible(true);
		gd.setHgap(10);
		gd.setVgap(5);

		ToggleGroup tg = createToggleGroup();
		button1 = (RadioButton) tg.getToggles().get(0);
		button2 = (RadioButton) tg.getToggles().get(1);

		button1.setSelected(true);

		HBox topHbox = new HBox(20);
		topHbox.getChildren().addAll(button1, button2);
		gd.add(topHbox, 0, 0);

		files = new ListView<>();

		files.setItems(FXCollections.observableArrayList(con.readFilesPrivate()));

		gd.add(files, 0, 1);
		HBox bottomHbox = new HBox(20);
		play = new Button("Play");

		lang = new ChoiceBox<>();
		lang.setItems(FXCollections.observableArrayList(languages));
		lang.setValue(languages[0]);

		lang.setFocusTraversable(false);
		bottomHbox.getChildren().addAll(play, lang);

		gd.add(bottomHbox, 0, 2);

		VBox vb = new VBox(gd);
		return vb;

	}

	public HBox topHorizonatalBox() {
		BorderPane bp = new BorderPane();
		bp.setMinWidth(gSize.x);
		HBox left = new HBox(5);

		lifes = new Text();
		lifes.setText("Lives left: ");

		livs = new Text();
		livs.setMouseTransparent(true);
		livs.setFocusTraversable(false);

		left.getChildren().add(lifes);
		left.getChildren().add(livs);

		HBox right = new HBox(5);

		score = new Text("Score: ");
		scores = new Text();

		right.getChildren().add(score);
		right.getChildren().add(scores);
		right.setPrefWidth(100);

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
		DatabaseScore.setText(rb.getString("Score"));
		DatabaseName.setText(rb.getString("Name"));
		DatabaseDate.setText(rb.getString("Date"));
		button1.setText(rb.getString("Private"));
		button2.setText(rb.getString("Public"));
		play.setText(rb.getString("Play"));

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
					popup.close();
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
		game.setFocusTraversable(true);
		lang.setFocusTraversable(false);
		play.setFocusTraversable(false);
		files.setFocusTraversable(false);
		button1.setFocusTraversable(false);
		button2.setFocusTraversable(false);

		game.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				player.path(event.getCode());
			}
		});
		button1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				files.setItems(FXCollections.observableArrayList(con.readFilesPrivate()));
			}
		});
		button2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				files.setItems(FXCollections.observableArrayList(con.readFilesDataBase()));
			}
		});
		lang.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Object>() {

			@Override
			public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
				// TODO Auto-generated method stub
				if ((int) newValue >= 0) {
					lang((int) newValue);
				}

			}
		});
		play.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (!con.ThreadActive()) {
					con.startThreads();
				}

			}
		});
		files.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				if (newValue != null) {
					if (con.ThreadActive()) {
						con.ThreadsSuppress();
					}
					System.out.println(newValue);
					if (button1.isSelected()) {
						con.readMapPrivate(newValue);

					} else if (button2.isSelected()) {
						con.readMapPublic(newValue);
					}
				}
			}
		});
	}
}

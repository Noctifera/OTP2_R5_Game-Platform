package canvas;

import ghosts.Ghost;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import map.Map;
import player.Player;

public class ComCanvas extends Canvas {
	private Draw_IF canvas;
	private Player player;
	private Ghost[] gh;
	private Map map;

	private int width;
	private int height;
	private int tileSize;

	public ComCanvas(Player player, Ghost[] gh, Map map, int width, int height, int tileSize) {
		super(width, height);
		this.player = player;
		this.gh = gh;
		this.map = map;
		this.width = width;
		this.height = height;
		this.tileSize = tileSize;
		handle();
	}

	public void update() {
		canvas.update();
	}

	public void menu() {
		canvas = new Menu(width, height, this.getGraphicsContext2D());
	}

	public void game() {
		canvas = new Draw(width, height, tileSize, player, gh, map, this.getGraphicsContext2D());
	}

	public void handle() {
		// TODO Auto-generated method stub
		this.setFocusTraversable(true);
		this.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent me) {
				// TODO Auto-generated method stub
				if (canvas instanceof Menu) {
					System.out.println(me.getX());
					System.out.println(me.getY());
				}

			}
		});
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if ((event.getCode() != KeyCode.ESCAPE) && (canvas instanceof Draw)) {
					player.path(event.getCode());
				} else {
					if (canvas instanceof Draw) {
						menu();
					} else if (canvas instanceof Menu) {
						game();
					}
				}
			}
		});
	}

}

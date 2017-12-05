package canvas_PacMan;

import java.awt.Point;
import java.io.File;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import map_PacMan.Map;

/**
 * Class extends Canvas and allows items to be drawn onto it
 * 
 * @author markus
 * @version 1.0
 *
 */
public class Game extends Canvas implements Draw_IF {

	private Map map;

	private String[] strings;
	private GraphicsContext gc;
	private Point gSize;

	private int tileSize;

	private Image blinky;
	private Image bashful;
	private Image speedy;
	private Image pokey;
	private Image player_Left;
	private Image player_Up;
	private Image player_Down;
	private Image player_Right;
	private Image vulnerable;

	public Game(int tileSize, Map map, Point gSize, String[] strings) {
		super(gSize.x, gSize.y);
		this.tileSize = tileSize;
		this.gSize = gSize;
		this.gc = this.getGraphicsContext2D();
		this.map = map;
		this.strings = strings;
		blinky = getImage("/Pictures/Pacman-red-blinky.png");
		speedy = getImage("/Pictures/Pacman-pink-speedy.png");
		bashful = getImage("/Pictures/Pacman-inky-bashful.png");
		pokey = getImage("/Pictures/Pacman-orange-pokey.png");
		player_Left = getImage("/Pictures/Pacman-pacman-player_Left.png");
		player_Right = getImage("/Pictures/Pacman-pacman-player_Right.png");
		player_Up = getImage("/Pictures/Pacman-pacman-player_Up.png");
		player_Down = getImage("/Pictures/Pacman-pacman-player_Down.png");
		vulnerable = getImage("/Pictures/Pacman-blue-vulnerable.png");
	}

	private Image getImage(String s) {
		File f = new File(getClass().getResource(s).getFile());
		Image image = new Image(f.toURI().toString());
		return image;
	}

	/**
	 * One function that combines all drawn
	 * 
	 * @return
	 */
	public void update() {
		clear();
		draw();
	}

	/**
	 * Gets Players position and draws an orange circle
	 */

	private void drawplayer(String item, Point point) {
		if(item.contains(KeyCode.LEFT.toString())) {
			gc.drawImage(player_Left, point.x, point.y, tileSize, tileSize);
		}else if(item.contains(KeyCode.RIGHT.toString())){
			gc.drawImage(player_Right, point.x, point.y, tileSize, tileSize);
		}else if(item.contains(KeyCode.UP.toString())) {
			gc.drawImage(player_Up, point.x, point.y, tileSize, tileSize);
		}else if(item.contains(KeyCode.DOWN.toString())) {
			gc.drawImage(player_Down, point.x, point.y, tileSize, tileSize);
		}
		

	}

	/**
	 * draws a black square the size of the canvas
	 */

	private void clear() {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

	/**
	 * Draws a wall to the given point
	 * 
	 * @param point
	 *            The Point where The wall is Drawn
	 */
	private void drawWall(Point point) {
		gc.setFill(Color.BLUE);
		gc.fillRect(point.x, point.y, tileSize - 2, tileSize - 2);
	}

	private void drawDot(Point point) {
		gc.setFill(Color.WHITE);
		int block = tileSize / 4;
		gc.fillOval(point.x + block + block / 2, point.y + block + block / 2, block, block);
	}

	private void drawLargeDot(Point point) {
		gc.setFill(Color.WHITE);
		int block = tileSize / 2;
		gc.fillOval(point.x + block / 2, point.y + block / 2, block, block);
	}

	private void drawPlayerSpawn(Point point) {
		gc.setFill(Color.GREEN);
		gc.fillRect(point.x, point.y, tileSize - 2, tileSize - 2);
	}

	private void drawGhostHouse(Point point) {
		gc.setFill(Color.RED);
		gc.fillRect(point.x, point.y, tileSize - 2, tileSize - 2);
	}

	private void drawGhost(Image image, Point point) {
		gc.drawImage(image, point.x, point.y, tileSize, tileSize);
	}

	private void draw() {
		for (int y = 0; y < gSize.y;) {
			for (int x = 0; x < gSize.x;) {
				Point point = new Point(x, y);

				if (map.getDots().contains(point)) {

					drawDot(point);

				}
				if (map.getLargeDots().contains(point)) {

					drawLargeDot(point);

				}

				x = x + tileSize;
			}
			y = y + tileSize;
		}
		for (int y = 0; y < gSize.y;) {
			for (int x = 0; x < gSize.x;) {
				Point point = new Point(x, y);
				String item = map.getMap().get(point);
				if (item.contains(strings[2])) {

					drawWall(point);

				}
				if (item.contains(strings[4])) {

					drawPlayerSpawn(point);

				}
				if (item.contains(strings[5])) {

					// System.out.println(item);
					drawGhostHouse(point);

				}

				if (item.contains(strings[8])) {

					if (item.contains("true")) {
						drawGhost(vulnerable, point);
					} else if (item.contains("false")) {
						drawGhost(blinky, point);
					}

				}
				if (item.contains(strings[9])) {

					if (item.contains("true")) {
						drawGhost(vulnerable, point);
					} else if (item.contains("false")) {
						drawGhost(speedy, point);
					}

				}
				if (item.contains(strings[10])) {

					if (item.contains("true")) {
						drawGhost(vulnerable, point);
					} else if (item.contains("false")) {
						drawGhost(bashful, point);
					}

				}
				if (item.contains(strings[11])) {

					if (item.contains("true")) {
						drawGhost(vulnerable, point);
					} else if (item.contains("false")) {
						drawGhost(pokey, point);
					}

				}

				if (item.contains(strings[6])) {

					drawplayer(item, point);

				}

				x = x + tileSize;
			}
			y = y + tileSize;
		}

	}
}

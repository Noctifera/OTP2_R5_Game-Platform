package canvas;

import java.awt.Point;
import java.io.File;

import com.google.common.base.Strings;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import map.Map;

/**
 * Class extends Canvas and allows items to be drawn onto it
 * 
 * @author markus
 * @version 1.0
 *
 */
public class Game extends Canvas implements Draw_IF {

	private Map map;
	private Point point;

	private String[] strings;
	private GraphicsContext gc;

	private int tileSize;
	private Image blinky;
	private Image bashful;
	private Image speedy;
	private Image pokey;
	private Image player;

	public Game(int tileSize, Map map, Point point, String[] strings) {
		super(tileSize, tileSize);
		this.tileSize = tileSize;
		this.point = point;
		this.gc = this.getGraphicsContext2D();
		this.map = map;
		this.strings = strings;
		blinky = getImage("/Pictures/Pacman-red-blinky.png");
		speedy = getImage("/Pictures/Pacman-pink-speedy.png");
		bashful = getImage("/Pictures/Pacman-inky-bashful.png");
		pokey = getImage("/Pictures/Pacman-orange-pokey.png");
		player = getImage("/Pictures/Pacman-pacman-player.png");
		
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

	private void drawplayer() {
		gc.drawImage(player, 0, 0,this.getWidth(),this.getHeight());
	}

	/**
	 * draws a black square the size of the canvas
	 */

	private void clear() {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

	public Point getPoint() {
		return point;
	}

	/**
	 * Draws a wall to the given point
	 * 
	 * @param point
	 *            The Point where The wall is Drawn
	 */
	private void drawWall() {
		gc.setFill(Color.BLUE);
		gc.fillRect(1, 1, tileSize - 2, tileSize - 2);
	}

	private void drawDot() {
		gc.setFill(Color.WHITE);
		int block = tileSize / 4;
		gc.fillOval(0 + block + block / 2, 0 + block + block / 2, block, block);
	}

	private void drawLargeDot() {
		gc.setFill(Color.WHITE);
		int block = tileSize / 2;
		gc.fillOval(0 + block / 2, 0 + block / 2, block, block);
	}

	private void drawPlayerSpawn() {
		gc.setFill(Color.GREEN);
		gc.fillRect(1, 1, tileSize - 2, tileSize - 2);
	}

	private void drawGhostHouse() {
		gc.setFill(Color.RED);
		gc.fillRect(1, 1, tileSize - 2, tileSize - 2);
	}

	private void drawGhost(Image image) {
		gc.drawImage(image, 0, 0, this.getWidth(), this.getHeight());
	}

	private void draw() {
		String item = map.getMap().get(point);
		//System.out.println(item);


		if (item.equals(strings[0])) {
			drawDot();
		} else if (item.equals(strings[1])) {
			drawLargeDot();
		} else if (item.equals(strings[2])) {
			drawWall();
		} else if (item.equals(strings[3])) {
			clear();
		} else if (item.equals(strings[4])) {
			drawPlayerSpawn();
		} else if (item.equals(strings[5])) {
			drawGhostHouse();
		} else if (item.contains(strings[6])) {
			drawplayer();
		} else if (item.contains(strings[7])) {
			//blinky
			drawGhost(blinky);
		} else if (item.contains(strings[8])) {
			//speedy
			drawGhost(speedy);
		}else if (item.contains(strings[9])) {
			//bashful
			drawGhost(bashful);
		}else if (item.contains(strings[10])) {
			//pokey
			drawGhost(pokey);
		}
	}
}

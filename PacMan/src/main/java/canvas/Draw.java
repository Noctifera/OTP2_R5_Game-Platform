package canvas;

import java.awt.Point;
import java.util.ArrayList;

import ghosts.Ghost;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import map.Map;
import player.Player;

/**
 * Class extends Canvas and allows items to be drawn onto it
 * 
 * @author markus
 * @version 1.0
 *
 */
public class Draw extends Canvas implements Draw_IF {

	private Player player;
	private Ghost[] gh;

	private Map map;

	private GraphicsContext gc;
	private int tileSize;

	public Draw(int x, int y, int tileSize, Player player, Ghost[] gh, Map map, GraphicsContext gc) {
		super(x, y);
		this.gc = gc;
		this.tileSize = tileSize;
		this.player = player;
		this.gh = gh;
		this.map = map;
	}

	/**
	 * One function that combines all drawn
	 * 
	 * @return
	 */
	public void update() {
		clear();
		draw();
		move();
		drawGhost();
	}

	/**
	 * Gets Players position and draws an orange circle
	 */

	private void move() {
		gc.setFill(Color.ORANGE);
		//System.out.println("player: "+ player.getPos());
		gc.fillOval(player.getPos().getX() + 5, player.getPos().getY() + 5, tileSize - 10, tileSize - 10);
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
		gc.fillRect(point.getX() + 1, point.getY() + 1, tileSize - 2, tileSize - 2);
	}

	private void drawDot(Point pos) {
		gc.setFill(Color.WHITE);
		int block = tileSize / 4;
		gc.fillOval(pos.getX() + block + block / 2, pos.getY() + block + block / 2, block, block);
	}

	private void drawLargeDot(Point pos) {
		gc.setFill(Color.WHITE);
		int block = tileSize / 2;
		gc.fillOval(pos.getX() + block / 2, pos.getY() + block / 2, block, block);
	}

	private void drawGhost() {
		for (int i = 0; i < gh.length; i++) {
			//System.out.println("ghost: "+i+gh[i].getPos());
			gc.setFill(gh[i].getColor());
			gc.fillRect(gh[i].getPos().getX() + 5, gh[i].getPos().getY() + 5, tileSize - 10, tileSize - 10);
			// gc.drawImage(gh[i].getImage(),gh[i].getPos().getX(),gh[i].getPos().getY(),tileSize-10,
			// tileSize-10);
		}
	}

	private void draw() {
		ArrayList<Point> walls = map.getWalls();
		// System.out.println(walls);
		ArrayList<Point> dots = map.getDots();
		// System.out.println(dots);
		ArrayList<Point> largedots = map.getLargeDots();
		// System.out.println(largedots);

		for (int i = 0; i < this.getHeight();) {
			for (int j = 0; j < this.getWidth();) {
				if (dots.contains(new Point(j, i))) {
					drawDot(new Point(j, i));

				}
				if (largedots.contains(new Point(j, i))) {
					drawLargeDot(new Point(j, i));

				}
				if (walls.contains(new Point(j, i))) {
					drawWall(new Point(j, i));
				}
				j = j + 40;
			}
			i = i + 40;
		}

	}

}

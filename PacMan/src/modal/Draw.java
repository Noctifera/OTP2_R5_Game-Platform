package modal;

import java.awt.Point;
import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Draw extends Canvas implements Draw_IF {

	private Player player;
	private Ghost[] gh;

	private Map map;

	private GraphicsContext gc;
	private int tileSize;


	public Draw(int x, int y, int tileSize, Player player, Ghost[] gh,Map map) {
		super(x, y);
		this.gc = this.getGraphicsContext2D();
		this.tileSize = tileSize;
		this.player = player;
		this.gh = gh;
		this.map = map;
	}

	public void update() {
		clear();
		draw();
		move();
		drawGhost();
	}

	public void move() {
		gc.setFill(Color.ORANGE);
		gc.fillOval(player.getPos().getX()+5, player.getPos().getY()+5, tileSize-10, tileSize-10);
	}

	public void clear() {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

	public void drawWall(Point point) {
		gc.setFill(Color.BLUE);
		gc.fillRect(point.getX()+1, point.getY()+1, tileSize-2, tileSize-2);
	}

	public void drawDot(Point pos) {
		gc.setFill(Color.WHITE);
		int block = tileSize / 4;
		gc.fillOval(pos.getX() + block + block / 2, pos.getY() + block + block / 2, block, block);
	}

	public void drawLargeDot(Point pos) {
		gc.setFill(Color.WHITE);
		int block = tileSize / 2;
		gc.fillOval(pos.getX() + block / 2, pos.getY() + block / 2, block, block);
	}

	public void drawGhost() {
		for (int i = 0; i < gh.length; i++) {
			gc.setFill(gh[i].getColor());
			gc.fillRect(gh[i].getPos().getId().getX()+5, gh[i].getPos().getId().getY()+5, tileSize-10, tileSize-10);
		}
	}
	public void draw() {
		ArrayList<Point> walls = map.getWalls();
		ArrayList<Point> dots = map.getDots();
		ArrayList<Point> largedots = map.getLargeDots();
		
		for (int i = 0; i < this.getHeight();) {
			for (int j = 0; j < this.getWidth();) {
				if (dots.contains(new Point(j,i))) {
					drawDot(new Point(j, i));

				}
				if (largedots.contains(new Point(j,i))) {
					drawLargeDot(new Point(j, i));

				}
				if (walls.contains(new Point(j,i))) {
					drawWall(new Point(j, i));
				}
				j = j + 40;
			}
			i = i + 40;
		}

	}

}

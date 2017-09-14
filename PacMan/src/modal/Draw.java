package modal;

import java.awt.Point;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Draw extends Canvas implements Draw_IF {

	private Player player;
	private Map map;
	private Ghost[] gh;
	private GhostThread[] ght;

	private GraphicsContext gc;
	private int size;
	private String[] strings;

	public Draw(int x, int y, int size, Player player, Map map, Ghost[] gh, String[] strings, GhostThread[] ght) {
		super(x, y);
		this.gc = this.getGraphicsContext2D();
		this.size = size;
		this.player = player;
		this.map = map;
		this.gh = gh;
		this.strings = strings;
		this.ght = ght;
	}

	public void update() {
		clear();
		move();
		draw();
		drawGhost();
	}

	public void eat() {
		// System.out.println("eat");
		for (int i = 0; i < gh.length; i++) {
			// System.out.println("player pos: "+player.getPos()+" ghostPos:
			// "+gh[i].getPos());
			if (player.getPos().equals(gh[i].getPos())) {
				player.getEaten();
				player.setPos(map.getPlayerSpawn());
				
				for (int j = 0; j < gh.length; j++) {
					gh[j].setPos(map.getGhostHouse());
				}
			}
		}
	}

	public Point getPlayerSpawn() {
		return map.getPlayerSpawn();
	}

	public void move() {
		gc.setFill(Color.ORANGE);
		gc.fillOval(player.getPos().getX(), player.getPos().getY(), size, size);
	}

	public void clear() {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

	public void drawWall(Point point) {
		gc.setFill(Color.BLUE);
		gc.fillRect(point.getX(), point.getY(), size, size);
	}

	public void drawDot(Point pos) {
		gc.setFill(Color.WHITE);
		int block = size / 4;
		gc.fillOval(pos.getX() + block + block / 2, pos.getY() + block + block / 2, block, block);
	}

	public void drawLargeDot(Point pos) {
		gc.setFill(Color.WHITE);
		int block = size / 2;
		gc.fillOval(pos.getX() + block / 2, pos.getY() + block / 2, block, block);
	}

	public void drawGhost() {
		for (int i = 0; i < gh.length; i++) {
			gc.setFill(Color.RED);
			gc.fillRect(gh[i].getPos().getX(), gh[i].getPos().getY(), size, size);
		}
	}

	public boolean collectAllDots() {
		boolean check = false;
		if (map.getDots().size() == 0 && map.getLargeDots().size() == 0) {
			check = true;

		}
		return check;
	}

	public void draw() {

		for (int i = 0; i < this.getHeight();) {
			for (int j = 0; j < this.getWidth();) {
				if (map.getMap().get(new Point(j, i)).equals(strings[0])) {
					drawDot(new Point(j, i));

				}
				if (map.getMap().get(new Point(j, i)).equals(strings[1])) {
					drawLargeDot(new Point(j, i));

				}
				if (map.getMap().get(new Point(j, i)).equals(strings[2])) {
					drawWall(new Point(j, i));
				}
				j = j + 40;
			}
			i = i + 40;
		}

	}

}

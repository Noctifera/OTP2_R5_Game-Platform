package modal;

import java.awt.Point;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Draw extends Canvas implements Draw_IF {

	private Player player;
	private Map map;
	private Ghost[] gh;

	private GraphicsContext gc;
	private int size;
	private String[] strings;

	public Draw(int x, int y, int size, Player player, Map map,Ghost[] gh,String[] strings) {
		super(x, y);
		this.gc = this.getGraphicsContext2D();
		this.size = size;
		this.player = player;
		this.map = map;
		this.gh = gh;
		this.strings = strings;
	}

	public void update() {
		clear();
		move();
		draw();
		drawGhost();
	}

	public void move() {
		gc.setFill(Color.ORANGE);

		try{
		gc.fillOval(player.getPos().getX(), player.getPos().getY(), size, size);
		}catch(NullPointerException e){
			player.setPos(map.getPlayerSpawn());
		}
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
		for(int i = 0; i<gh.length; i++){
			gc.setFill(Color.RED);
			gc.fillRect(gh[i].getPos().getX(), gh[i].getPos().getY(), size, size);
		}


	}

	public void draw() {
		int free = 0;
		for(int i = 0; i<this.getHeight();){
			for(int j = 0; j<this.getWidth();){
				if(map.getMap().get(new Point(j,i)).equals(strings[0])){
					drawDot(new Point(j,i));
					free++;
				}if(map.getMap().get(new Point(j,i)).equals(strings[1])){
					drawLargeDot(new Point(j,i));
					free++;
				}if(map.getMap().get(new Point(j,i)).equals(strings[2])){
					drawWall(new Point(j,i));
				}
				j = j +40;
			}
			i = i +40;
		}
		//System.out.println(free);

	}

}

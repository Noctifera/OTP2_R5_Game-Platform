package modal_CreateMap;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Draw extends Canvas {
	private Map map;
	private GraphicsContext gc;
	private final int tileSize;
	private String[] strings;

	public Draw(int x, int y, int tileSize, Map map, String[] strings) {
		super(x, y);
		this.gc = this.getGraphicsContext2D();
		this.map = map;
		this.strings = strings;
		this.tileSize = tileSize;
	}

	public void drawOuterBound() {
		gc.setFill(Color.BLACK);
		gc.moveTo(0, 0);
		gc.lineTo(this.getWidth(), 0);

		gc.moveTo(this.getWidth(), 0);
		gc.lineTo(this.getWidth(), this.getHeight());

		gc.moveTo(this.getWidth(), this.getHeight());
		gc.lineTo(0, this.getHeight());

		gc.moveTo(0, this.getHeight());
		gc.lineTo(0, 0);

		gc.stroke();

	}

	public void drawGrid() {
		gc.setFill(Color.BLACK);
		for (int i = 0; i < this.getWidth();) {
			gc.moveTo(i, 0);
			gc.lineTo(i, this.getHeight());

			i = i + tileSize;
		}
		for (int i = 0; i < this.getHeight();) {
			gc.moveTo(0, i);
			gc.lineTo(this.getWidth(), i);

			i = i + tileSize;
		}
		gc.stroke();
	}

	public void clear() {
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

	private void clearTile(Point pos) {
		gc.setFill(Color.WHITE);
		gc.fillRect(pos.getX() + 2, pos.getY() + 2, tileSize - 4, tileSize - 4);

	}

	private void drawWall(Point pos) {
		gc.setFill(Color.BLUE);
		gc.fillRect(pos.getX() + 2, pos.getY() + 2, tileSize - 4, tileSize - 4);
	}

	private void drawDot(Point pos) {
		gc.setFill(Color.BLACK);
		int size = tileSize / 4;
		gc.fillOval(pos.getX() + size + size / 2, pos.getY() + size + size / 2, size, size);

	}

	private void drawLargeDot(Point pos) {
		gc.setFill(Color.BLACK);
		int size = tileSize / 2;
		gc.fillOval(pos.getX() + size / 2, pos.getY() + size / 2, size, size);

	}

	private void drawPlayerSpawn(Point pos) {
		gc.setFill(Color.GREEN);
		gc.fillRect(pos.getX() + 2, pos.getY() + 2, tileSize - 4, tileSize - 4);

	}

	private void drawGhostHouse(Point pos) {
		gc.setFill(Color.RED);
		gc.fillRect(pos.getX() + 2, pos.getY() + 2, tileSize - 4, tileSize - 4);

	}

	public void drawFullMap() {
		Point point;
		for (int i = 0; i < this.getHeight();) {
			for (int j = 0; j < this.getWidth();) {
				point = new Point(j, i);
				if (map.getMap().get(point).equals(strings[0])) {
					// Dot
					drawDot(point);
				} else if (map.getMap().get(point).equals(strings[1])) {
					// LargeDot
					drawLargeDot(point);
				} else if (map.getMap().get(point).equals(strings[2])) {
					// Wall
					drawWall(point);
				} else if (map.getMap().get(point).equals(strings[3])) {
					// Empty
					clearTile(point);
				} else if (map.getMap().get(point).equals(strings[4])) {
					// PlayerSpawn
					drawPlayerSpawn(point);
				} else if (map.getMap().get(point).equals(strings[5])) {
					// GhostHouse
					drawGhostHouse(point);
				}

				j = j + tileSize;
			}
			i = i + tileSize;

		}
	}

	public byte[] getImage(){
		Image image = this.snapshot(null, null);
		byte[] data = null;
		BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			ImageIO.write(bImage, "PNG", baos);
			baos.flush();
			data = baos.toByteArray();
			baos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}

}

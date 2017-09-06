package modal;

import java.awt.Point;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Draw extends Canvas {
	private GraphicsContext gc;
	private final int tileSize;

	public Draw(int x, int y, int tileSize) {
		super(x, y);
		this.gc = this.getGraphicsContext2D();
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

	public void clearTile(Point pos) {
		gc.setFill(Color.WHITE);
		gc.fillRect(pos.getX(), pos.getY(), tileSize, tileSize);

	}

	public void drawWall(Point pos) {
		gc.setFill(Color.BLUE);
		gc.fillRect(pos.getX(), pos.getY(), tileSize, tileSize);
	}

	public void drawDot(Point pos) {
		gc.setFill(Color.BLACK);
		int size = tileSize / 4;
		gc.fillOval(pos.getX() + size + size / 2, pos.getY() + size + size / 2, size, size);

	}

	public void drawLargeDot(Point pos) {
		gc.setFill(Color.BLACK);
		int size = tileSize / 2;
		gc.fillOval(pos.getX() + size / 2, pos.getY() + size / 2, size, size);

	}

}

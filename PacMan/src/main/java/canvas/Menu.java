package canvas;

import java.util.List;

import hibernate.DataBaseConnection;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Menu extends Canvas implements Draw_IF {
	private GraphicsContext gc;

	public Menu(int width, int height, GraphicsContext gc) {
		super(width, height);
		this.gc = gc;
	}

	private void drawString(String string, int x, int y, int width, int height, Font f, Image image) {

		gc.setFont(f);
		gc.setFill(Color.BLACK);

		if (image != null) {

			gc.drawImage(image, x, y, width, height);
			gc.fillText(string, (x + width / 2) - (string.length() + f.getSize()), y + height + f.getSize());

		} else {

			gc.fillText(string, x, y);
		}
	}

	private void ColorBox(String string, int x, int y, int width, int height, Font f, Image image) {
		gc.setFill(Color.BLUE);
		gc.fillRect(x - 5, y - 5, width + 10, height + f.getSize() + 10);
		gc.drawImage(image, x, y, width, height);
		gc.setFill(Color.BLACK);
		gc.fillText(string, (x + width / 2) - (string.length() + f.getSize()), y + height + f.getSize());
	}

	private void clear() {
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());

	}

	public void update() {
		clear();
		drawMenu(DataBaseConnection.maplist());
	}

	private void drawMenu(List<TextNode> texts) {
		for (TextNode t : texts) {
			if (t.isColor()) {
				ColorBox(t.getText(), t.getX(), t.getY(), t.getImageWidth(), t.getImageHeight(), t.getFont(), t.getImage());
			} else {
				drawString(t.getText(), t.getX(), t.getY(), t.getImageWidth(), t.getImageHeight(), t.getFont(), t.getImage());
			}

		}

	}


}

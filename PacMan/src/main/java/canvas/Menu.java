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

	public Menu(int width, int height) {
		super(width, height);
		this.gc = this.getGraphicsContext2D();
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
		this.toFront();
		this.setFocusTraversable(true);
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

	@Override
	public void handle(DrawThread dt) {
		// TODO Auto-generated method stub
		this.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent me) {
				// TODO Auto-generated method stub
				System.out.println(me.getX());
				System.out.println(me.getY());
			}
		});
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if(event.getCode() == KeyCode.ESCAPE) {
					toBack();
					setFocusTraversable(false);
					dt.runGame();
				}
			}
		});
	}

	@Override
	public void front() {
		// TODO Auto-generated method stub
		this.toFront();
		this.setFocusTraversable(true);
	}

	@Override
	public void back() {
		// TODO Auto-generated method stub
		this.toFront();
		this.setFocusTraversable(false);
	}

}

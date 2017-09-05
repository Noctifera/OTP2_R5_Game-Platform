package controller;

import java.awt.Point;

import javafx.scene.input.MouseEvent;
import modal.Draw;

public class Controller {
	private Draw draw;
	private String[] strings;

	public Controller(Draw draw) {
		this.draw = draw;
	}

	public void draw(MouseEvent e, String s) {
		int mouseX = (int) e.getX();
		int mouseY = (int) e.getY();

		while (mouseX % 40 != 0) {
			mouseX = mouseX - 1;
			if (mouseX % 40 == 0) {
				break;
			}

		}
		while (mouseY % 40 != 0) {
			mouseY = mouseY - 1;
			if (mouseY % 40 == 0) {
				break;
			}
		}
		Point point = new Point(mouseX,mouseY);
		if (s.equals(strings[0])) {
			draw.drawDot(point);

		}else if(s.equals(strings[1])){
			draw.drawLargeDot(point);

		}else if(s.equals(strings[2])){
			draw.drawWall(point);

		}else if(s.equals(strings[3])){
			draw.clearTile(point);

		}
	}
}

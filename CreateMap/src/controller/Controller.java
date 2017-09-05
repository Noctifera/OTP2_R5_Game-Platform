package controller;

import java.awt.Point;

import javafx.scene.input.MouseEvent;
import modal.Draw;
import modal.Map;

public class Controller {
	private Draw draw;
	private Map map;

	private String[] strings;

	public Controller(Draw draw, Map map, String[] strings) {
		this.draw = draw;
		this.map = map;
		this.strings = strings;
	}

	public void beginning() {
		draw.drawGrid();
		draw.drawOuterBound();
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
		Point point = new Point(mouseX, mouseY);
		System.out.println(point);
		if (s.equals(strings[0])) {
			draw.drawDot(point);
			map.addToMap(point, strings[0]);

		} else if (s.equals(strings[1])) {
			draw.drawLargeDot(point);
			map.addToMap(point, strings[1]);

		} else if (s.equals(strings[2])) {
			draw.drawWall(point);
			map.addToMap(point, strings[2]);

		} else if (s.equals(strings[3])) {
			draw.clearTile(point);
			map.addToMap(point, strings[3]);

		}
	}
}
